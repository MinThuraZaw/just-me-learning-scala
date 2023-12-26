case class Experience(duration: Int, definition: Double, network: Network)

sealed trait Network
case object Fixed extends Network
case object Mobile extends Network

case class Card(shape: Shape, number: Int, color: Color, shading: Shading)

sealed trait Shape
case object Diamond extends Shape
case object Squiggle extends Shape
case object Oval extends Shape

sealed trait Color
case object Red extends Color
case object Green extends Color
case object Purple extends Color

sealed trait Shading
case object Open extends Shading
case object Striped extends Shading
case object Solid extends Shading




