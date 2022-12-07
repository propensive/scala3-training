package opaquetypes


trait Pack[T]:
  def pack(i1: T, i2: T): Long
  def unpack1(long: Long): T
  def unpack2(long: Long): T

given Pack[Int] with
  def pack(i1: Int, i2: Int): Long = (i1.toLong << 32) + i2
  def unpack1(long: Long): Int = (long >> 32).toInt
  def unpack2(long: Long): Int = long.toInt

given Pack[Char] with
  def pack(i1: Char, i2: Char): Long = (i1.toLong << 32) + i2.toInt
  def unpack1(long: Long): Char = (long >> 32).toChar
  def unpack2(long: Long): Char = long.toChar

object Intervals:
  opaque type Interval[T] = Long
  
  object Interval:
    def apply[T: Pack](i1: T, i2: T): Interval[T] = summon[Pack[T]].pack(i1, i2)

  extension [T](interval: Interval[T])(using pack: Pack[T])
    def get: (T, T) = (pack.unpack1(interval), pack.unpack2(interval))
    //def range: Int = pack.unpack2(interval) - pack.unpack1(interval)



object Elsewhere:
  import Intervals.*

  val interval: Interval[Int] = Interval(10, 20)
  val interval2: Interval[Char] = Interval('a', 'b')