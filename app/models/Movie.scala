package models

import services.SparkService

/**
 * Created by yuriy on 2/27/15.
 */

case class Movie(id: Long, title: String, prodDate: String, link: String) extends Serializable

object Movie {
  def fromString(input: String) = input.split("\\|+").take(4) match {
    case Array(id, title, date, link) => Movie(id.toLong, title, date, link)
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
      .filter(m => m.title.contains(query))
      .collect()
      .toList
  }
}
