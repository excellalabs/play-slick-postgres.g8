package controllers

import javax.inject.{Inject, Singleton}
import play.api.mvc._
import play.api.libs.json.Json
import models.Message

@Singleton
class MessageController @Inject() (cc: ControllerComponents) extends AbstractController(cc) {
  implicit val fmt = Json.format[Message]

  def getMessage = Action {
    Ok(Json.toJson(Message("Hello from message endpoint.")))
  }
}