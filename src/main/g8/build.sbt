name := "$name$"
description := "$app_description$"
version := "1.0-SNAPSHOT"
organization := "$organization$"
scalaVersion := "2.12.6"

lazy val root = (project in file(".")).enablePlugins(PlayScala, SwaggerPlugin)

swaggerDomainNameSpaces := Seq("models")

libraryDependencies ++= Seq(
  guice,
  ws,
  "com.typesafe.play" %% "play-slick-evolutions" % "3.0.1",
  "com.typesafe.play" %% "play-slick" % "3.0.1",
  "org.postgresql" % "postgresql" % "42.2.2",
  "org.webjars" % "swagger-ui" % "3.13.3",
  "com.h2database" % "h2" % "1.4.197" % Test,
  "commons-io" % "commons-io" % "2.6",
  "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test,
  "com.typesafe.akka" %% "akka-testkit" % "2.5.11" % Test,
  "org.mockito" % "mockito-core" % "2.18.0" % Test
)
scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature",
  "-language:postfixOps", "-language:reflectiveCalls")

coverageExcludedPackages := "<empty>;controllers\\\\.docs\\\\..*;router\\\\..*;dao\\\\..*;handlers\\\\.TrailingSlashRequestHandler"
coverageMinimum := 75
coverageFailOnMinimum := true

scalastyleFailOnError := true
scalastyleFailOnWarning := true
