package modules

object HttpServer extends App {

}

trait HttpClient{

  def send() = {
    ???
  }

  final def get(uri: String) = {
    this.send()
  }

}
