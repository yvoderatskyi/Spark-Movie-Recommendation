package models

import org.apache.spark.mllib.recommendation
import org.apache.spark.rdd.RDD
import services.SparkService
import org.apache.spark.SparkContext._
import services.SparkService._

/**
 * Created by yuriy on 2/27/15.
 */

case class Movie(id: Int, title: String, prodDate: String, link: String) extends Serializable

object Movie {
  def fromString(input: String) = input.split("\\|+").take(4) match {
    case Array(id, title, date, link) => Movie(id.toInt, title, date, link)
  }

  def listMovies: List[Movie] = {
    SparkService.movieRDD
      .sortBy(m => m.id)
      .collect()
      .toList
  }

  def getMovieById(id: Long): Option[Movie] = {
    SparkService.movieRDD
      .collect()
      .toList
      .find(m => m.id == id)
  }

  def queryMovies(query: String): List[Movie] = {
    SparkService.movieRDD
      .filter(m => m.title.toLowerCase.contains(query.toLowerCase))
      .collect()
      .toList
  }

  def getTopRatedMovies(amount: Int = 20): List[Movie] = {
    val ratingCounts = SparkService.ratingRDD
      .map(r => (r.product, 1))
      .reduceByKey(_+_)
      .sortBy(_._2, ascending = false)
    val movieIds = SparkService.movieRDD.keyBy(_.id) // RDD[movieId, Movie]
    ratingCounts.join(movieIds)      // RDD[(movieId: Int, (count: Int, Movie))]
      .sortBy(_._2._1, ascending = false) //sort by counts
      .map(_._2._2) //return Array[Movie]
      .take(amount)
      .toList
  }

  def recommendMovies(user: User, amount: Int = 20): List[Movie] = {
    val predicted = SparkService.factorizationModel
      .recommendProducts(user.id, amount)

    val predictedRDD = context.parallelize(predicted)

    val predictedIDs: RDD[(Int, recommendation.Rating)] = predictedRDD.keyBy(r => r.product)
    val movieIDs: RDD[(Int, Movie)] = SparkService.movieRDD.keyBy(m => m.id)

    predictedIDs.join(movieIDs) //RDD[(movieID, (Rating, Movie))]
      .map(_._2._2) //RDD[Movie]
      .collect()
      .toList
  }
}
