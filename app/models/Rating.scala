package models

import org.apache.spark.mllib._
import services.SparkService

object Rating extends Serializable {
  def fromString(input: String): recommendation.Rating = input.split("\\s+") match {
    case Array(userId, movieId, rating) => recommendation.Rating(userId.toInt, movieId.toInt, rating.toDouble)
  }
  
  def listRatings: List[recommendation.Rating] = {
      SparkService.ratingRDD
        .sortBy(r => r.user)
        .collect()
        .toList
  }
  
  def getRatingsByUser(user: User) = {
    SparkService.ratingRDD
      .filter(r => r.user == user.id)
      .collect()
      .toList
  }
  
  def getRatingsByMovie(movie: Movie) = {
    SparkService.ratingRDD
      .filter(r => r.product == movie.id)
      .collect()
      .toList
  }
}
