import scala.io.Source

object FunctionValuesLiterals extends App {
  // Define functions inside other functions!
  // Similar to local variables in that they are
  // only visible in their enclosing block.
  def processFile(filename: String, width: Int) {
    def processLine(line: String) {
      // Can also access parameters of enclosing function (filename, width)
      if (line.length > width)
        println(filename + ": " + line)
    }

    val source = Source.fromFile(filename)
    for (line <- source.getLines()) {
      processLine(line)
    }
  }

  // FUNCTION VALUES, LITERALS
  // In Scala, you can write functions as unnamed function literals
  // and pass them around as values.
  var increase = (x: Int) => x + 1
  increase(1)

  val someNumbers = List(-11, -10, -5, 0, 5, 10)
  // Passing an anonymous function as an argument to `foreach`
  someNumbers.foreach((x: Int) => println(x))

  // Passing anon function as argument to collection filter method
  someNumbers.filter((x: Int) => x > 0)
  // List[Int] = List(5, 10)

  // This shorthand (without type) also works because someNumbers is a collection
  // of type `Int`. Compiler can determine that `x` should be an `Int` as well.
  // This is called *target typing*
  // You can also leave off parens around a parameter those type is inferred.
  someNumbers.filter(x => x > 0)

  // PLACEHOLDER SYNTAX
  // You can use underscores as placeholders for parameters, as long as each parameter
  // appears only once in the function literal. Multiple underscores mean multiple
  // parameters - *not* reuse of the same parameter!
  someNumbers.filter(_ > 0)

  // If the compile can't infer the parameter types, you can add them like:
  // val f = _ + _ // won't compile!
  val f = (_: Int) + (_: Int)
  f(5, 10)

  // Partially Applied Functions
  // Replace an entire parameter list with an underscore:
  // shorthand for `someNumbers.foreach(x => println(x))`
  // THIS IS A PLACEHOLDER FOR AN ENTIRE PARAMETER LIST!
  // not a single placeholder
  someNumbers.foreach(println _)

  // Partially applied function where you don't supply all the arguments
  // needed by the function. Instead you supply some args, or none.
  def sum(a: Int, b: Int, c: Int) = a + b + c

  // Store `sum` in a variable using the underscore for placeholder arguments
  // This is a partially applied function. (None of the arguments are supplied.)
  val a = sum _
  // Then the function can be invoked with arguments
  a(1, 2, 3)

  // Partially applied function that includes *some* arguments and 1 placeholder.
  val b = sum(1, _: Int, 3)

  // Behind the scenes, the Scala compiler generates a new function class whose `apply`
  // method takes only 1 argument. When invoked with that argument, the generated function's
  // `apply` method invokes sum, passing in 1, 3, and whatever we supply for the placeholder.
  b(2)
  // so `b.apply` invokes `sum(1, 2, 3)
  //
  // If the partially applied function is required at some point in the code, you can
  // leave the placeholder off completely. Instead of:
  // someNumbers.foreach(println _)
  // just write:
  someNumbers.foreach(println)
}
