object Methods {

  def showPrice(paintArea: Double, paintPrice: Double): String = {
    val price = paintArea * paintPrice
    if (price > 100){
      "This is too expensive"
    }else {
      price.toString
    }

  }

}
