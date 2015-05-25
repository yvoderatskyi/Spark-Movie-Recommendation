package controllers

import play.api.libs.json.{Json, JsValue, Writes}
import play.api.mvc._
import models.{Movie, User}
import services._

object UserController extends Controller {
  implicit val userWrites: Writes[User] = new Writes[User] {
    override def writes(u: User): JsValue = Json.obj(
      "id" -> u.id,
      "age" -> u.age,
      "gender" -> u.gender,
      "occupation" -> u.occupation,
      "zipCode" -> u.zipCode
    )
  }

  def list() = Action {
    Ok(Json.toJson(UserService.listUsers))
  }

  def show(id: Int) = Action {
    UserService.getUserById(id.toLong) match {
      case Some(user: User) =>
        Ok(Json.toJson(user))
      case _ => NotFound(s"User with id $id not found")
    }
  }
}
