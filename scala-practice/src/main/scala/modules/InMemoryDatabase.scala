package modules

class InMemoryDatabase extends DatabaseAccess with DatabaseLogging {
  override def getData(): List[Int] = super.getData()

  override def getLogData: Nothing = super.getLogData
}
