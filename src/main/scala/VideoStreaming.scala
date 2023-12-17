case class Experience (duration: Int, definition: Double, network: Network)

sealed trait Network
case class Fixed()
case class Mobile()