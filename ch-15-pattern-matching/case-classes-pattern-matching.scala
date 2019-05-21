// Case classes allow us to pattern match on objects without requiring a lot of boilerplate.

abstract class Expr
case class Var(name: String) extends Expr
case class Number(num: Double) extends Expr
case class UnOp(operator: String, arg: Expr) extends Expr
case class BinOp(operator: String, left: Expr, right: Expr) extends Expr

// Case classes automatically have a factory method with the name of the class. Don't need to
// make a companion object to do this.
val v = Var("x") // No need for `new` keyword
val op = BinOp("+", Number(1), v)

// All arguments implicitly get a `val` prefix so they are maintained as immutable fields.
v.name // res3: String = x

// The compiler adds implementations of `toString`, `hashCode`, and `equals` to the class.
println(op)           // BinOp(+,Number(1.0),Var(x))
op.right == Var("x")  // res5: Boolean = true

// Compiler also adds a `copy` method for making modified copies.
// Specify the changes you want using named parameters.
op.copy(operator = "-") // BinOp(-,Number(1.0),Var(x))

// Pattern match includes a sequence of alternatives.
// Each alternative includes a pattern and one or more
// expressions to be evaluated if the pattern matches.
//
// Scala's match expression never "falls through" like switch statements do in Java.
// If no patterns match, a `MatchError` is thrown (this is why the default case is used).
def simplifyTop(expr: Expr): Expr = expr match {
  case UnOp("-", UnOp("-", e))  => e // Double negation
  case BinOp("+", e, Number(0)) => e // Adding zero
  case BinOp("*", e, Number(1)) => e // Multiplying by one
  case _ => expr // Default case
}

// WILDCARD PATTERNS
// Matches any object. No variable bound here.
val wildcard = (expr: Expr) => expr match {
  // Can also be used to ignore parts of the object you don't care about.
  case BinOp(_, _, _) => println(expr + " is a binary operation")
  case _ => println("It's something else")
}

// CONSTANT PATTERNS
// Matches only itself. Use a literal for a constant, or any `val` or
// singleton object.
def describe(x: Any) = x match {
  case 5 => "five"
  case true => "truth"
  case "hello" => "hi!"
  case Nil => "the empty list"
  case _ => "something else"
}

// VARIABLE PATTERNS
// Matches any object, just like a wildcard.
// Scala binds the variable to whatever the object is (unlike the wildcard, which does not bind).
// Simple name starting with a lowercase letter is taken to be variable pattern; all other
// references are taken to be constants.
val variable = (v: Any) => v match {
  case 0 => "zero"
  case somethingElse => "not zero: " + somethingElse
}

// CONSTRUCTOR PATTERNS
// First checks that the object is a member of the named case class,
// then checks that the constructor params match the extra patterns supplied.
val constructorPattern = (expr: Expr) => expr match {
  // Matching on `BinOp` members with the "+" operator and the number zero ONLY
  case BinOp("+", e, Number(0)) => println("a deep match")
  case _ => expr
}

// SEQUENCE PATTERNS
val seqPattern = (a: Any) => a match {
  case List(0, _, _) => println("found it") // Matches a 3-item list where the first element is 0
  case List(0, _*) => println("found this") // Matches a list of any length where the first element is 0
  case _ =>
}

// TUPLE PATTERNS
def tupleDemo(expr: Any) =
  expr match {
    case (a, b, c) => println("matched " + a + b + c) // Matches an arbitrary 3-item tuple
    case _ =>
  }

// TYPED PATTERNS
// See pg. 281-282 re: TYPE ERASURE
def generalSize(x: Any) = x match {
  case s: String => s.length   // Matches any non-null instance of String and binds a variable `s`
  case m: Map[_, _] => m.size  // Matches any instance of Map with arbitrary key/values
  case _ => -1
}

// PATTERN GUARDS
// Pattern guard comes after a pattern and starts with `if`.
// If a pattern guard is present, the match succeeds only if the boolean evaluates to `true`.
def simplifyAdd(e: Expr) = e match {
  // Tip: a pattern variable can only appear once in a pattern.
  case BinOp("+", x, y) if x == y => BinOp("*", x, Number(2))
  case _ => e
}
