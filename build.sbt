name := """MovieRecomendation"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.10.4"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  ws,
  "org.apache.spark" %  "spark-core_2.10"  % "1.2.1",
  "org.apache.spark" %  "spark-mllib_2.10" % "1.2.1",
  "com.websudos"     %% "phantom-dsl"      % "1.8.0",
  "joda-time"        %  "joda-time"        % "2.7",
  "org.webjars"      %% "webjars-play"     % "2.3.0-3",
  "org.webjars"      %  "bootswatch-journal" % "3.3.2",
  "org.webjars.bower" % "angular"            % "1.3.15",
  "org.webjars.bower" % "jquery"             % "2.1.3"
)
