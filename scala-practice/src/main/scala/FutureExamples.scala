import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

case class User(first_name: String, last_name: String)

object FutureExamples extends App {

  def bcrypt(saltRound: Int, password: String): Future[Seq[Byte]] = {
    ???
  }

  def insertUser(login: String, passwordHash: Seq[Byte]): User = {
    ???
  }

  def hashPasswordAndInsert(name: String, password: String): Future[User] = {
    bcrypt(10, password).map(passwordHash => insertUser(name, passwordHash))
  }

  // println("calling bcrypt")
  // bcrypt(10, "paerendjfer").map(hash => println(s"Result : $hash"))

  val fa = Future(println("A"))
  val fb = Future(println("B"))
  println(fa)
  println(fb)
}

