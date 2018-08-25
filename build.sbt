scalacOptions ++= Seq("-Yrangepos", "-unchecked", "-deprecation")

version := "0.1"

name := "centrifuge"

organization := "io.univalence"

scalaVersion := "2.11.12"

resolvers ++= Seq(
  "sonatype-oss" at "http://oss.sonatype.org/content/repositories/snapshots",
  "OSS" at "http://oss.sonatype.org/content/repositories/releases",
  "Typesafe Releases" at "http://repo.typesafe.com/typesafe/releases/",
  "Conjars" at "http://conjars.org/repo",
  "Clojars" at "http://clojars.org/repo",
  "m2-repo-github" at "https://github.com/ahoy-jon/m2-repo/raw/master"
)

libraryDependencies ++= Seq(
  "com.chuusai" %% "shapeless" % "2.2.5",
  "org.scalaz" %% "scalaz-core" % "7.1.4",
  "org.spire-math" %% "spire" % "0.13.0",
  "org.typelevel" %% "shapeless-spire" % "0.6.1",
  "org.typelevel" %% "shapeless-scalaz" % "0.4",
  //  "io.univalence" %% "excelsius" % "0.1-SNAPSHOT",
  "org.apache.spark" %% "spark-core" % "2.1.1" % Provided,
  "org.apache.spark" %% "spark-sql" % "2.1.1" % Provided,
  "org.apache.spark" %% "spark-mllib" % "2.1.1" % Provided,
  "org.scalatest" %% "scalatest" % "3.0.3" % "test",
  "org.scalacheck" %% "scalacheck" % "1.13.5" % "test",
  "io.monix" %% "monix" % "2.3.3",
  "org.typelevel" %% "cats-core" % "1.0.0-MF",
  "org.typelevel" %% "cats-laws" % "1.0.0-MF"
)

//2.1.0-SNAPSHOT
addCompilerPlugin(
  "org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full)

libraryDependencies += "org.scala-lang" % "scala-reflect" % scalaVersion.value

//publishTo := Some(Resolver.file("file",  new File( "/Users/jon/Project/m2-repo")))
// Add sonatype repository settings
publishTo := Some(
  if (isSnapshot.value)
    Opts.resolver.sonatypeSnapshots
  else
    Opts.resolver.sonatypeStaging
)

lazy val format = TaskKey[Unit]("scalafmt", "FMT Files")

format := {
  import sys.process.Process
  Process("./scalafmt --non-interactive --git true", baseDirectory.value).!
}

//compile in Compile := (compile in Compile).dependsOn(format).value
