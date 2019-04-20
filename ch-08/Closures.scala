object Closures extends App {
  var more = 1
  // `more` is a "free variable" because the function does not
  // give it any meaning. `x` is a "bound variable" - it does have meaning
  // in the context of the function literal.
  (x: Int) => x + more

  // closure: "closing" the function literal by "capturing" the bindings of
  // its free variables.

  // Function below is a *closed term* - it's already closed as written.
  (x: Int) => x + 1

  // This one is *open term* - it includes free variables
  // It requires that a binding for its free variable be captured.
  (x: Int) => x +  more

  // Scala's closures capture variables themselves, not the values to which they refer.
  // Changes made by a closer to a captured variable are visible outside the closure. E.g.:
  val someNumbers = List(-11, -10, -5, 0, 5, 10)
  var sum = 0
  someNumbers.foreach(sum += _)
  // The value is `sum` is now -11!
}

