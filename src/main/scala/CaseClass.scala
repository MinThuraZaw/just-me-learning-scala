
sealed trait Shape
case class Rectangle(width: Int, height: Int) extends Shape
case class Circle(radius: Int) extends Shape

//def area(shape: Shape): Double =
//  shape match {
//    case rectangle: Rectangle => width * height
//    case circle: Circle => radius * radius * 3.14
//  }


//def area(shape: Shape): Double =
//  shape match {
//    case Rectangle(w, h) => w * h
//    case Circle(r) => r * r * 3.14
//  }



