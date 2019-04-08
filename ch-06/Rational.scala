class Rational(n: Int, d: Int) {
  // The `require` method is automatically available and allows you to
  // create a *precondition* - a constraint on values passed to a method
  // of constructor. This ensure the denominator is not zero.
  // An exception will be thrown if you try to pass 0 as the denominator.
  require(d != 0)

  // Public fields / initializers
  private val greatestCommonDivisor: Int = gcd(n.abs, d.abs) // `abs` ensures a positive Int value
  val numerator: Int = n / greatestCommonDivisor
  val denominator: Int = d / greatestCommonDivisor

  // Auxiliary constructor for when you're just trying to represent 5/1 and want to
  // write it as `new Rational(5)` without the denominator. Auxiliary constructors
  // start with `def this`...
  // Auxiliary constructors must invoke the constructor of the same class as their first action.
  // This could either be the primary constructor *or* another auxiliary constructor that comes
  // textually before the calling constructor. Eventually the aux constructor will wind up calling
  // the primary constructor.
  def this(n: Int) = this(n, 1)

  // By default, when you create a new instance of this it just
  // prints the class name, @, then hexadecimal number.
  // This is kind of useless and ugly - you can customize the output for logging, etc
  // by overriding `toString`. e.g. if you enter `new Rational(1, 2)` in the console,
  // `Rational@761b5700` becomes `1/2`
  // The `override` modifier is required since this class implements `toString` already!
  override def toString = numerator + "/" + denominator

  // Method to add another rational to this rational and return a new one. (Functional approach
  // instead of mutating internal state).
  // In order to access numerator and denominator on the `that` object, those must be availabe as
  // public fields in the class. E.g. just `that.d` or `that.n` will not compile!
  def + (that: Rational): Rational =
    new Rational(
      // The first value `numerator` could also be expressed as `this.numerator` - `this` refers to the
      // instance of the class. It's not necessary if you have a field to use, but in the event that you
      // need to return the value of the instance or something you should use `this`.
      numerator * that.denominator + that.numerator * denominator,
      denominator * that.denominator
    )

  // Method overloading!!! Compiler picks the version of the overloaded method that correctly
  // matches the types of arguments.
  def + (i: Int): Rational =
    new Rational(numerator + i * denominator, denominator)

  def - (that: Rational): Rational =
    new Rational(
      numerator * that.denominator - that.numerator * denominator,
      denominator * that.denominator
    )

  def - (i: Int): Rational =
    new Rational(numerator - i * denominator, denominator)

  // Expressions using `+` and `*` will have the expected precedence automatically!
  def * (that: Rational): Rational =
    new Rational(numerator * that.numerator, denominator * that.denominator)

  def * (i: Int): Rational =
    new Rational(numerator * i, denominator)

  def / (that: Rational): Rational =
    new Rational(numerator * that.denominator, denominator * that.numerator)

  def / (i: Int): Rational =
    new Rational(numerator, denominator * i)

  // Normalizes Rationals like "66/42" to reduced form of "11/7"
  private def gcd(a: Int, b: Int): Int =
    if (b == 0) a else gcd(b, a % b)
}

// The above above class allows for multiplying a Rational by an Int when the Int is on the right side of the
// expression, but it doesn't work in reverse since Int does not have a multiplication method that works with
// a Rational. To solve this, create an *implicit conversion* that automatically converts integers to rational
// numbers when needed:
//
// implicit def intToRational(x: Int) = new Rational(x)
//
// The implicit conversion needs to be in scope to work, but it won't be in scope if it's defined inside this class.
// This will work if it's added directly in the interpreter though.
