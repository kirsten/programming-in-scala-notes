// Patterns in variable definitions

// Deconstructing tuples
val myTuple = (123, "abc")
val (number, string) = myTuple
// > number: Int = 123
// > string: String = abc

// Deconstructing case classes
abstract class Expr
case class Var(name: String) extends Expr
case class Number(num: Double) extends Expr
case class UnOp(operator: String, arg: Expr) extends Expr
case class BinOp(operator: String, left: Expr, right: Expr) extends Expr

val exp = new BinOp("*", Number(5), Number(1))
val BinOp(op, left, right) = exp
// > op: String = *
// > left: Expr = Number(5.0)
// > right: Expr = Number(1.0)

// Partial Functions
// A sequence of cases (alternatives) in curly braces can be used anywhere
// a function literal can be used. A case sequence is essentially a function literal.
val withDefault: Option[Int] => Int = {
  case Some(x) => x
  case None => 0
}

withDefault(Some(5))
// res6: Int = 5

withDefault(None)
// res7: Int = 0
//
// Try to work with complete (not partial) functions - partial functions allow for
// runtime errors that the compiler doesn't prevent.

// For expressions!
val capitals = Map("France" -> "Paris", "Japan" -> "Tokyo")

for ((country, city) <- capitals)
  println("The capital of " + country + " is " + city)
// > The capital of France is Paris
// > The capital of Japan is Tokyo

val results = List(Some("apple"), None, Some("orange"))
for (Some(fruit) <- results) println(fruit) // ignores the `None`!
// > apple
// > orange
