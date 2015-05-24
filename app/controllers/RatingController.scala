package controllers

import models._
import play.api.libs.json.{JsValue, Json, Writes}
import play.api.mvc._
import services._
import org.apache.spark.mllib.recommendation.Rating

object RatingController extends Controller {
  implicit val ratingWrites: Writes[Rating] = new Writes[Rating] {
    override def writes(r: Rating): JsValue = Json.obj(
      "userId" -> r.user,
      "movieId" -> r.product,
      "rating" -> r.rating
    )
  }

  implicit val movieWithRating: Writes[(Double, Movie)] = new Writes[(Double, Movie)] {
    override def writes(t: (Double, Movie)): JsValue = Json.obj(
      "rating" -> t._1,
      "title" -> t._2.title,
      "movieId" -> t._2.id,
      "prodDate" -> t._2.prodDate
    )
  }

  def list() = Action {
    Ok(Json.toJson(RatingService.listRatings))
  }

  def listByMovie(id: String) = Action {
    MovieService.getMovieById(id.toLong) match {
      case Some(movie: Movie) =>
        Ok(Json.toJson(RatingService.getRatingsByMovie(movie)))
      case _ => NotFound(s"Movie with id $id not found")
    }
  }

  def listByUser(id: String) = Action {
    UserService.getUserById(id.toLong) match {
      case Some(user: User) =>
        Ok(Json.toJson(RatingService.getRatingsByUser(user)))
      case _ => NotFound(s"User with id $id not found")
    }
  }
}