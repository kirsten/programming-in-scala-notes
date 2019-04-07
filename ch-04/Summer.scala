import ChecksumAccumulator.calculate

// run scala application using `main` method.
object Summer {
  def main(args: Array[String]) {
    for (arg <- args)
      println(arg + ": " + calculate(arg))
  }
}
