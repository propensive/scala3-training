
// git clone https://github.com/propensive/scala3-training/
// > sbt
// sbt> compile

package repetition

enum Weekday(longName: String):
  case Mon extends Weekday("Monday")
  case Tue extends Weekday("Tuesday")
  case Wed extends Weekday("Wednesday")
  case Thu extends Weekday("Thursday")
  case Fri extends Weekday("Friday")
  case Sat extends Weekday("Saturday")
  case Sun extends Weekday("Sunday")

enum Repetition:
  case Once
  case EveryNDays(interval: Int)
  case Weekly(days: Set[Weekday])
  case Monthly(ord: Int, day: Weekday)

  def annually: Int = this match
    case Once                 => 1
    case EveryNDays(interval) => 365/interval
    case Weekly(days)         => days.size*52
    case Monthly(ord, day)    => 12
