package opaquetypes

def pack(i1: Int, i2: Int): Long = (i1 << 32) + i2
def unpack1(long: Long): Int = (long >> 32).toInt
def unpack2(long: Long): Int = long.toInt

