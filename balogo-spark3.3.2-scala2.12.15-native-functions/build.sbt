version := "0.1.0-SNAPSHOT"

scalaVersion := "2.12.15"
val sparkVersion = "3.3.2"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion % Provided,
  "org.apache.spark" %% "spark-sql" % sparkVersion % Provided,
  "org.apache.spark" %% "spark-catalyst" % sparkVersion % Provided,
  "org.apache.commons" % "commons-text" % "1.9"
)

lazy val root = (project in file("."))
  .settings(
    name := "balogo-spark3.3.2-scala2.12.15-native-functions"
  )
