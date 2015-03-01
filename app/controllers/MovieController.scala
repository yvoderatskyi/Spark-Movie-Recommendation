package controllers

import models.{Rating, Movie}
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._

object MovieController extends Controller {

  val searchForm = Form (
    "query" -> nonEmptyText
  )

  def list = Action {
    Ok(views.html.movie.list(Movie.listMovies))
  }

  def search = Action { implicit request =>
    searchForm.bindFromRequest.fold(
      formWithErrors => BadRequest("Empty query"),
      value => Ok(views.html.movie.list(Movie.queryMovies(value)))
    )
  }

  def top = Action {
    Ok(views.html.movie.list(Movie.getTopRatedMovies()))
  }

  def show(id: Int) = Action {
    Movie.getMovieById(id) match {
      case Some(movie) => Ok(views.html.movie.show(movie, Rating.getRatingsByMovie(movie)))
      case _ => Redirect(routes.MovieController.list())
    }
  }
}