class Generator(previous: Int) {

  def nextInt(): (Int, Generator) =  {
    val result = previous * 100 + 1
    (result, Generator(result))

  }

}

object Generator {
  def apply(i: Int): Generator = ???

  def init(): Generator ={
    Generator(40)
  }
}

