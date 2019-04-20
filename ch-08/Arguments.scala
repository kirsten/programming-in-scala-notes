object Arguments extends App {
  // Repeated parameters!
  //
  // The * symbol indicates the last parameter can be repeated.
  // The type of repeated parameter winds up being `Array` of
  // whatever the type is.
  def echo(args: String*) =
    for (arg <- args) println(arg)

  // both work fine
  echo("Hi")
  echo("Hi", "what's", "up", "?")

  val words = Array("this", "works", "too")
  // you can't just do echo(array) - will result in a compiler error
  // the _* symbol tells the compiler to provide each element of the
  // array to `echo` as its own argument
  echo(words: _*)

  // Named arguments
  def speed(distance: Float, time: Float): Float = distance / time
  speed(100, 10)
  speed(time = 10, distance = 100)

  // Default parameter values
  def printTime(out: java.io.PrintStream = Console.out, divisor: Int = 1) =
    out.println("time = " + System.currentTimeMillis()/divisor)

  printTime()
  printTime(out = Console.err)
  printTime(divisor = 5)
}
