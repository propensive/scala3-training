lazy val root = project
  .in(file("."))
  .settings(
    name := "examples",
    description := "Scala 3 Training Examples",
    version := "1.0.0",
    scalaVersion := "3.2.0",
    scalacOptions ++= Seq()
  )

Compile / scalaSource := baseDirectory.value / "src"
