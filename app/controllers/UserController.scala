package controllers

import play.api.mvc._
import services._

object UserController extends Controller {
  def list() = Action {
    Ok(views.html.user.list(UserService.listUsers))
  }

  def show(id: Int) = Action {
    UserService.getUserById(id.toLong) match {
      case Some(user) =>
        Ok(views.html.user.show(
          user,
          RatingService.getRatingsByUser(user),
          MovieService.recommendMovies(user)))
      case _ =>
        Redirect(routes.UserController.list())
    }
  }
}