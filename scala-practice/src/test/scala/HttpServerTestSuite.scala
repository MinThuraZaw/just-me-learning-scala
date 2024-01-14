import modules.HttpServer

class HttpServerTestSuite extends munit .FunSuite {
//  val withHttpServer = FunFixture[HttpServer](){
//    setup => test => {
//      val httpServer = new HttpServer()
//    }
//
//  }

  val httpServer = new HttpServer("test")

  override def beforeAll(): Unit = httpServer.start()
  override def afterAll(): Unit = httpServer.stop()

  test("server is running"){

  }

  test("another test"){

  }




}
