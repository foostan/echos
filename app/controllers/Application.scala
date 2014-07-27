package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import play.api.libs.json._

import models.Echo

object Application extends Controller {
  def index = Action {
    Redirect(routes.Application.echos)
  }

  def echos = Action {
    Ok(views.html.index(Echo.all(), echoForm))
  }

  val echoForm = Form("message" -> nonEmptyText)
  def newEcho = Action { implicit request =>
    echoForm.bindFromRequest.fold(
      errors => BadRequest(Json.obj("errors" -> List(Map("message" -> "Bad Request")))),
      message => {
        Echo.create(message)
        Ok(Json.obj("echos" -> List(message)))
      }
    )
  }
}