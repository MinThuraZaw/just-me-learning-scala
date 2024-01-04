import scala.collection.mutable

case class Contact(name:String, email:Option[String])

object Collections extends App{

  // Constructing
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

  // Querying collection
  val data = List(2,4,5,7,88,97)
  println(data.find(x => x > 50))

  println(data.filter(x => x > 50))

  // Transforming
  // map, flatMap, foldLeft
  val foldLeftresult = data.foldLeft(0)((first, second) => first + second)
  // println("foldLeft")
  // println(foldLeftresult)

  // println(myList2.size)
  // println(myAB.isEmpty)

  val emails = List("member1@gmail.com", "sam@earth.world", "bob@sca.la", "me@earth.world")

  val domain: String => String = (email) => email.dropWhile(c => c != '@').drop(1)
  val emailsByDomain = emails.groupBy(domain)

  // println(emailsByDomain)

  val summerFruit = List("strawberry", "cherry", "raspberry")
  // println(summerFruit.map(str => str.toUpperCase))

  // Sequences
  val seqData = List("Alice" -> 1, "Bob" -> 2)
  println(seqData.sortBy((name, _) => name))

  // Maps
  val mapData = Map("A" -> 0, "B" -> 1, "C" -> 2)
  println(mapData.get("B"))
  println(mapData.get("e"))

  // Option
  // zip
  val contact = Contact("user1", Some("user1@gmail.com"))
  val contact2 = Contact("user2", None)

  def emailLength(contact: Contact): Int =
    contact.email.map(email => email.length).getOrElse(0)

  println(emailLength(contact))
  println(emailLength(contact2))


}
