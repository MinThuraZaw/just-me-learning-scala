//object TypeDirected {
//
//  def f(x: Int)(implicit a:A, b:B): Int = {
//    a.value + b.value
//  }
//
//}
//
//class A(val value: Int)
//class B(val value: Int)

trait Show[A] {
  def show(value: A): String
}

object Show {
  // Define implicit instances for Int and String
  implicit val intShow: Show[Int] = new Show[Int] {
    def show(value: Int): String = s"Int: $value"
  }

  implicit val stringShow: Show[String] = new Show[String] {
    def show(value: String): String = s"String: $value"
  }
}

object ContextParameterExample extends App {
  def display[A](value: A)(implicit showInstance: Show[A]): Unit = {
    val representation = showInstance.show(value)
    println(representation)
  }

  val intValue = 42
  val stringValue = "Hello, Scala!"

  // The compiler will implicitly find the appropriate Show instance based on the type.
  display(intValue)    // Output: Int: 42
  display(stringValue) // Output: String: Hello, Scala!

  // summon()
}

// SubTyping
trait Comparable{
  def compareTo(that: Comparable): Int
}

case class Rational(num: Int, denom: Int) extends Comparable{
  override def compareTo(that: Comparable): Int = ???
}

// def sort(List[Comparable]): List[Comparable] = ???

def sort[A](as: List[A])(implicit ordering: Ordering[A]): List[A]

// given definition with regular
implicit val orderingInt: Ordering[Int] = new Ordering[Int] {
  def compare(x: Int, y: Int): Int =
    if (x < y) -1 else if (x > y) 1 else 0
}

// with condition
implicit def orderingPair[A, B](implicit ordA: Ordering[A], ordB: Ordering[B]): Ordering[(A, B)] = new Ordering[(A, B)] {
  def compare(x: (A, B), y: (A, B)) = ???
}