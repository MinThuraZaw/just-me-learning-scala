import main.scala._

object SideEffects extends App{

  val gen1 = Generator.init
  val (x, gen2) = gen1.nextInt

  val (y, _) = gen1.nextInt
  println(x, y)


}
