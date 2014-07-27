package controllers

import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import play.api.libs.json._

import models.Echo

object Api1_0 extends Controller {
  val echoForm = Form("message" -> nonEmptyText)

  def echos = Action {
    Ok(Json.obj("echos" -> Json.toJson(Echo.all().map { t => t.message} toList)))
  }

  def newEcho = Action { implicit request =>
    echoForm.bindFromRequest.fold(
      errors => BadRequest(Json.obj("errors" -> List(Map("message" -> "Bad Request")))),
      message => {
        Echo.create(message)
        Ok(Json.obj("echos" -> Json.toJson(Echo.all().map { t => t.message} toList)))
      }
    )
  }
}
