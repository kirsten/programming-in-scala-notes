import ChecksumAccumulator.calculate

// The `Application` trait declares a `main` method of the right signature
// Object inherits this main method so its usable as a Scala application
// Code between curly braces is collected into a *primary constructor* and is
// executed when the class is initialized.
// You no longer have access to command line args, so if you need that the `Application`
// trait won't work.
// You also need a `main` method if the program is multi-threaded because of JVM stuff.
object FallWinterSpringSummer extends App {
  for (season <- List("fall", "winter", "spring", "summer"))
    println(season + ": " + calculate(season))
}

// NOTE: The book uses the `Application` trait, but this seems to have been changed to the `App`
// trait in a more recent version of Scala.
