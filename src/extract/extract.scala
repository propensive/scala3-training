package extraction

/*
   Write an extractor object called ClosedRange with two type singleton integer type parameters (From
   and To) which will match an integer if and only if the integer is between From and To (inclusive),
   for example,

   def roughScore(i: Int): String = i match
     case ClosedRange[1, 5](x)  => “low”
     case ClosedRange[6, 10](x) => “high”
*/

object ClosedRange:
  def unapply(value: Int): Option[Int] = ???
