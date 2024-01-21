import scala.util.control.NonFatal

object ErrorHandling extends App {
  try{
    runSomething()
  }
  catch {
    case error: RuntimeException => println("error")
    case NonFatal(throwable) => -1

  }
  finally {
    println("end")
  }


  def runSomething(): Unit = {
    println("start running")
    throw RuntimeException
    println("something")
  }

}
