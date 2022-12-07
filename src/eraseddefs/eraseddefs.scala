package eraseddefs

import collection.JavaConverters.*

// These are simple type definitions representing the environment variables "PATH" and "PWD"
type Path
type Pwd

// The `VarName` type relates the types to their names
case class VarName[T](name: String)
given VarName[Path]("PATH")
given VarName[Pwd]("PWD")

object OpaqueEnv:
  opaque type Env[-T] = Map[String, String]
  object Env:
    def apply(): Env[Nothing] = System.getenv.asScala.to(Map)

  extension [T](env: Env[T])
    def in(fn: Env[T] ?=> Unit): Unit = ???

    def get(key: String): String = env(key)
  
    def guarantee[V](using varName: VarName[V]): Env[T | V] =
      if !env.contains(varName.name) then throw Exception("Missing value") else env

import OpaqueEnv.Env

def env[V: VarName](using Env[V]): String = summon[Env[V]].get(summon[VarName[V]].name)

// This should compile
val eg1 =
  val e1: Env[Nothing] = Env()
  val e2: Env[Path] = e1.guarantee[Path]
  val e3: Env[Pwd | Path] = e2.guarantee[Pwd]
  e3.in {
    println(env[Path])
    println(env[Pwd])
  }

def pwdAsFile(using Env[Pwd]): java.io.File =
  java.io.File(env[Pwd])

// // This should also compile
val eg2 =
  Env().guarantee[Pwd].in {
    println(pwdAsFile)
  }

// // This should not compile
/*
val eg3 =
  Env().guarantee[Path].in {
    println(pwdAsFile)
  }
*/
