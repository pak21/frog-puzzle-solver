import FrogPuzzleSolver.Actions

case class PuzzleState(
  puzzle: Puzzle,
  actions: Map[(Int, Int), Action],
  frogPosition: (Int, Int),
  direction: Direction,
  visitedPlatforms: Set[(Int, Int)],
  completion: PuzzleCompletion,
  moves: Int
)

object PuzzleState {
  def initialState(puzzle: Puzzle, actions: Actions): PuzzleState = {
    val activeActions = actions.filter { _._2 != Action.NoAction }.toMap
    PuzzleState(puzzle, activeActions, (0, 0), Direction.Up, Set((0, 0)), PuzzleCompletion.Incomplete, 0)
  }

  def step(puzzleState: PuzzleState): PuzzleState = {
    val nextPosition = puzzleState.direction match {
      case Direction.Right => (puzzleState.frogPosition._1 + 1, puzzleState.frogPosition._2)
      case Direction.Up => (puzzleState.frogPosition._1, puzzleState.frogPosition._2 + 1)
      case Direction.Left => (puzzleState.frogPosition._1 - 1, puzzleState.frogPosition._2)
      case Direction.Down => (puzzleState.frogPosition._1, puzzleState.frogPosition._2 - 1)
    }
    val nextVisitedPlatforms = puzzleState.visitedPlatforms + nextPosition
    val nextMoves = puzzleState.moves + 1

    val nextPlatform = puzzleState.puzzle.platforms.get(nextPosition)
    val nextCompletion = nextPlatform.map {
      case PlatformType.EndPlatform =>
        if (puzzleState.puzzle.platforms.size == nextVisitedPlatforms.size)
          PuzzleCompletion.Success
        else
          PuzzleCompletion.UnvisitedPlatforms
      case _ =>
        if (nextMoves >= 100) PuzzleCompletion.TimedOut else PuzzleCompletion.Incomplete
    }.getOrElse(PuzzleCompletion.InWater)

    val nextDirection = puzzleState.actions.get(nextPosition).map {
      case Action.NoAction => ??? // Filtered out, should never happen
      case Action.MoveRight => Direction.Right
      case Action.MoveUp => Direction.Up
      case Action.MoveLeft => Direction.Left
      case Action.MoveDown => Direction.Down
    }.getOrElse(puzzleState.direction)

    PuzzleState(
      puzzleState.puzzle,
      puzzleState.actions,
      nextPosition,
      nextDirection,
      nextVisitedPlatforms,
      nextCompletion,
      nextMoves
    )
  }
}