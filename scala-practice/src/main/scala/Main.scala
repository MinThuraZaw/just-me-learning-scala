import Methods.showPrice
import scala.Console.println

object Main {

  val lowQuantity = 0.3 // MB/s
  val highQuality = 0.6
  val duration = 1800 // seconds
  val dataCenterEnergy = 0.000072

  def main(args: Array[String]): Unit = {
    println("-------------------------------")

    println(showPrice(30.5, 2))

    // business modeling examples
    // the impact of video streaming on environment
    val highQualityAndMobile = Experience(duration, highQuality, Mobile)
    val lowQualityAndFixed = Experience(duration, lowQuantity, Fixed)

    val footprint1 = footprint(highQualityAndMobile)
    val footprint2 = footprint(lowQualityAndFixed)

    println(footprint1)
    println(footprint2)

    // card game - Set
    // val card = Card(Diamond, 1, Red, Solid)
    val deck = List(
      Card(Diamond, 1, Red, Open),
      Card(Squiggle, 2, Purple, Solid),
      Card(Oval, 3, Green, Striped)
    )
    // println(deck)

    println(isValidSet(Card(Diamond, 1, Red, Open),
      Card(Diamond, 3, Purple, Solid),
      Card(Oval, 3, Green, Striped)))


    println("-------------------------------")
  }

  def networkEnergy(network: Network): Double = network match {
    case Fixed => 0.00043
    case Mobile => 0.00088
  }

  def footprint(experience: Experience): Double = {
    val megaBytes = experience.duration * experience.definition
    val energy = dataCenterEnergy + networkEnergy(experience.network)

    megaBytes * energy * 0.5 // kgCO2PerKwh
  }

  def isValidSet(card1: Card, card2: Card, card3: Card): Boolean = {
    // checkShape(card1, card2, card3)
    val result =
      checkProperty(card1.shape, card2.shape, card3.shape) &&
        checkProperty(card1.number, card2.number, card3.number) &&
        checkProperty(card1.color, card2.color, card3.color) &&
        checkProperty(card1.shading, card2.shading, card3.shading)

    result

  }

//  def checkShape(card1: Card, card2: Card, card3: Card): Boolean = {
//    val allSame = card1.shape == card2.shape == card3.shape
//    val allDifferent =
//      card1.shape != card2.shape ||
//        card1.shape != card2.shape ||
//        card2.shape != card3.shape
//
//    allSame || allDifferent
//  }

  def checkProperty(property1: Any, property2: Any, property3: Any): Boolean = {
    val allSame = property1 == property2 == property3
    val allDifferent =
      property1 != property2 &&
        property1 != property3 &&
        property2 != property3

    allSame || allDifferent
  }


}
