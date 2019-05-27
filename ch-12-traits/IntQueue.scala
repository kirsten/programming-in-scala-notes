// Traits as stackable modifications:
// Doubling
// Incrementing
// Filtering

// FIFO
abstract class IntQueue {
  def get(): Int
  def put(x: Int)
}

import scala.collection.mutable.ArrayBuffer

class BasicIntQueue extends IntQueue {
  private val buf = new ArrayBuffer[Int]

  // Removes the first item from the queue
  def get() = buf.remove(0)

  // Appends an item to the queue
  def put(x: Int) { buf += x }
}

// Extending `IntQueue` means this trait can only be mixed into a class that
// also extends `IntQueue`, e.g. `BasicIntQueue`
trait Doubling extends IntQueue {
  // A `super` call in an abstract method would fail in a class, but since
  // `super` is dynamically bound in a trait, this can work... AS LONG AS the
  // trait is mixed in *after* another trait or class that gives a concrete
  // definition to the method.
  abstract override def put(x: Int) { super.put(2 * x) }
}

trait Incrementing extends IntQueue {
  abstract override def put(x: Int) { super.put(x + 1) }
}

trait Filtering extends IntQueue {
  abstract override def put(x: Int) {
    if (x >= 0) super.put(x)
  }
}

class DoubleQueue extends BasicIntQueue with Doubling
class IncrementingQueue extends BasicIntQueue with Incrementing
class FilteringQueue extends BasicIntQueue with Filtering

// The order of mixins matters! Traits further to the right generally take
// effect first.
class MyQueue extends BasicIntQueue with Filtering with Incrementing

// This involves linearization of all inherited classes and traits. See pg. 232
// for more detailed info on this behavior.
