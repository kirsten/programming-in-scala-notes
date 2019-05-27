object Hierarchy extends App {
  // Every class inherits from a common superclass `Any`.
  // At the bottom of the hierarchy, `Null` and `Nothing`
  // act as common *subclasses*.
  //
  // The equality (`==`) and inequality (`!=`) methods are
  // declared `final` in `Any` and cannot be overriden in
  // subclasses.
  //
  // `Any` has two subclasses:
  // 1. `AnyVal`: parent of all *value classes*. Byte, Short,
  //     Char, Int, Long, Float, Double, Boolean, and Unit.
  //     These correspond to Java's primitive types and are
  //     written as literals in Scala. E.g. you cannot create
  //     instances of these classes using `new`.
  //
  //     All value classes subclass `AnyVal`, but not each other.
  //     Value class space is flat! Instead, Scala uses implicit
  //     conversions between different value class types.
  //
  // 2. `AnyRef`: Base class of all *reference classes* in Scala
  //    In Java, `AnyRef` is an alias for `java.lang.Object`. In
  //    Scala `AnyRef` also inherits from a special trait called
  //    `ScalaObject`.
  //
  // Bottom types: `Null` and `Nothing`.
  // `Null` is a type of the `null` reference - it is a subclass
  // of every references class. It is not compatible with value types.
  // `Nothing` is a subtype of every other type (including `Null`).
  // No values of this type exist! It could be used to signal abnormal
  // termination, e.g.:

  def error(message: String): Nothing =
    throw new RuntimeException(message)

  def divide(x: Int, y: Int): Int =
    if (y!= 0) x / y
    // Because `Nothing` is a subtype of `Int`, the type of the condition here
    // is still `Int` as required by the compiler.
    else error("can't divide by zero")
}
