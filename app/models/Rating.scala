package models

import org.apache.spark.mllib._
import org.apache.spark.rdd.RDD
import org.apache.spark.SparkContext._
import services.SparkService
import services.SparkService._

object Rating extends Serializable {
  def fromString(input: String): recommendation.Rating = input.split("\\s+") match {
    case Array(userId, movieId, rating, timestamp) => recommendation.Rating(userId.toInt, movieId.toInt, rating.toDouble)
  }
  
  def listRatings: List[recommendation.Rating] = {
      SparkService.ratingRDD
        .sortBy(r => r.user)
        .collect()
        .toList
  }
  
  def getRatingsByUser(user: User): List[(Double, Movie)] = {
    val userRatings: RDD[recommendation.Rating] = SparkService.ratingRDD
      .filter(r => r.user == user.id)
    val ratingIDs = userRatings.map(r => (r.product, r.rating))
    val moviesIDs: RDD[(Int, Movie)] = SparkService.movieRDD.keyBy(m => m.id)
    val joined: RDD[(Int, (Double, Movie))] = ratingIDs.join(moviesIDs)
    joined
      .values
      .collect()
      .toList
  }
  
  def getRatingsByMovie(movie: Movie): List[recommendation.Rating] = {
    SparkService.ratingRDD
      .filter(r => r.product == movie.id)
      .collect()
      .toList
  }

}
