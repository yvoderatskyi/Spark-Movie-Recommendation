name := """MovieRecomendation"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.10.4"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  ws,
  "org.apache.spark" % "spark-core_2.10" % "1.2.1",
  "org.apache.spark" % "spark-mllib_2.10" % "1.2.1",
  "org.webjars" %% "webjars-play" % "2.3.0-3",
  "org.webjars" % "bootstrap" % "3.3.4"
)
