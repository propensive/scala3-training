package fntypes
/*
   1. Write an explicit return type for join.
   2. Implement join without changing its signature.
   3. Rewrite join as a function value.
*/

trait Join[T]:
  type Result
  def apply(values: List[T]): Result

def join[T](values: List[T])(using Join[T]) = ???
