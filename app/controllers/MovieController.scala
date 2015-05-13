package controllers

import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import services.{RatingService, MovieService}

object MovieController extends Controller {

  val searchForm = Form (
    "query" -> nonEmptyText
  )

  def listMovies(page: Int, size: Int = 10) = Action {
    Ok(
      views.html.movie.list(
        MovieService.listMovies(page, size)))
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
      case _ => Redirect(routes.MovieController.listMovies())
    }
  }
}