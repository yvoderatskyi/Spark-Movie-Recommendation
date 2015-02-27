package services

import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf

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
  
  def version = context.version
}
