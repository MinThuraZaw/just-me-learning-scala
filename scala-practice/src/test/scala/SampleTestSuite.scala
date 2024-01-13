import Methods.{add, allCap}

class SampleTestSuite extends munit.FunSuite {

  test("add"){
    val obtained = add(1, 1)
    val expected = 2
    assertEquals(obtained, expected)

  }

  test("all capitalize"){
    val obtained = allCap("meme")
    val expected = "Meme"
    assertEquals(obtained, expected)
  }

}
