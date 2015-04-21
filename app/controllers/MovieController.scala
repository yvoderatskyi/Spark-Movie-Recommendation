package controllers

import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import services.{RatingService, MovieService}

object MovieController extends Controller {

  val searchForm = Form (
    "query" -> nonEmptyText
  )

  def list = Action {
    Ok(views.html.movie.list(MovieService.listMovies))
  }

  def search = Action { implicit request =>
    searchForm.bindFromRequest.fold(
      formWithErrors =>
        BadRequest("Empty query"),
      value =>
        Ok(views.html.movie.list(MovieService.queryMovies(value)))
    )
  }

  def top = Action {
    Ok(views.html.movie.list(MovieService.getTopRatedMovies()))
  }

  def show(id: Int) = Action {
    MovieService.getMovieById(id) match {
      case Some(movie) =>
        Ok(views.html.movie.show(
          movie,
          RatingService.getRatingsByMovie(movie)))
      case _ => Redirect(routes.MovieController.list())
    }
  }
}