package controllers

import play.api.libs.json._
import play.api.mvc._
import models.Movie
import services.MovieService

object MovieController extends Controller {
  implicit val movieWrites: Writes[Movie] = new Writes[Movie] {
    override def writes(m: Movie): JsValue = Json.obj(
      "id" -> m.id,
      "title" -> m.title,
      "prodDate" -> m.prodDate,
      "link" -> m.link
    )
  }

  def list = Action {
    Ok(Json.toJson(MovieService.listMovies))
  }

  def search(query: String) = Action { implicit request =>
    Ok(Json.toJson(MovieService.queryMovies(query)))
  }

  def top = Action {
    Ok(Json.toJson(MovieService.getTopRatedMovies()))
  }

  def show(id: Int) = Action {
    MovieService.getMovieById(id) match {
      case Some(movie: Movie) =>
        Ok(Json.toJson(movie))
      case _ => NotFound(s"Movie with id $id not found")
    }
  }
}