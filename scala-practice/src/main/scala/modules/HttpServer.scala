package modules

case class HttpServer(url:String) {

  def start():Unit = ???
  def stop():Unit = ???

}

trait HttpClient{

  def send() = {
    ???
  }

  final def get(uri: String) = {
    this.send()
  }

}
