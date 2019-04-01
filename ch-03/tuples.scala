// Step 9: TUPLES
//
// Immutable but can contain objects of different types.
// Useful if you need to return multiple objects from a method.

object Chapter3Tuples {
  def main(args: Array[String]): Unit = {

    // The type of this typle is inferred by Scala to be `Tuple2[Int, String]`
    // Conceptually you can make tuples up to any length, but the Scala libary 
    // only defines them up to `Tuple22`.
    val pair = (99, "Luftballoons")
    println(pair._1)
    println(pair._2)
  }
}
