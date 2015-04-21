package utils

import play.Configuration

object SparkConfig extends Serializable {
  private val conf = Configuration.root()

  val master = conf.getString("spark.master")

  val appName = conf.getString("spark.app.name")

  val configs = Map(
    "spark.logConf" -> "true",
    "spark.driver.host" -> conf.getString("spark.driver.host"),
    "spark.driver.port" -> conf.getString("spark.driver.port"),
    "spark.akka.logLifecycleEvents" -> "true"
  )
}
