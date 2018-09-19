package controllers

import play.api._
import play.api.mvc._
import models._

object Application extends Controller {
  def redirectApi = Action {
    Redirect(url = "/assets/lib/swagger-ui/index.html", queryString = Map("url" -> Seq("/swagger.json")))
  }
}