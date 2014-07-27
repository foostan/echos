package controllers

import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import play.api.libs.json._

import models.Echo
import models.EchoForm

object Api1_0 extends Controller {
  val echoForm = Form(
    mapping(
      "message" -> nonEmptyText,
      "number" -> default(number, 10))(EchoForm.apply)(EchoForm.unapply))

  def echos = Action {
    Ok(Json.obj("echos" -> Json.toJson(Echo.all().map { t => t.message} toList)))
  }

  def newEcho = Action { implicit request =>
    echoForm.bindFromRequest.fold(
      errors => BadRequest(Json.obj("errors" -> List(Map("message" -> "Bad Request")))),
      values => {
        val echoList = (Echo.all().map { t => t.message} toList)
        Echo.create(values.message)
        Echo.purge(values.number)
        Ok(Json.obj("echo" -> values.message, "echos" -> Json.toJson(echoList)))
      }
    )
  }
}
