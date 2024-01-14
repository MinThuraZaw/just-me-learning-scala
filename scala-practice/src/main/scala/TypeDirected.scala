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
