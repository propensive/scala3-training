package extensions

trait Winged:
  def name: String
  def fly(): Unit = println(s"$name is flying")

trait Mammal:
  def name: String
  def milk(): Unit = println(s"feeding young $name with milk")

class Bat() extends Winged, Mammal:
  def name: String = "bat"

