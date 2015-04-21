package services

import java.io.File

import models.{Rating, Movie, User}
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf
import org.apache.spark.mllib.recommendation.ALS
import org.apache.spark.rdd.RDD

import play.api._
import play.api.Play.current
import utils.SparkConfig

object SparkService {
  lazy val context = {
    val conf = new SparkConf(false)
      .setAppName(SparkConfig.appName)
      .setMaster(SparkConfig.master)

      for((key,value) <- SparkConfig.configs) {
        conf.set(key, value)
      }

    new SparkContext(conf)
  }

  lazy val root = Play.application.path

  private def getData(fileName: String): String = {
    new File(root, "/public/data/" + fileName).toString
  }

  lazy val userRDD = {
    val input: RDD[String] = context.textFile(getData("u.user"))
    val users = input.map(User.fromString)
    users.cache()
    users
  }

  lazy val movieRDD = {
    val input: RDD[String] = context.textFile(getData("u.item"))
    val movies = input.map(Movie.fromString)
    movies.cache()
    movies
  }

  lazy val ratingRDD = {
    val input: RDD[String] = context.textFile(getData("u.data"))
    val ratings = input.map(Rating.fromString)
    ratings.cache()
    ratings
  }

  lazy val factorizationModel = {
    val rank = 10
    val numIterations = 20
    ALS.train(ratingRDD, rank, numIterations, 0.01)
  }

  lazy val version = context.version

}

