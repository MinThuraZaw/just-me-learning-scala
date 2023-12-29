object Functions extends App {

  val increment = (x:Int) => {
    val result = x + 1
    result
  }

  val endWithDotCom: String => Boolean = _.endsWith(".com")

  val result1 = increment.apply(2)
  println(result1)

  val result2 = endWithDotCom("minthura")
  println(result2)


}
