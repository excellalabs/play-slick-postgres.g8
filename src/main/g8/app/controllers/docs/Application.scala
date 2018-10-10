package controllers.docs

import javax.inject.{Inject, Singleton}
import play.api.mvc._

@Singleton
class Application @Inject()(cc: ControllerComponents)
    extends AbstractController(cc) {
  def redirectApi: Action[AnyContent] = Action {
    Redirect(url = "/assets/lib/swagger-ui/index.html",
             queryString =
               Map("url" -> Seq("http://localhost:9000/assets/swagger.json")))
  }
}
