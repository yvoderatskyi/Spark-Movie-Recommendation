package services

import models.User
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD

import play.Configuration

object SparkService {
  private lazy val context = {
    val conf = new SparkConf(false)
      .setAppName(SparkConfig.appName)
      .setMaster(SparkConfig.master)
    
      for((key,value) <- SparkConfig.configs) {
        conf.set(key, value)
      }
    
    new SparkContext(conf)    
  }

  lazy val userRDD = {
    val file: RDD[String] = context.textFile(Configuration.root().getString("user.file"))
    val users = file.map(User.fromString)
    users.cache()
    users
  }
  
  def version = context.version
}
