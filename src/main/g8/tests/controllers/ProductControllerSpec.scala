package controllers

import common.SlickInMemorySpec
import play.api.mvc.{Result, Results}
import play.api.test.FakeRequest
import play.api.test.Helpers._

import scala.concurrent.Future

class ProductControllerSpec extends SlickInMemorySpec with Results {
  "The product controller" should {
    "respond to getAll with a list of products " in {
      val controller: ProductController = app.injector.instanceOf[ProductController]
      val result: Future[Result] = controller.getAll.apply(FakeRequest())
      val bodyText: String = contentAsString(result)
      status(result) mustEqual OK
      contentType(result) mustEqual Some("application/json")
      bodyText must include("tmblr-stl-med")
    }
  }
}
