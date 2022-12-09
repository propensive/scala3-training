package priority

import compiletime.summonFrom

trait Size[T]:
  def size(value: T): Int

given Size[Set[Int]] = _.sum

trait Length[T]:
  def length(value: T): Int

def getSize[T](value: T): Int = ???

@main def run(): Unit =
  println(getSize(Set(1, 2, 3)))
  println(getSize(List("a", "b", "c")))