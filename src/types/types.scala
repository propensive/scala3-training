package types

import annotation.targetName

object Checks:

  // Replace every occurrence of ?:? with either =:=, <:< or >:>

  type A = String
  type B = Int
  type C = Exception

/*
  summon[           Any ?:? Nothing           ] // 1
  summon[             A ?:? Nothing           ] // 2
  summon[             B ?:? Any               ] // 3
  summon[             C ?:? Nothing           ] // 4
  summon[       Nothing ?:? AnyRef            ] // 5

  summon[       (A & B) ?:? (B & A)           ] // 6
  summon[       (A & B) ?:? Nothing           ] // 7
  summon[       (A | B) ?:? Any               ] // 8
  summon[       (A & B) ?:? (A | B)           ] // 9
  summon[       (A | C) ?:? (A | B | C)       ] // 10
  summon[   (A & B & C) ?:? (A & B)           ] // 11
  summon[ (A & (B | C)) ?:? (C & A)           ] // 12
  summon[   (A & B & C) ?:? (C & B & C & A)   ] // 13
  summon[ (B | (A & C)) ?:? ((A | B) & (C | B)) ] // 14
*/
// Some simple type definitions
@targetName("unknownRelation")
infix type ?:?[L, R]

@targetName("supertypeOf")
infix type >:>[L, R] = <:<[R, L]


