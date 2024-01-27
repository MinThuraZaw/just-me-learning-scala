package wikigraph

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{ExecutionContext, Future, Promise}
import scala.util.{Failure, Success, Try}
import scala.util.control.NonFatal

object Async:

  /**
    * Transforms a successful future value of type `Int` into a
    * successful future value of type `Boolean`, indicating whether
    * the number was even or not.
    *
    * In case the given future value failed, this method should
    * return a failed future with the same error.
    */
  def transformSuccess(eventuallyX: Future[Int]): Future[Boolean] = {
    eventuallyX.transform {
      case Success(x) => Success(x % 2 == 0) // Check if the number is even
      case Failure(exception) => Failure(exception) // Pass along the failure
    }
  }

  /**
    * Transforms a failed future value of type `Int` into a successful
    * one returning `-1`.
    *
    * Any non-fatal failure should be recovered.
    *
    * In case the given future value was successful, this method should
    * return a successful future with the same value.
    */
  def recoverFailure(eventuallyX: Future[Int])(implicit ec: ExecutionContext): Future[Int] = {

    eventuallyX.recover {
      case NonFatal(_) => -1 // Recover from non-fatal failures with -1
    }
  }

  /**
    * Performs two asynchronous computation, one after the other.
    * `asyncComputation2` should start ''after'' the `Future` returned
    * by `asyncComputation1` has completed.
    *
    * In case the first asynchronous computation failed, the second one
    * should not even be started.
    *
    * The returned `Future` value should contain the successful result
    * of the first and second asynchronous computations, paired together.
    */
  def sequenceComputations[A, B](
    asyncComputation1: () => Future[A],
    asyncComputation2: () => Future[B]
  )(implicit ec: ExecutionContext):  Future[(A, B)] = {
    asyncComputation1().flatMap { result1 =>
      asyncComputation2().map(result2 => (result1, result2))
    }
  }

  /**
    * Concurrently performs two asynchronous computations and pair their
    * successful results together.
    *
    * The two computations should be started independently of each other.
    *
    * If one of them fails, this method should return the failure.
    */
  def concurrentComputations[A, B](
    asyncComputation1: () => Future[A],
    asyncComputation2: () => Future[B])(implicit ec: ExecutionContext): Future[(A, B)] = {
    val future1 = asyncComputation1()
    val future2 = asyncComputation2()

    val combinedFuture: Future[(A, B)] = for {
      result1 <- future1
      result2 <- future2
    } yield (result1, result2)

    // Handle failure of either future
    val resultFuture: Future[(A, B)] = combinedFuture.recoverWith {
      case ex1: Throwable =>
        future2.flatMap(_ => Future.failed(ex1)) // Propagate the failure of the first computation
      case ex2: Throwable =>
        future1.flatMap(_ => Future.failed(ex2)) // Propagate the failure of the second computation
    }

    resultFuture
  }

  /**
    * Makes a chocolate cake.
    *
    * Combine the ingredients (`butter`, `eggs`, `chocolate`, and
    * `sugar`) by using the actions `meltButterWithChocolate`,
    * `mixEverything`, and `bake` to return a `Future[Cake]`.
    */
  def makeCake[Butter, Eggs, Chocolate, Sugar, MeltedButterAndChocolate, CakeDough, Cake](
    butter: Butter,
    eggs: Eggs,
    chocolate: Chocolate,
    sugar: Sugar,
    meltButterWithChocolate: (Butter, Chocolate) => Future[MeltedButterAndChocolate],
    mixEverything: (MeltedButterAndChocolate, Eggs, Sugar) => Future[CakeDough],
    bake: CakeDough => Future[Cake]
  )(implicit ec: ExecutionContext) : Future[Cake] = {
    val meltedButterAndChocolateFuture: Future[MeltedButterAndChocolate] =
      meltButterWithChocolate(butter, chocolate)

    // Mix everything
    val cakeDoughFuture: Future[CakeDough] =
      meltedButterAndChocolateFuture.flatMap(melted => mixEverything(melted, eggs, sugar))

    // Bake the cake
    cakeDoughFuture.flatMap(bake)
  }

  /**
    * Attempts to perform an asynchronous computation at most
    * `maxAttempts` times.
    *
    * In case of failure this method should try again to run the
    * asynchronous computation so that at most `maxAttempts` are
    * eventually performed.
    *
    * Hint: recursively call `insist` in the failure handler.
    */
  def insist[A](asyncComputation: () => Future[A], maxAttempts: Int)(implicit ec: ExecutionContext): Future[A] = {
    if (maxAttempts <= 0) {
      // No more attempts left, return a failed future
      Future.failed(new RuntimeException("Exceeded maximum attempts"))
    } else {
      // Execute the asynchronous computation
      val resultFuture = asyncComputation()

      resultFuture.recoverWith {
        case NonFatal(_) =>
          // In case of failure, recursively call insist with reduced maxAttempts
          insist(asyncComputation, maxAttempts - 1)
      }
    }
  }

end Async
