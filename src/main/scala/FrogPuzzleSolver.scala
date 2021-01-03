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
    val puzzleState = PuzzleState(puzzle, actions)
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

  private val basicActions = List(Action.MoveRight, Action.MoveUp, Action.MoveLeft, Action.MoveDown)
  private val jumpActions = basicActions :+ Action.Jump

  def run(args: List[String]): IO[ExitCode] = {
    IO {
      val startPlatform = ((0, 0), PlatformType.StartPlatform)
      val platforms = LevelData.level18Platforms + startPlatform
      val puzzle = Puzzle(platforms, jumpActions)

      println(findSolution(puzzle))
    }.as(ExitCode.Success)
  }
}
