import scala.concurrent.Future
import org.scalatestplus.play.PlaySpec
import play.api.mvc.{Result, Results}
import play.api.test._
import play.api.test.Helpers._
import controllers.MessageController

class MessageControllerSpec extends PlaySpec with Results {
  "The message controller" should {
    "respond with a message" in {
      val controller = new MessageController(stubControllerComponents())
      val result: Future[Result] = controller.getMessage().apply(FakeRequest())
      val bodyText: String = contentAsString(result)
      status(result) mustEqual OK
      contentType(result) mustEqual Some("application/json")
      bodyText must include "message"
    }
  }
}