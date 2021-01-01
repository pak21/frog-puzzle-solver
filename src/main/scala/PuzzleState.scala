import FrogPuzzleSolver.Actions

case class PuzzleState(
  puzzle: Puzzle,
  actions: Map[(Int, Int), Action],
  frogPosition: (Int, Int),
  direction: Direction,
  seenStates: Set[((Int, Int), Direction)],
  completion: PuzzleCompletion,
  moves: Int
)

object PuzzleState {
  def initialState(puzzle: Puzzle, actions: Actions): PuzzleState =
    PuzzleState(
      puzzle,
      actions.filter { _._2 != Action.NoAction }.toMap,
      (0, 0),
      Direction.Up,
      Set(((0, 0), Direction.Up)),
      PuzzleCompletion.Incomplete,
      0)

  private def calculateCompletion(
      puzzleState: PuzzleState,
      nextPosition: (Int, Int),
      nextSeenStates: Set[((Int, Int), Direction)],
      nextMoves: Int): PuzzleCompletion = {
    val nextPlatform = puzzleState.puzzle.platforms.get(nextPosition)
    nextPlatform.map {
      case PlatformType.EndPlatform =>
        if (nextSeenStates.map { _._1 }.size == puzzleState.puzzle.platforms.size)
          PuzzleCompletion.Success
        else
          PuzzleCompletion.UnvisitedPlatforms
      case _ =>
        if (nextMoves >= 100) PuzzleCompletion.TimedOut else PuzzleCompletion.Incomplete
    }.getOrElse(PuzzleCompletion.InWater)
  }

  def step(puzzleState: PuzzleState): PuzzleState = {
    val nextPosition = puzzleState.direction match {
      case Direction.Right => (puzzleState.frogPosition._1 + 1, puzzleState.frogPosition._2)
      case Direction.Up => (puzzleState.frogPosition._1, puzzleState.frogPosition._2 + 1)
      case Direction.Left => (puzzleState.frogPosition._1 - 1, puzzleState.frogPosition._2)
      case Direction.Down => (puzzleState.frogPosition._1, puzzleState.frogPosition._2 - 1)
    }

    val nextDirection = puzzleState.actions.get(nextPosition).map {
      case Action.NoAction => ??? // Filtered out, should never happen
      case Action.MoveRight => Direction.Right
      case Action.MoveUp => Direction.Up
      case Action.MoveLeft => Direction.Left
      case Action.MoveDown => Direction.Down
    }.getOrElse(puzzleState.direction)

    val nextState = (nextPosition, nextDirection)
    val nextMoves = puzzleState.moves + 1
    val nextSeenStates = puzzleState.seenStates + nextState

    val nextCompletion =
      if (puzzleState.seenStates.contains(nextState))
        PuzzleCompletion.Loop
      else
        calculateCompletion(puzzleState, nextPosition, nextSeenStates, nextMoves)

    PuzzleState(
      puzzleState.puzzle,
      puzzleState.actions,
      nextPosition,
      nextDirection,
      nextSeenStates,
      nextCompletion,
      nextMoves
    )
  }
}