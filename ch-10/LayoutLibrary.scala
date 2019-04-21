// Without an `extends` clause, compiler assumes your class inherits from `AnyRef`.
abstract class Element {
  // No `abstact` modifier is needed for this method. The lack of body
  // means it is abstract. (Methods that have an implementation are *concrete*.)
  //
  // declaration vs. definition - this is only a declaration and has no definition.
  def contents: Array[String]

  // `contents`, `height`, and `width` are a *parameterless method*. Recommended
  // practice is to use parameterless methods (no parens!) when there are no
  // parameters and the method accesses mutable state only by reading fields of
  // the containing object.
  // If the method performs I/O, writes mutable state, or has any side effects, you
  // should use empty parens `()` even if the method has no parameters.
  // The point of this is that you might decide to change the internal implementation
  // from a field to a method, but the client doesn't have to care if you leave off parens.
  // It's technically possible to leave off all empty parens in Scala, but it's recommended
  // to do so only in special circumstances.
  def height: Int = contents.length
  def width:  Int = if (height == 0) 0 else contents(0).length

  def above(that: Element): Element =
    // `++` operator concatenates 2 arrays
    new ArrayElement(this.contents ++ that.contents)

  def beside(that: Element): Element =
    new ArrayElement(
      for (
        // `zip` creates a new array of pairs (tuples)
        // pattern match on the pair in the `for` expression
        (line1, line2) <- this.contents zip that.contents
      ) yield line1 + line2
    )

  def toString = contents mkString "\n"
}

// `ArrayElement` is a subclass of `Element`
// It inherits all non-private members from `Element`, and makes `ArrayElement` a subtype of `Element`
//
// Member of a superclass is not inherited if a member of with the same name and parameters is already
// implemented in the subclass. If that's the case, the subclass *overrides* the member of the superclass.
// If the member in the subclass is concrete and the member of the superclass is abstract, we say the
// subclass *implements* implements the abstract method.
//
// *parametric field* - combines parameter and field in a single definition - you can also include
// `override`, `private`, or `protected` (or use `var`)
// Implement `contents` via a *parametric field*:
class ArrayElement(val contents: Array[String]) extends Element {
  // Implements the abstract `contents` method. (Inherits `width` and `height` from abstract superclass.)
  // def contents: Array[String] = conts
  // Fields and methods belong to the same namespace, so we can also implement/override an abstract
  // method with `val`:
  // val contents: Array[String] = conts
}

// *subtyping*
// The value of the subclass can be used wherever a value of the superclass is required!
// val e: Element = new ArrayElement(Array("hi"))

// To invoke superclass constructor, use parents/params following the name of the superclass.
// class LineElement(s: String) extends ArrayElement(Array(s)) {
class LineElement(s: String) extends Element {
  val contents = Array(s)
  // The `override` modifier is required for all members that override a concrete member in a parent class.
  override def width =  s.length
  override def height = 1
}

class UniformElement(
  ch: Char,
  override val width: Int,
  override val height: Int
) extends Element {
  private val line = ch.toString * width
  def contents = Array.fill(height)(line)
}

// Method invocations are *dynamically bound* - method implementation invoked is determined at run time
// based on the class of the object, not the type of the variable/expression.
// *Subtyping polymorphism*
// val e1: Element = new ArrayElement
// val ae: ArrayElement = new LineElement
// val e2: Element = ae
// val e3: Element = new UniformElement

// `final` members!
// Use the `final` modifier when you don't want a member to be overridden by a subclass.
// E.g. If you defined `ArrayElement` like `final class ArrayElement extends Element`, then the
// subclassed `LineElement` would not compile. Also works for methods / fields.

object Element {
  def elem(contents: Array[String]): Element =
    new ArrayElement(contents)

  def elem(chr: Char, width: Int, height: Int): Element =
    new UniformElement(chr, width, height)

  def elem(line: String): Element =
    new LineElement(line)
}