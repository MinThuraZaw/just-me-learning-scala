object Functions extends App {

  val increment = (x:Int) => {
    val result = x + 1
    result
  }

  val renameColumns = (name:String) => name.toLowerCase()

  val endWithDotCom: String => Boolean = _.endsWith(".com")

  val applyDiscount = (totalCost:Double, discount:Double) => totalCost - discount


  val result1 = increment.apply(2)
  println(result1)

  val result2 = endWithDotCom("minthura")
  println(result2)

  val result3 = renameColumns("Name")
  println(result3)

  val result4 = applyDiscount(100, 4)
  println(result4)


}
