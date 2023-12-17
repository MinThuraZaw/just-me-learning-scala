case class Experience(duration: Int, definition: Double, network: Network)

sealed trait Network
case object Fixed extends Network
case object Mobile extends Network

case class Card(shape: Shape)

sealed trait Shape
case object Diamond extends Shape
case object Squiggle extends Shape
case object Oval extends Shape

