package models

import anorm.SqlParser._
import anorm._
import play.api.Play.current
import play.api.db._

case class Echo(id: Long, message: String)

object Echo {
  val echo = {
    get[Long]("id") ~
      get[String]("message") map {
      case id ~ label => Echo(id, label)
    }
  }

  def all(): List[Echo] = DB.withConnection { implicit c =>
    SQL("select * from echo order by id desc").as(echo *)
  }

  def create(message: String) {
    DB.withConnection { implicit c =>
      SQL("insert into echo (message) values ({message})").on(
        'message -> message
      ).executeUpdate()
    }
  }

  def purge(number: Int) {
    val echoList = all()
    if (echoList.size > number) {
      DB.withConnection { implicit c =>
        SQL("delete from echo where id <= ({id})").on(
          "id" -> echoList(number).id
        ).executeUpdate()
      }
    }
  }
}
