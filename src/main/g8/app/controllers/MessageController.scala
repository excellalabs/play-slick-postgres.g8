package controllers

import javax.inject.{Inject, Singleton}
import models.Message
import play.api.libs.json.{Json, Format}
import play.api.mvc._

@Singleton()
class MessageController @Inject() (cc: ControllerComponents) extends AbstractController(cc) {
  implicit val fmt: Format[Message] = Json.format[Message]

  def getMessage: Action[AnyContent] = Action {
    Ok(Json.toJson(Message("Hello from message endpoint.")))
  }
}
