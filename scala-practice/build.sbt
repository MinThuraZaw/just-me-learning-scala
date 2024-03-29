scalaVersion := "2.12.10"

libraryDependencies ++= List("com.lihaoyi" %% "fansi" % "0.4.0",
  "org.scalameta" %% "munit" % "0.7.29" % Test,
  "io.spray" %%  "spray-json" % "1.3.6",
  "org.scalameta" %% "munit-scalacheck" % "0.7.29" % Test)

testFrameworks += new TestFramework("munit.Framework")

