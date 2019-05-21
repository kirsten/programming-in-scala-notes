// SEALED CLASSES
// A sealed class cannot have any new subclasses except for the
// ones in the same file. This is useful for pattern matching because
// you only have to worry about subclasses you already know about.
// This could be an alternative to `case _` when there really is no
// default behavior.
//
// If you have a hierarchy of classes intended to be pattern matched
// it's a good idea to make the superclass *sealed*.

sealed abstract class Expr
case class Var(name: String) extends Expr
case class Number(num: Double) extends Expr
case class UnOp(operator: String, arg: Expr) extends Expr
case class BinOp(operator: String, left: Expr, right: Expr) extends Expr

// The method below could result in a `MatchError` and elicits a warning from the compiler:
//
// warning: match may not be exhaustive.
// It would fail on the following inputs: BinOp(_, _, _), UnOp(_, _)
def describeWithWarning(e: Expr): String = e match {
  case Number(_) => "a number"
  case Var(_) => "a variable"
}

// This satisfies the compiler but kind of sucks if you don't except the RuntimeException
def describeWithDefault(e: Expr): String = e match {
  case Number(_) => "a number"
  case Var(_) => "a variable"
  case _ => throw new RuntimeException
}

// If you add the `unchecked` annotation, the exhaustive checking for patterns will be suppressed.
// You can still get a `MatchError` - this isn't safe - but it will suppress compiler warnings...
def describeUnchecked(e: Expr): String = (e: @unchecked) match {
  case Number(_) => "a number"
  case Var(_) => "a variable"
}
