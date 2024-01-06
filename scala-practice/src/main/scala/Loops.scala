object Loops extends App {

  println("Loops")

  def factorial(n:Int):Int = {
    (1 to n).foldLeft(1)((result, x) => result * x)
  }


  println(factorial(4))

}
