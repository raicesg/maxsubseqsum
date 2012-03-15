// SBT Basic Configuration File

name := "Maximum Subsequence Sum"

version := "0.1.0"

scalaVersion := "2.9.1"

// sbt-onejar config

seq(com.github.retronym.SbtOneJar.oneJarSettings: _*)

libraryDependencies += "commons-lang" % "commons-lang" % "2.6"

mainClass in oneJar := Some("edu.spsu.rgoodwin.maxsubseqsum.App")

// Extra Libraries

libraryDependencies ++= Seq(
	"org.scala-lang" % "scala-swing" % "2.9.1"
)

resolvers += ScalaToolsReleases
