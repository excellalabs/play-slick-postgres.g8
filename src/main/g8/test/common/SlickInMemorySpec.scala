package common

import org.scalatest.BeforeAndAfter
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.{Application, Mode}
import play.api.db.DBApi
import play.api.db.evolutions.Evolutions
import play.api.inject.guice.GuiceApplicationBuilder

abstract class SlickInMemorySpec extends PlaySpec with GuiceOneAppPerSuite with BeforeAndAfter {
  implicit override lazy val app : Application = {
    val dbName = s"play-test-${scala.util.Random.nextInt()}"
    val dbUrl = s"jdbc:h2:mem:$dbName;MODE=PostgreSQL;DATABASE_TO_UPPER=false"

    new GuiceApplicationBuilder()
      .configure(Map(
        "slick.dbs.default" -> Map(
          "profile" -> "slick.jdbc.H2Profile$",
          "db" -> Map(
            "driver" -> "org.h2.Driver",
            "url" -> dbUrl
          )
        )
      ))
      .in(Mode.Test)
      .build
  }

  before {
    val db = app.injector.instanceOf[DBApi]
    Evolutions.applyEvolutions(db.database("default"))
  }

  after {
    val db = app.injector.instanceOf[DBApi]
    Evolutions.cleanupEvolutions(db.database("default"))
  }
}