package services

import models._
import org.apache.spark.mllib.recommendation
import org.apache.spark.rdd.RDD
import services.SparkService
import org.apache.spark.SparkContext._
import services.SparkService._

/**
 * Created by yuriy on 4/21/15.
 */
object MovieService {
  def listMovies: List[Movie] = {
    SparkService.movieRDD
      .sortBy(m => m.id)
      .collect()
      .toList
  }

  def getMovieById(id: Long): Option[Movie] = {
    SparkService.movieRDD
      .filter(m => m.id == id)
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
    val movieById = SparkService.movieRDD.keyBy(_.id) // RDD[movieId, Movie]
    ratingCounts.join(movieById)      // RDD[(movieId: Int, (count: Int, Movie))]
      .sortBy(_._2._1, ascending = false) //sort by counts
      .map(_._2._2) //return Array[Movie]
      .take(amount)
      .toList
  }

  def recommendMovies(user: User, amount: Int = 20): List[Movie] = {
    val recommended = SparkService.factorizationModel
      .recommendProducts(user.id, amount)

    val recommendedRDD = context.parallelize(recommended)

    val recommendedById: RDD[(Int, recommendation.Rating)] = recommendedRDD.keyBy(r => r.product)
    val movieById: RDD[(Int, Movie)] = SparkService.movieRDD.keyBy(m => m.id)

    recommendedById.join(movieById) //RDD[(movieID, (Rating, Movie))]
      .map(_._2._2) //RDD[Movie]
      .take(amount)
  }
}
