// Step 8: LISTS
//
// *Immutable* sequence of objects that all share the same type.

object Chapter3Lists {
  def main(args: Array[String]): Unit = {
    // Instantiate a new List[Int]
    val oneTwoThree = List(1, 2, 3)

    val oneTwo = List(1, 2)
    val threeFour = List(3, 4)

    // Concatenate lists with the `:::` method
    // No mutation happening here.
    val oneTwoThreeFour = oneTwo ::: threeFour
    println(oneTwo + " and " + threeFour + " were not mutated.")
    println("Thus, " + oneTwoThreeFour + " is a new List.")

    // "Cons" -> `::` method.
    // Prepends a new element to the beginning of the list and
    // returns the resulting list without mutating.
    // There is an append method `:+`, but the time it takes grows linearly
    // with the size of a list and `::` is constant time.
    val fiveSix = List(5, 6)
    // In `1 :: twoThree`, `::` is a method of the *right* operand `twoThree`.
    // Rule: if a method is used in operator notation, it's invoked on the left operand
    // *unless* the method name ends in a colon.
    // The below could also be written as `twoThree.::(1)`
    val fourFiveSix = 4 :: fiveSix
    println(fourFiveSix)

    // Shorthand way to specify an empty list is `Nil`.
    val sevenEightNine = 7 :: 8 :: 9 :: Nil
    println(sevenEightNine)
    // This works because you can call the cons method on Nil!
    // Nil.::(1) will evaluate to List(1)
    // Nil is an empty List
  }
}
