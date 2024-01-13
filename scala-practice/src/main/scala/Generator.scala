package main.scala

class Generator(previous: Int) {

  def nextInt: (Int, Generator) =  {
    val result = previous * 100 + 1
    (result, new Generator(result))

  }

}

object Generator {
  def init: Generator = new Generator(40)
}



