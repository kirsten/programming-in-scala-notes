import scala.collection.mutable.Map

class ChecksumAccumulator {
  // Give class a var field `sum`
  // aka instance variable

  // Default for fields is *public*
  var foo = 0

  // Private means fields can only be accessed by methods in the
  // same class, which means state is localized to the class:
  private var sum = 0

  // Methods with a result type of `Unit` are executed for their side effects.
  // This one mutates state.
  def add(b: Byte): Unit = sum += b

  // Avoid `return` in Scala - you don't need it. Make methods small / return simple expressions if possible!
  def checksum(): Int = ~(sum & 0xFF) + 1
}

// *Companion object* for the class above.
// Companion objects must be defined in the same file as their *companion class*.
// A class and its companion object can access each other's private members (e.g. `sum`)
// Objects, unlike classes, can't take any parameters.
object ChecksumAccumulator {
  private val cache = Map[String, Int]()

  def calculate(s: String): Int =
    if (cache.contains(s))
      cache(s)
    else {
      val acc = new ChecksumAccumulator
      for (c <- s)
        acc.add(c.toByte)
        val cs = acc.checksum()
        cache += (s -> cs)
        cs
    }
}

// Singleton objects that don't have companion classes are called
// *standalone objects*.

// Create 2 new instances of `ChecksumAccumulator`
// `sum` will be 0 at this point
// val acc = new ChecksumAccumulator
// val csa = new ChecksumAccumulator

// Since `foo` is a `var`, it can be reassigned. This works even tho `acc` is a `val`.
// You can't reassign `acc`, however. `acc` will always refer the same instance of
// `ChecksumAccumulator`, but the fields inside it may change over time.
// acc.foo = 3

// If you leave off the equals sign before the body of a function, its return value will always be `Unit`
// Return type will be `Unit` if the function declares that, even if that's not what the function returns. E.g.
// def f(): Unit = "this String gets lost"
// def g(): { "this String gets lost to" }

