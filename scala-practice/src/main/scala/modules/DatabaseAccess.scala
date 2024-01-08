package modules

trait DatabaseAccess{

  def getData() = {
    val data = List(1,2,3,4)
    data
  }

}

trait DatabaseLogging{

  def getLogData={
    ???
  }
}
