package greeting

import scala.util.*

object greet:
  def main(args: Array[String]): Unit =
    println("Hello world")


enum Day:
  case Mon, Tue, Wed, Thu, Fri, Sat, Sun

given CommandLineParser.FromString[Day] = str => Day.fromOrdinal(str.toInt)

@main
def run(day: Day): Unit = println(day.toString)
