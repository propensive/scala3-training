package repetition

sealed trait Weekday
case object Mon extends Weekday
case object Tue extends Weekday
case object Wed extends Weekday
case object Thu extends Weekday
case object Fri extends Weekday
case object Sat extends Weekday
case object Sun extends Weekday

sealed trait Repetition:
  def annually: Int

case object Once extends Repetition:
  def annually = 1

case class EveryNDays(interval: Int) extends Repetition:
  def annually: Int = 365/interval

case class Weekly(days: Set[Weekday]) extends Repetition:
  def annually: Int = days.size*52

case class Monthly(ordinal: Int, day: Weekday) extends Repetition:
  def annually: Int = 12

