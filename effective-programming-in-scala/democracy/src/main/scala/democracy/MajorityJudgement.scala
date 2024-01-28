package democracy

/**
 * A grade to assign to a candidate. There are seven possible grades (from
 * the worst to the best): `Bad`, `Mediocre`, `Inadequate`, `Passable`, `Good`,
 * `VeryGood`, and `Excellent`.
 *
 * Grades can be compared by using their `ordinal` method:
 *
 * {{{
 *   Grade.Mediocre.ordinal < Grade.Good.ordinal
 * }}}
 */
enum Grade:
  case Bad, Mediocre, Inadequate, Passable, Good, VeryGood, Excellent

object Grade:
  /**
   * @return The median grade of a collection of grades.
   *
   * The median grade can be computed by sorting the collection
   * and taking the element in the middle. If there are an even
   * number of grades, any of the two grades that are just before
   * or after the middle of the sequence are correct median values.
   *
   * Grades can be compared by using their `ordinal` method.
   *
   * Hints: use the following operations:
   * - `sortBy` and `ordinal` to sort the collection of grades,
   * - `size` to compute the number of elements,
   * - `apply` to select an element at a specific index.
   */
  def median(grades: Seq[Grade]): Grade =
    grades.sortBy(_.ordinal).apply(grades.size / 2)
end Grade

/**
 * A candidate in an election.
 * @param name (unique) name of the candidate (e.g., “Barack Obama”)
 */
case class Candidate(name: String)

/**
 * A ballot, which assigns a grade to each candidate of an election.
 * @param grades The grades assigned to each candidate
 */
case class Ballot(grades: Map[Candidate, Grade])

/**
 * An election is defined by a simple description and a set of possible
 * candidates.
 * @param description  Description of the election (e.g., “Presidential Election”)
 * @param candidates Possible candidates
 */
case class Election(description: String, candidates: Set[Candidate]):
  /**
   * @return The candidate that wins this election, according to the Majority
   *         Judgement voting process.
   *
   * @param ballots The ballots for this election
   *
   * The ballots ''must'' assign a grade to each of the `candidates` of this
   * election.
   */
  def elect(ballots: Seq[Ballot]): Candidate =
    assert(ballots.nonEmpty)
    assert(ballots.forall(_.grades.keySet == candidates))

    val allGrades: Seq[(Candidate, Grade)] =
      ballots.flatMap(_.grades.map((candidate,grade) => (candidate,grade)))

    val gradesPerCandidate: Map[Candidate, Seq[Grade]] =
      allGrades.groupMap(_._1)(_._2)

    findWinner(gradesPerCandidate)
  end elect

  def findWinner(gradesPerCandidate: Map[Candidate, Seq[Grade]]): Candidate =

    if gradesPerCandidate.forall((candidate, grades) => grades.isEmpty) then {
      val candidatesSeq = gradesPerCandidate.keys.toSeq
      val randomIndex = util.Random.between(0, candidatesSeq.size)
      candidatesSeq(randomIndex)
    }
    else {

      val bestMedianGrade: Grade =
        gradesPerCandidate
          .values
          .filter(_.nonEmpty)
          .map(grade => Grade.median(grade))
          .maxBy(_.ordinal)

      val bestCandidates: Map[Candidate, Seq[Grade]] =
        gradesPerCandidate
          .filter((_, listGrade) =>
            Grade.median(listGrade) == bestMedianGrade)

      if bestCandidates.size == 1 then
        bestCandidates.head._1
      else
        val bestCandidatesMinusOneMedianGrade: Map[Candidate, Seq[Grade]] =
          bestCandidates.map((candidate, grade) =>
            candidate -> grade.diff(List(bestMedianGrade)))

        findWinner(bestCandidatesMinusOneMedianGrade)
    }

  end findWinner

end Election
