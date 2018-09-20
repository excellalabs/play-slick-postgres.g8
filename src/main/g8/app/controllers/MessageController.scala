package controllers

import javax.inject.{Inject, Singleton}
import play.api.mvc._
import play.api.libs.json.{Json, OFormat}
import models.Message

@Singleton
class MessageController @Inject() (cc: ControllerComponents) extends AbstractController(cc) {
  implicit val fmt : OFormat[Message] = Json.format[Message]

  def getMessage : Action[AnyContent] = Action {
    Ok(Json.toJson(Message("Hello from message endpoint.")))
  }
}
