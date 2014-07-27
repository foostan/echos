import play.Project._

name := "echos"

version := "1.0"

playScalaSettings

libraryDependencies ++= Seq(
  jdbc, anorm
)