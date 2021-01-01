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

  private val level7Platforms = Map(
    (-1, 2) -> PlatformType.Teleporter((1, 3)),
    (0, 1) -> PlatformType.NormalPlatform,
    (0, 2) -> PlatformType.NormalPlatform,
    (0, 3) -> PlatformType.EndPlatform,
    (1, 3) -> PlatformType.Teleporter((-1, 2)),
  )

  private val level8Platforms = Map(
    (-1, 1) -> PlatformType.Teleporter((1, 3)),
    (-1, 2) -> PlatformType.NormalPlatform,
    (-1, 3) -> PlatformType.NormalPlatform,
    (0, 1) -> PlatformType.NormalPlatform,
    (0, 3) -> PlatformType.NormalPlatform,
    (1, 0) -> PlatformType.EndPlatform,
    (1, 1) -> PlatformType.NormalPlatform,
    (1, 2) -> PlatformType.NormalPlatform,
    (1, 3) -> PlatformType.Teleporter((-1, 1))
  )

  private val level9Platforms = Map(
    (-1, 1) -> PlatformType.NormalPlatform,
    (-1, 2) -> PlatformType.NormalPlatform,
    (0, 1) -> PlatformType.NormalPlatform,
    (0, 2) -> PlatformType.Teleporter((2, 4)),
    (1, 1) -> PlatformType.NormalPlatform,
    (1, 2) -> PlatformType.NormalPlatform,
    (1, 4) -> PlatformType.NormalPlatform,
    (1, 5) -> PlatformType.NormalPlatform,
    (2, 1) -> PlatformType.EndPlatform,
    (2, 4) -> PlatformType.Teleporter((0, 2)),
    (2, 5) -> PlatformType.NormalPlatform,
    (3, 4) -> PlatformType.NormalPlatform,
    (3, 5) -> PlatformType.NormalPlatform,
  )

  val level10Platforms = Map(
    (-2, 3) -> PlatformType.NormalPlatform,
    (-2, 4) -> PlatformType.Teleporter((-1, 1)),
    (-1, 1) -> PlatformType.Teleporter((-2, 4)),
    (-1, 2) -> PlatformType.NormalPlatform,
    (-1, 3) -> PlatformType.NormalPlatform,
    (-1, 4) -> PlatformType.Teleporter((2, 2)),
    (0, 1) -> PlatformType.NormalPlatform,
    (0, 2) -> PlatformType.NormalPlatform,
    (1, 1) -> PlatformType.EndPlatform,
    (1, 2) -> PlatformType.NormalPlatform,
    (1, 3) -> PlatformType.NormalPlatform,
    (2, 2) -> PlatformType.Teleporter((-1, 4)),
    (2, 3) -> PlatformType.NormalPlatform,
  )

  val level11Platforms = Map(
    (-1, 1) -> PlatformType.Teleporter((0, 5)),
    (-1, 2) -> PlatformType.NormalPlatform,
    (-1, 4) -> PlatformType.Teleporter((2, 2)),
    (0, 1) -> PlatformType.NormalPlatform,
    (0, 2) -> PlatformType.Teleporter((2, 1)),
    (0, 4) -> PlatformType.NormalPlatform,
    (0, 5) -> PlatformType.Teleporter((-1, 1)),
    (1, 1) -> PlatformType.NormalPlatform,
    (1, 2) -> PlatformType.NormalPlatform,
    (2, 1) -> PlatformType.Teleporter((0, 2)),
    (2, 2) -> PlatformType.Teleporter((-1, 4)),
    (3, 1) -> PlatformType.NormalPlatform,
    (3, 2) -> PlatformType.EndPlatform,
  )

  val level12Platforms = Map(
    (-1, 1) -> PlatformType.Teleporter((1, 4)),
    (-1, 2) -> PlatformType.NormalPlatform,
    (-1, 3) -> PlatformType.Teleporter((1, 2)),
    (-1, 4) -> PlatformType.NormalPlatform,
    (-1, 5) -> PlatformType.EndPlatform,
    (0, 1) -> PlatformType.NormalPlatform,
    (0, 2) -> PlatformType.NormalPlatform,
    (0, 3) -> PlatformType.NormalPlatform,
    (0, 4) -> PlatformType.NormalPlatform,
    (1, 1) -> PlatformType.NormalPlatform,
    (1, 2) -> PlatformType.Teleporter((-1, 3)),
    (1, 3) -> PlatformType.NormalPlatform,
    (1, 4) -> PlatformType.Teleporter((-1, 1)),
  )

  def run(args: List[String]): IO[ExitCode] = {
    IO {
      val startPlatform = ((0, 0), PlatformType.StartPlatform)
      val platforms = level12Platforms + startPlatform
      val puzzle = Puzzle(platforms)

      println(findSolution(puzzle))
    }.as(ExitCode.Success)
  }
}
