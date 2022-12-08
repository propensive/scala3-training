package exceptions

/*
   1. Uncomment the saferExceptions import to turn on exception checking
   2. Fix the compile errors by adding throws clauses to return types
   3. Change the return type of divide to throw UserError and change the implementation to rethrow
      the other exceptions as UserErrors
   4. Add a global contextual CanThrow[UserError] and remove the throws clause from divide
   5. Add an appropriate try/catch to the share method and remove the throws clause from share
*/

//import scala.language.experimental.saferExceptions

case class UserError(msg: String) extends Exception
case class UnevenSplit() extends Exception
case class NotEnoughGifts() extends Exception

def checkEnough(people: Int, gifts: List[String]): Unit =
  if gifts.length < people then throw NotEnoughGifts()

def checkEqualSplit(people: Int, gifts: List[String]): Unit =
  if gifts.length%people != 0 then throw UnevenSplit()

def divide(people: Int, gifts: List[String]): Map[Int, List[String]] =
  checkEnough(people, gifts)
  checkEqualSplit(people, gifts)
  gifts.grouped(people).zipWithIndex.map(_.swap).to(Map)

@main
def share(people: Int, gifts: String*): Unit =
  divide(people, gifts.to(List)).foreach { (idx, gifts) =>
    println(s"$idx: ${gifts.mkString(", ")}")
  }
