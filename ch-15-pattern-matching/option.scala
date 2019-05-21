// OPTION
// Optional values are produced by some of the standard operations in
// Scala's collections, e.g. `Map`.

val capitals = Map("France" -> "Paris", "Japan" -> "Tokyo")

// If a value corresponding to a given key is found, return `Some`
// $ capitals get "France"
// > Some(Paris)

// If the key is not defined in the Map, return `None`
// $ capitals get "Sweden"
// > None

// Common way to take optional values apart - through matching on `Some`/`None`:
def show(x: Option[String]) = x match {
  case Some(s) => s
  case None    => "?"
}
