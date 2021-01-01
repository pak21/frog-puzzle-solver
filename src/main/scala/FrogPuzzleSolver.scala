import cats.effect.{ExitCode, IO, IOApp}
import cats.implicits._

object FrogPuzzleSolver extends IOApp {
  type Actions = List[((Int, Int), Action)]

  def runToCompletion(puzzleState: PuzzleState): PuzzleState = {
    val nextState = PuzzleState.step(puzzleState)
    nextState.completion match {
      case PuzzleCompletion.Incomplete => runToCompletion(nextState)
      case _ => nextState
    }
  }

  def findSolution(puzzle: Puzzle, actions: Actions): Option[Actions] = {
    val puzzleState = PuzzleState.initialState(puzzle, actions)
    val finalState = runToCompletion(puzzleState)
    if (finalState.completion == PuzzleCompletion.Success) actions.some
    else {
      ActionsGenerator.next(actions) match {
        case None => None
        case Some(nextActions) => findSolution(puzzle, nextActions)
      }
    }
  }

  def findSolution(puzzle: Puzzle): Option[Actions] = {
    val initialActions = ActionsGenerator.initialState(puzzle)
    findSolution(puzzle, initialActions)
  }

  def run(args: List[String]): IO[ExitCode] = {
    IO {
      val level1Platforms = Map(
        (0, 0) -> PlatformType.StartPlatform,
        (0, 1) -> PlatformType.NormalPlatform,
        (0, 2) -> PlatformType.NormalPlatform,
        (1, 2) -> PlatformType.NormalPlatform,
        (2, 2) -> PlatformType.EndPlatform,
      )

      val level1 = Puzzle(level1Platforms)

      println(findSolution(level1).map { _.filter { _._2 != Action.NoAction } })
    }.as(ExitCode.Success)
  }
}
