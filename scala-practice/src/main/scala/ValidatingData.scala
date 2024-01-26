import scala.util.Try

object ValidatingData extends App {
  type Errors = Seq[String]
  type validated[A] = Either[Errors, A]

  val validatedInt: validated[Int] = Right(40)
  println(validatedInt match {
    case Right(n) => n
  })

  def validateBoth[A,B](validatedA: validated[A], validatedB: validated[B]): validated[(A,B)] = {
    (validatedA, validatedB) match {
      case (Right(a), Right(b)) => Right(a,b)
      case (Left(e), Right(_)) => Left(e)
      case (Right(_), Left(e)) => Left(e)
      case (Left(e1), Left(e2)) => Left(e1 ++ e2)
    }
  }

  // all available in third-party libraries

  // combining Try and Either
//  def readDateString(fileName: String): Try[Seq[String]] = {
//    Using()
//  }


}
