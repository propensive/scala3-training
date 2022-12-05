package extensions

import math.Numeric.Implicits.infixNumericOps

def calcProd[T: Numeric](xs: List[T]): Int = xs.foldLeft(1)(_ * _.toInt)

// def test(): Unit = println(List(2, 8, 5, 3, 12).prod)
