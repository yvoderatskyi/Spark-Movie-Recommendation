package models

import org.apache.spark.mllib._

object Rating extends Serializable {
  def fromString(input: String) = input.split("\\s+") match {
    case Array(userId, movieId, rating, timestamp) =>
      recommendation.Rating(
        userId.toInt,
        movieId.toInt,
        rating.toDouble)
  }
}
