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

  def findSolution(puzzle: Puzzle, actions: Actions, count: Int): (Option[Actions], Int) = {
    val puzzleState = PuzzleState.initialState(puzzle, actions)
    val finalState = runToCompletion(puzzleState)
    if (finalState.completion == PuzzleCompletion.Success) (actions.some, count)
    else {
      ActionsGenerator.next(puzzle, actions) match {
        case None => (None, count)
        case Some(nextActions) => findSolution(puzzle, nextActions, count + 1)
      }
    }
  }

  def findSolution(puzzle: Puzzle): (Option[Actions], Int) = {
    val initialActions = ActionsGenerator.initialState(puzzle)
    findSolution(puzzle, initialActions, 1)
  }

  def run(args: List[String]): IO[ExitCode] = {
    IO {
      val level6Platforms = Map(
        (-1, 1) -> PlatformType.NormalPlatform,
        (-1, 2) -> PlatformType.NormalPlatform,
        (-1, 3) -> PlatformType.NormalPlatform,
        (0, 0) -> PlatformType.StartPlatform,
        (0, 1) -> PlatformType.NormalPlatform,
        (0, 2) -> PlatformType.NormalPlatform,
        (0, 3) -> PlatformType.NormalPlatform,
        (1, 2) -> PlatformType.NormalPlatform,
        (1, 3) -> PlatformType.EndPlatform,
        (1, 4) -> PlatformType.NormalPlatform,
        (2, 2) -> PlatformType.NormalPlatform,
        (2, 3) -> PlatformType.NormalPlatform,
        (2, 4) -> PlatformType.NormalPlatform,
        (3, 2) -> PlatformType.NormalPlatform,
        (3, 3) -> PlatformType.NormalPlatform,
      )

      val level6 = Puzzle(level6Platforms)

      println(findSolution(level6))
    }.as(ExitCode.Success)
  }
}
