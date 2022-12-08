package records

/*
   1. Create a new record type called Env.
   2. Create a structural subtype of Env which defines the members path and pwd, both Strings.
   3. Make Env extend Selectable and implement selectDynamic to call System.getenv (remembering that
      environment variables are uppercase).
*/

class Env() extends Selectable:
  def selectDynamic(name: String): Any = System.getenv(name.toUpperCase)

type MyEnv = Env {
  def path: String
  def pwd: String
  def foo: String
}

@main def test(): Unit = println(Env().asInstanceOf[MyEnv].foo)