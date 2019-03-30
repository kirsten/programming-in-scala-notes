// Run this file with `scala ch-01-repl.scala`

// Prints a string
println("Hello, world, from a script!")

// Use additional args passed to script
// $ scala ch-01-repl.scala Kirsten
println("Hello, " + args(0) + "!")

// Loop over args in dumb way
// $ scala ch-01-repl.scala are we there yet
var i = 0
while (i < args.length) {
  print(args(i))
  print(" ")
  i += 1
}

// Use `forEach` to loop over args
args.foreach(arg => println(arg))

// This also works!
args.foreach(println)

// Using a for expression
for (arg <- args) println(arg)
