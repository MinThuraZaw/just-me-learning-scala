import scala.util.control.NonFatal
import scala.util.Try
import scala.util.{Success, Failure}
import java.time.LocalDate

object ErrorHandling extends App {
  try{
    runSomething()
  }
  catch {
    case error: RuntimeException => println("Stop")
    case NonFatal(throwable) => -1

  }
  finally {
    println("end")
  }

  // with Try
  trySomething().recover{
    case e: RuntimeException => println("Stop")
  }

  // matching on Try
  parseDate("2024-01-01") match {
    case Success(date) => println("")
    case Failure(throwable) => println("")
  }


  def runSomething(): Unit = {
    println("start running")
    throw new RuntimeException("Error")
    println("something")
  }

  def trySomething(): Try[Unit] = {
    Try{
      println("start running")
      throw new RuntimeException("Error")
      println("something")
    }
  }

  def parseDate(str: String): Try[LocalDate] = {
    ???
  }

}
