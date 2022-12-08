package tmatch

/*
   Write the match type `Hash` to compute the Java hashCode of a lowercase String at compiletime.
   Use the `Ascii` match type provided.
*/

import scala.compiletime.ops.int.{`+`, `*`}
import scala.compiletime.ops.string.{Substring, Length, CharAt}

type Ascii[C <: Char] = C match
  case ' ' => 32
  case 'a' => 97
  case 'b' => 98
  case 'c' => 99
  case 'd' => 100
  case 'e' => 101
  case 'f' => 102
  case 'g' => 103
  case 'h' => 104
  case 'i' => 105
  case 'j' => 106
  case 'k' => 107
  case 'l' => 108
  case 'm' => 109
  case 'n' => 110
  case 'o' => 111
  case 'p' => 112
  case 'q' => 113
  case 'r' => 114
  case 's' => 115
  case 't' => 116
  case 'u' => 117
  case 'v' => 118
  case 'w' => 119
  case 'x' => 120
  case 'y' => 121
  case 'z' => 122

def hashCode(str: String, h: Int = 0): Int =
  if str.isEmpty then h else hashCode(str.tail, 31*h + str.head)

//type Hash[S <: String, H <: Int] <: Int = ???
