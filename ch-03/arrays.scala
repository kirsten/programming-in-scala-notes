// Step 7: ARRAYS
//
// *Mutable* sequence of objects that all share the same type
// You can't change the length of an array after it is instantiated,
// but you can change its element values.

object Chapter3Arrays {
  def main(args: Array[String]): Unit = {
    val greetStrings = new Array[String](3)
    // Array(Hello, null, null)

    // Array is mutable, so its values can be reassigned!
    // Can't assign greetStrings(3) though - that index is out of bounds!
    greetStrings(0) = "Hello"
    greetStrings(1) = ", "
    greetStrings(2) = "world!\n"

    // `0 to 2` is really just `(0).to(2)`
    // If a method takes only 1 parameter, you can call it without a dot or parentheses!
    for (i <- 0 to 2)
      print(greetStrings(i))

      // These both work!
      greetStrings.foreach(print)
      greetStrings foreach print

      // All operations are method calls in Scala
      // These two expressions are the same!
      1 + 2
      (1).+(2)

      // Assignment operator is transformed by the compiler to an invocation of the `update` method
      greetStrings.update(0, "Hello") // is the same as greetStrings(0) = "Hello"
      greetStrings.apply(0)           // is the same as greetStrings(0)

      // A more concise way to initialize an array with strings:
      // Compiler can infer Array[String] because strings were passed to the Array
      val numNumes = Array("zero", "one", "two")

      // This is actually calling a factory method `apply` which is defined in an Array companion object
      // More verbose way of writing line 31:
      val numNames2 = Array.apply("zero", "one", "two")
  }
}
