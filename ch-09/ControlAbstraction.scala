object ControlAbstraction extends App {
  // CURRYING!
  //
  // curried function is applied to multiple arguments lists, not just one
  def curriedSum(x: Int)(y: Int) = x + y

  // The first invocation takes a single parameter x, and returns a function value
  // for the second function. The second function takes the parameter y.
  curriedSum(5)(2)

  // Using placeholder for 2nd parameter list!
  val onePlus = curriedSum(1)_ // no space needed before underscore here
  onePlus(2) // 3


  // "Loan pattern" - a control abstraction that opens a resource and "loans" it to a function.
  // see example about opening/closing files
  def withPrintWriter(file: File)(op: PrintWriter => Unit) {
    val writer = new PrintWriter(file)
    try {
      op(writer)
    } finally {
      writer.close()
    }
  }

  val file = new File("date.txt")
  // Currying gives this a better syntax - function can go second in curly braces. instead of repeated calls with parens.
  // Works because we're only passing in 1 argument (a function)
  withPrintWriter(file) {
    writer => writer.println(new java.util.Date)
  }

  // By-name parameters
  //
  var assertionsEnabled = true
  def myAssert(predicate: () => Boolean) =
    if (assertionsEnabled && !predicate())
      throw new AssertionError
  // Annoying to pass the function literal in!
  myAssert(() => 5 < 3)

  // You can use by-name parameters to leave out the empty parameter list
  // and => symbol from the function literal and still have it work!
  def byNameAssert(predicate: => Boolean) =
    if (assertionsEnabled && !predicate())
      throw new AssertionError
  byNameAssert(5 < 3) // works!
}
