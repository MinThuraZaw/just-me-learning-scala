import scala.util.control.NonFatal
import scala.util.Try
import scala.util.{Failure, Success}
import java.time.{LocalDate, Period}
import scala.io.Source

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
    Try{
      LocalDate.parse(str)
    }
  }

  def tryPeriod(str1: String, str2: String): Try[Period] = {
    for {
      date1 <- parseDate(str1)
      date2 <- parseDate(str2)
    }
      yield Period.between(date1, date2)

  }

  println(tryPeriod("2024-01-01", "2024-02-01"))
  println(tryPeriod("2024-01-33", "2024-02-33"))

  // read from file and parse
  def readStringFile(fileName: String): Try[Seq[String]] = {
    Try{
      val source = Source.fromFile(fileName)
      val stringInFile = source.getLines().toSeq
      source.close()
      stringInFile
    }
  }

  val lines = readStringFile("src/main/scala/Sample.txt")
  println(lines match {
    case Success(lines) => println(lines)
  })


}
