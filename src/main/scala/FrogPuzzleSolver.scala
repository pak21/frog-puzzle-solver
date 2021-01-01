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

  def findSolution(puzzle: Puzzle, actions: Actions, count: Int, moves: Int): (Option[Actions], Int, Int) = {
    val puzzleState = PuzzleState.initialState(puzzle, actions)
    val finalState = runToCompletion(puzzleState)
    val nextMoves = moves + finalState.moves
    if (finalState.completion == PuzzleCompletion.Success) (actions.some, count, nextMoves)
    else {
      ActionsGenerator.next(puzzle, actions) match {
        case None => (None, count, nextMoves)
        case Some(nextActions) => findSolution(puzzle, nextActions, count + 1, nextMoves)
      }
    }
  }

  def findSolution(puzzle: Puzzle): (Option[Actions], Int, Int) = {
    val initialActions = ActionsGenerator.initialState(puzzle)
    findSolution(puzzle, initialActions, 1, 0)
  }

  private val level1Platforms = Map(
    (0, 1) -> PlatformType.NormalPlatform,
    (0, 2) -> PlatformType.NormalPlatform,
    (1, 2) -> PlatformType.NormalPlatform,
    (2, 2) -> PlatformType.EndPlatform,
  )

  private val level2Platforms = Map(
    (0, 1) -> PlatformType.NormalPlatform,
    (0, 2) -> PlatformType.NormalPlatform,
    (1, 2) -> PlatformType.NormalPlatform,
    (2, 2) -> PlatformType.NormalPlatform,
    (2, 3) -> PlatformType.NormalPlatform,
    (2, 4) -> PlatformType.EndPlatform,
  )

  private val level3Platforms = Map(
    (0, 1) -> PlatformType.NormalPlatform,
    (0, 2) -> PlatformType.NormalPlatform,
    (0, 3) -> PlatformType.NormalPlatform,
    (1, 2) -> PlatformType.NormalPlatform,
    (1, 3) -> PlatformType.NormalPlatform,
    (2, 2) -> PlatformType.NormalPlatform,
    (2, 3) -> PlatformType.NormalPlatform,
    (2, 4) -> PlatformType.EndPlatform,
  )

  private val level4Platforms = Map(
    (-1, 1) -> PlatformType.NormalPlatform,
    (-1, 2) -> PlatformType.NormalPlatform,
    (-1, 3) -> PlatformType.NormalPlatform,
    (0, 1) -> PlatformType.NormalPlatform,
    (0, 2) -> PlatformType.NormalPlatform,
    (0, 3) -> PlatformType.NormalPlatform,
    (1, 1) -> PlatformType.NormalPlatform,
    (1, 2) -> PlatformType.NormalPlatform,
    (2, 1) -> PlatformType.EndPlatform,
  )

  private val level5Platforms = Map(
    (-1, 2) -> PlatformType.NormalPlatform,
    (-1, 3) -> PlatformType.NormalPlatform,
    (0, 1) -> PlatformType.NormalPlatform,
    (0, 2) -> PlatformType.NormalPlatform,
    (0, 3) -> PlatformType.NormalPlatform,
    (0, 4) -> PlatformType.EndPlatform,
    (1, 1) -> PlatformType.NormalPlatform,
    (1, 2) -> PlatformType.NormalPlatform,
    (1, 3) -> PlatformType.NormalPlatform,
    (2, 1) -> PlatformType.NormalPlatform,
    (2, 2) -> PlatformType.NormalPlatform,
    (2, 3) -> PlatformType.NormalPlatform,
  )

  private val level6Platforms = Map(
    (-1, 1) -> PlatformType.NormalPlatform,
    (-1, 2) -> PlatformType.NormalPlatform,
    (-1, 3) -> PlatformType.NormalPlatform,
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

  def run(args: List[String]): IO[ExitCode] = {
    IO {
      val startPlatform = ((0, 0), PlatformType.StartPlatform)
      val platforms = level6Platforms + startPlatform
      val puzzle = Puzzle(platforms)

      println(findSolution(puzzle))
    }.as(ExitCode.Success)
  }
}
