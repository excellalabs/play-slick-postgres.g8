name := "$name$"
description := "$app_description$"
version := "1.0-SNAPSHOT"
organization := "$organization$"
scalaVersion := "2.12.6"

lazy val root = (project in file(".")).enablePlugins(PlayScala, SwaggerPlugin)

swaggerDomainNameSpaces := Seq("models")

/*
* Workaround for https://github.com/sbt/sbt/issues/630 when running travis tests on the template.
* If desired, you can remove this line and rename the /tests/ folder to /test/
* You will need to do something different with the scripted test file /test
**/
scalaSource in Test := baseDirectory.value / "tests"

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

scalafmtOnCompile in Compile := true
