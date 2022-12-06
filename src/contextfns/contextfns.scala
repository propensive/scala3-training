package contextfns

import collection.JavaConverters.*

// These are simple type definitions representing the environment variables "PATH" and "PWD"
type Path
type Pwd

// The `VarName` type relates the types to their names
case class VarName[T](name: String)
given VarName[Path]("PATH")
given VarName[Pwd]("PWD")

object Env:
  def apply(): Env[Nothing] = Env(System.getenv.asScala.to(Map))

case class Env[Values](map: Map[String, String]):
  def guarantee[T](using varName: VarName[T]): Env[Values] =
    if !map.contains(varName.name) then throw Exception("Missing value") else Env[Values](map)


/*
// This should compile
val eg1 =
  Env().guarantee[Path].guarantee[Pwd].in {
    println(env[Path])
    println(env[Pwd])
  }

def pwdAsFile(using Env[Pwd]): java.io.File =
  java.io.File(env[Pwd])

// This should also compile
val eg2 =
  Env().guarantee[Pwd].in {
    println(pwdAsFile)
  }

// This should not compile
val eg3 =
  Env().guarantee[Path].in {
    println(pwdAsFile)
  }

*/
