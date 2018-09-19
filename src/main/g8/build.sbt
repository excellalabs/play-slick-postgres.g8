name := "$name$"
version := "1.0-SNAPSHOT"
organization := "$organization$"
scalaVersion := "2.12.6"

lazy val root = (project in file(".")).enablePlugins(PlayScala, SwaggerPlugin)

swaggerDomainNameSpaces := Seq("models")

libraryDependencies ++= Seq(
  guice,
  ws,
  cache,
  "com.typesafe.play" %% "play-slick-evolutions" % "3.0.3",
  "com.typesafe.play" %% "play-slick" % "3.0.3",
  "org.postgresql" % "postgresql" % "42.2.2",
  "org.webjars" % "swagger-ui" % "3.13.3",
  "ai.x" %% "play-json-extensions" % "0.10.0",
  "com.typesafe.play" %% "play-json-joda" % "2.6.9",
  "com.github.tminglei" %% "slick-pg" % "0.16.0",
  "com.github.tminglei" %% "slick-pg_play-json" % "0.16.0",
  "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test,
  "com.typesafe.akka" %% "akka-testkit" % "2.5.11" % Test,
  "org.mockito" % "mockito-core" % "2.18.0" % Test,
  "org.scalamock" %% "scalamock" % "4.1.0" % Test
)
scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature",
  "-language:postfixOps", "-language:reflectiveCalls")

coverageExcludedPackages := "" // CHANGE ME
coverageMinimum := 75
coverageFailOnMinimum := true

coverageEnabled.in(Test, test) := true
scalastyleFailOnError := true
scalastyleFailOnWarning := true