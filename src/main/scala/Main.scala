import Methods.showPrice

sealed trait Network
case object Fixed extends Network
case object Mobile extends Network

object Main {

  val lowQuantity = 0.3 // MB/s
  val highQuality = 0.6
  val duration = 1800 // seconds
  val dataCenterEnergy = 0.000072

  def main(args: Array[String]): Unit = {
    println("-------------------------------")

    println(showPrice(30.5, 2))

    val highQualityAndMobile = Experience(duration, highQuality, Mobile)
    val lowQualityAndFixed = Experience(duration, lowQuantity, Fixed)

    println(footprint(highQualityAndMobile))
    println(footprint(lowQualityAndFixed))


    println("-------------------------------")
  }

  def networkEnergy(network: Network): Double = network match {
    case Fixed => 0.00043
    case Mobile => 0.00088
  }

  def footprint(experience: Experience): Double = {
    val megaBytes = experience.duration * experience.definition
    val energy = dataCenterEnergy * networkEnergy(experience.network)

    megaBytes * energy * 0.5 // kgCO2PerKwh
  }

}
