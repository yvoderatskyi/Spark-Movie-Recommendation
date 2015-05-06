package models

import org.apache.spark.mllib.recommendation

object Rating extends Serializable {
  def apply(input: String): recommendation.Rating = input.split("\\s+") match {
    case Array(userId, movieId, rating, _) =>
      recommendation.Rating(
        userId.toInt,
        movieId.toInt,
        rating.toDouble)
  }
}


