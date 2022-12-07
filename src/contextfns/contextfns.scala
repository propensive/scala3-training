/*
Convert the Env type to an opaque type on Map[String, String]
Create a method on Env called in, whose only parameter is a context function providing a “safe” 
Env instance
An Env which we know contains PATH and PWD should combine a set of types like Path and Pwd somehow
in its type parameter. Decide how, and choose what variance Env’s type parameter should have to
accommodate this
Provide a method called env which can be called only inside the in block which takes a type
parameter (no value parameters) to extract the appropriate variable from the environment
Make sure the env method cannot be called unsafely
Check the commented-out examples to see what should compile successfully or not.
*/


package contextfns

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
/*val eg3 =
  Env().guarantee[Path].in {
    println(pwdAsFile)
  }
*/
