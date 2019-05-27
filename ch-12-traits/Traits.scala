// A trait encapsulates method and field definitions, which can then be reused
// by mixing them into classes.
// A class can mix in any number of traits!
// Main ways they are useful:
// - Widening thin interfaces to rich ones
// - Defining stackable modifications
//
// Traits definitions can do anything class definitions can do, except have
// class parameters.
// In classes, `super` calls are statically bound, while in traits they are
// dynamically bound. You know exactly what implementation of `super.toString`
// will be invoked when you write it in a class. In a trait, the implementation
// is determined each time the trait is mixed into a concrete class.

// Does not declare a subclass (though it could), so this subclasses `AnyRef`.
trait Philosophical {
  // Concrete method
  def philosophize() {
    println("I consume memory, therefore I am!")
  }
}

// "Mix in" trait using `extends` keyword.
// `Frog` implicitly inherits the traits superclass here.
class Frog extends Philosophical {
  override def toString = "green"
}

// scala> val frog = new Frog
// > frog: Frog = green
// scala> frog.philosophize
// > I consume memory, therefore I am!
//
// Traits also define types. Variable below `phil` can be initialized with any
// object whose class mixes in `Philosophical`.
//
// scala> val phil: Philosophical = frog
// > phil: Philosophical = green
// scala> phil.philosophize
// > I consume memory, therefore I am!

class Animal

// Mix in a trait to a class that already extends a superclass:
class Zebra extends Animal with Philosophical {
  override def toString = "black and white"

  // You can override methods from the trait, just the same as a superclass.
  override def philosophize() {
    println("It ain't easy being " + toString + "!")
  }
}

// This vs. rich interfaces
// Major advantage of traits is that you can automatically add methods to a
// class in terms of the methods the class already has. This turns a thin
// interface into a rich one.
//
// Rich interfaces have many methods, which make them convenient for the
// caller.  Thin interfaces have fewer methods are are easier for implementers.
// The tradeoff being that a client calling a thin interface has to write more
// code.

// `Ordered` trait
// Mix in the `Ordered` trait, using a type parameter.
class Rational(n: Int, d: Int) extends Ordered[Rational] {
  val numer = 1 // Fakey fake
  val denom = 1

  // Implement the compare method, then the ordered trait defines `<`, `>`,
  // `<=`, and `>=` in terms of this one method.
  def compare(that: Rational) =
    // Compare should return an integer that is zero if the objects are the
    // same, negative if the receiver is less than the argument, and positive if
    // the receiver is greater than the argument.
    (this.numer * that.denom) - (that.numer * this.denom)

}
