package modules

import java.nio.charset.StandardCharsets

import javax.inject.{Inject, Singleton}
import org.apache.commons.io.FileUtils
import play.api.Environment
import play.api.db.evolutions._
import play.api.inject.{SimpleModule, _}
import play.api.libs.Collections

/**
  * Default module for evolutions API.
  */
class EvolutionsModule
    extends SimpleModule(
      bind[EvolutionsConfig].toProvider[DefaultEvolutionsConfigParser],
      bind[EvolutionsReader].to[CustomEvolutionsReader],
      bind[EvolutionsApi].to[DefaultEvolutionsApi],
      bind[ApplicationEvolutions]
        .toProvider[ApplicationEvolutionsProvider]
        .eagerly
    )

/**
  * Based on Evolution's sources
  *
  * @param environment
  */
@Singleton
class CustomEvolutionsReader @Inject()(environment: Environment)
    extends EvolutionsReader {

  /**
    * Read the application evolutions.
    *
    * @param db the database name
    */
  // scalastyle:off method.length
  def evolutions(db: String): Seq[Evolution] = {

    val upsMarker = """^#.*!Ups.*$"$"$""".r
    val downsMarker = """^#.*!Downs.*$"$"$""".r

    val UPS = "UPS"
    val DOWNS = "DOWNS"
    val UNKNOWN = "UNKNOWN"

    val mapUpsAndDowns: PartialFunction[String, String] = {
      case upsMarker() => UPS
      case downsMarker() => DOWNS
      case _ => UNKNOWN
      case _             => UNKNOWN
    }

    val isMarker: PartialFunction[String, Boolean] = {
      case upsMarker()   => true
      case downsMarker() => true
      case _             => false
    }

    val folder = environment.getFile(Evolutions.directoryName(db))
    val sqlFiles = folder
      .listFiles()
      .filter(file => file.getName.indexOf(".sql") > -1)
      .sortBy(file => {
        val fileName = file.getName
        val nameAfterSqlNumber = fileName
          .split("\\\\.")(0)
          .split("_")
          .drop(1)
          .mkString("") + ".sql"
        val sqlNumber = fileName.split("\\\\.")(0).split("_")(0).toInt
        val newPrefix = "%07d".format(sqlNumber)
        newPrefix + nameAfterSqlNumber
      })
      .toSeq
    sqlFiles
      .zip(1 to sqlFiles.size)
      .map {
        case (file, revision) => {
          val script = FileUtils.readFileToString(file, StandardCharsets.UTF_8)
          val parsed = Collections
            .unfoldLeft(("", script.split('\\n').toList.map(_.trim))) {
              case (_, Nil) => None
              case (context, lines) => {
                val (some, next) = lines.span(l => !isMarker(l))
                Some(
                  (next.headOption
                     .map(c => (mapUpsAndDowns(c), next.tail))
                     .getOrElse("" -> Nil),
                   context -> some.mkString("\\n")))
              }
            }
            .reverse
            .drop(1)
            .groupBy(i => i._1)
            .mapValues {
              _.map(_._2).mkString("\\n").trim
            }
          Evolution(
            revision,
            parsed.getOrElse(UPS, ""),
            parsed.getOrElse(DOWNS, "")
          )
        }
      }
  }
  // scalastyle:on method.length
}
