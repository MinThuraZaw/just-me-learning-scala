import scala.collection.mutable

object Collections extends App{

  val myList = List.empty[Int]
  val myAB = mutable.ArrayBuffer.empty[String]
  val myMap = Map.empty[Int, String]

  val myList2 = 0 +: myList :+ 1
  println(myList2)

  // Tuples
  val pair1:(Int, String) = 1 -> "Me"
  val pair2:(Int, String) = 2 -> "You"

  val myMap2: Map[Int, String] = Map(pair1, pair2)
  println(myMap2)


}
