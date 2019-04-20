// `throw` is an expression that has a result type
// an exception throw technically has type `Nothing`
//
import java.io.{FileReader, FileNotFoundException, IOException}

object Exceptions extends App {
  try {
    val f = new FileReader("input.txt")
    // Do stuff with file and close it
  } catch {
    case ex: FileNotFoundException => // Do stuff to handle the missing file
    case ex: IOException =>           // Hand some other I/O error
  }

  // You can wrap an expression wiht a `finally` clause if you want some code to
  // run no matter what happens. E.g.
  val file = new FileReader("input.txt")
  try {
    // Do stuff with file
  } finally {
    file.close() // Close the file!!
  }
}
