object ControlStructure extends App {
  // Vocabulary: equational reasoning!
  // The introduced variable is equal to the expression that
  // computes it, assuming that expression has no side effects.

  // if expressions
  val filename =
    if (!args.isEmpty)
      args(0)
    else "default.txt"

  // while loops: they're just like other while loops
  // Also has a do { stuff } while (expression) syntax
  def gcdLoop(x: Long, y: Long): Long = {
    var a = x
    var b = y
    while (a != 0) {
      val temp = a
      a = b % a
      b = temp
    }
    b
  }

  // for expressions
  val filesHere = (new java.io.File(".")).listFiles

  // file <- filesHere syntax is called a generator
  println("simple for expression")
  for (file <- filesHere)
    println(file)

  // filtering:
  println("for expression filtered to only include scala files")
  for (file <- filesHere if file.getName.endsWith(".scala"))
    println(file)

  // multiple filter conditions
  println("for expression wth multiple filters")
  for (
    file <- filesHere
    if file.isFile
    if file.getName.endsWith(".scala")
  ) println(file)

  // Nested iteration
  // multiple <- clauses results in nested loops
  def fileLines(file: java.io.File) =
    scala.io.Source.fromFile(file).getLines().toList

  def grep(pattern: String) =
    // Use curly braces instead of parens here since scala doesn't infer semicolons inside parens!
    for {
      // outer loop
      file <- filesHere
      if file.getName.endsWith(".scala")
      // inner loop
      line <- fileLines(file)
      if line.trim.matches(pattern)
    } println(file + ": " + line.trim)

  grep(".*gcd.*")

  // Producting a new collection using `yield`!
  // Result includes all yielded values in a single collection. Type of resulting collection
  // is based on the kind of collection processed in the iteration.
  // for (n <- 1 to 20) yield n
  // Vector(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20)
  def scalaFiles =
    for {
      file <- filesHere
      if file.getName.endsWith(".scala")
    } yield file

  println(scalaFiles)
}
