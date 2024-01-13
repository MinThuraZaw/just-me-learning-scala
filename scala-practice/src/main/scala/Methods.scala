object Methods {

  def showPrice(paintArea: Double, paintPrice: Double): String = {
    val price = paintArea * paintPrice
    if (price > 100){
      "This is too expensive"
    }else {
      price.toString
    }

  }

  val add = (x:Int, y:Int) => x + y

  def allCap(str:String): String = {
    str.capitalize
  }


}
