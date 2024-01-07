case class Task(name:String, duration:Int, requirements:List[Task])

object Loops extends App {

  println("Loops")

  def factorial(n:Int):Int = {
    (1 to n).foldLeft(1)((result, x) => result * x)
  }

  def factorial2(n: Int): Int = {
    def factorialTailRec(x: Int, acc: Int): Int = {
      if (x == 0)  acc
      else factorialTailRec(x - 1, x * acc)
    }

    factorialTailRec(n, 1)

  }

  println(factorial(4))
  println((1 to 10))

  var n = 10
  var i = 0
  while (i < n) {
    // println(i)
    i += 1
  }

  val csSetup = Task("cs setup", 4, Nil)
  val ide = Task("ide", 3, Nil)
  val hack = Task("hack", 8, List(csSetup, ide))
  val deploy = Task("deploy", 5, List(hack))

  def totalDuration(task: Task): Int = {
    val reqMaxTotalDuration =
      task.requirements
        .map(totalDuration)
        .maxOption
        .getOrElse(0)

    task.duration + reqMaxTotalDuration

  }

  println(totalDuration(deploy))

  // for loop
  val numbers = List("+9599", "+9594", "+99784", "+66334", "+9611")
  val mmContact: List[String] = {
    for (number <- numbers) {
      if (number.startsWith("+95")) number
    }
  }

  println(mmContact)

}
