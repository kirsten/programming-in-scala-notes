import scala.io.Source

def widthOfLength(s: String) = s.length.toString.length

// Check to ensure at least 1 argument was passed in.
if (args.length > 0) {

  // Use first arg as filename. Open the file and return a `Source` object which we call `getLines()` on
  // `getLines` returns an `Iterator[String]`

  // We call toList because once the list has been iterated through you can't do it again.
  val lines = Source.fromFile(args(0)).getLines().toList

  val longestLine = lines.reduceLeft(
    (a, b) => if (a.length > b.length) a else b
  )

  val maxWidth = widthOfLength(longestLine)

  for (line <- lines) {
    val numSpaces = maxWidth - widthOfLength(line)
    val padding = " " * numSpaces
    println(padding + line.length + " | " + line)
  }
}
else
  Console.err.println("Please enter filename")

