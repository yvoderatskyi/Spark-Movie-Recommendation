package controllers

import models.{Movie, Rating, User}
import play.api.mvc._

object UserController extends Controller {
  def list() = Action {
    Ok(views.html.user.list(User.listUsers))
  }

  def show(id: Int) = Action {
    User.getUserById(id.toLong) match {
      case Some(user) =>
        Ok(views.html.user.show(user, Rating.getRatingsByUser(user), Movie.recommendMovies(user)))
      case _ => Redirect(routes.UserController.list())
    }
  }
}