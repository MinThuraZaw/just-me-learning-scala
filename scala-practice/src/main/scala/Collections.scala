import scala.collection.mutable

object Collections extends App{

  val myList = List.empty[Int]
  var myAB = mutable.ArrayBuffer.empty[String]
  val myMap = Map.empty[Int, String]

  val myList2 = 0 +: myList :+ 1
  println(myList2)

  myAB = myAB :+ "hello"
  println(myAB :+ "hello")

  // Tuples
  val pair1:(Int, String) = 1 -> "Me"
  val pair2:(Int, String) = 2 -> "You"

  val myMap2: Map[Int, String] = Map(pair1, pair2)
  println(myMap2)


  def euclideanDivision(dividend: Int, divisor: Int): (Int, Int) =
    val quotient = dividend / divisor
    val remainder = dividend % divisor
    (quotient, remainder)

  val result = euclideanDivision(10,3)
  print(result(0))

  val (q,r) = euclideanDivision(10,4)
  println(r)


}
