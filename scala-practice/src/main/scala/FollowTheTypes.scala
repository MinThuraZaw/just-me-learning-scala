class FollowTheTypes extends App {
  def findFirstRowWithEvenValues(rows: Vector[Vector[Int]]): Option[Seq[Int]] =
    rows.find(row => row.forall(x => x % 2 == 0))

}
