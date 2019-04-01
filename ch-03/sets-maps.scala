// Step 10: SETS AND MAPS
// Scala has both immutable and mutable versions of sets and maps
// uses the same name for both so you need to get the correct import

object Chapter3SetsMaps {
  def main(args: Array[String]): Unit = {
    // Immutable Set is the default
    var jetSet = Set("Boeing", "Airbus")
    // Add a new element to a set with `+`
    // Shorthand for jetSet = jetSet + "Lear"
    // Returns a new Set with the new element added
    // This wouldn't have worked if it was set to `val`
    // This is creating a new Set and reassigning it to the jetSet var.
    jetSet += "Lear"
    println(jetSet.contains("Cessna"))

    // Using the mutable version of set
    val movieSet = scala.collection.mutable.Set("Hitch", "Poltergeist")
    // This adds the element to the set instead of creating a new one.
    movieSet += "Shrek"
    println(movieSet)

    // Initialize a new, empty mutable map. Types are provided because it's empty!
    val treasureMap = scala.collection.mutable.Map[Int, String]()
    // `1 -> "Go to island."` is the same as `1.->("Go to island.")`
    // -> method returns a two-element tuple containing the key and value
    // You can invoke -> on any object in scala!
    treasureMap += (1 -> "Go to island.")
    treasureMap += (2 -> "Find big X on ground.")
    treasureMap += (3 -> "Dig.")
    println(treasureMap(2))

    // Default Map implementation is *immutable*. This time types are inferred from the values passed in
    val romanNumeral = Map(1 -> "I", 2 -> "II", 3 -> "III", 4 -> "IV", 5 -> "V")
    println(romanNumeral(4))
  }
}

