package controllers

import play.api.mvc._
import play.api.data._
import play.api.data.Forms._

import models.Echo
import models.EchoForm

object Application extends Controller {
  val echoForm = Form(
    mapping(
      "message" -> nonEmptyText,
      "number" -> number)(EchoForm.apply)(EchoForm.unapply))

  def index = Action {
    Redirect(routes.Application.echos)
  }

  def echos = Action {
    Ok(views.html.index(Echo.all(), echoForm))
  }
}