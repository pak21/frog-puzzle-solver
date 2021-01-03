import FrogPuzzleSolver.Actions

case class PuzzleState(
  puzzle: Puzzle,
  actions: Map[(Int, Int), Action],
  frogPosition: (Int, Int),
  direction: Direction,
  seenStates: Set[((Int, Int), Option[Direction])],
  completion: PuzzleCompletion,
  moves: Int
)

object PuzzleState {
  def apply(puzzle: Puzzle, actions: Actions): PuzzleState =
    PuzzleState(
      puzzle,
      actions.filter { _._2 != Action.NoAction }.toMap,
      (0, 0),
      Direction.Up,
      Set(((0, 0), Some(Direction.Up))),
      PuzzleCompletion.Incomplete,
      0)

  private def calculateCompletion(
      puzzleState: PuzzleState,
      nextPosition: (Int, Int),
      nextSeenStates: Set[((Int, Int), Option[Direction])],
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
    val currentPlatformAction = puzzleState.actions.get(puzzleState.frogPosition)
    val distance = currentPlatformAction.map {
      case Action.Jump => 2
      case _ => 1
    }.getOrElse(1)

    val nextPositionBeforeTeleport = puzzleState.direction match {
      case Direction.Right => (puzzleState.frogPosition._1 + distance, puzzleState.frogPosition._2)
      case Direction.Up => (puzzleState.frogPosition._1, puzzleState.frogPosition._2 + distance)
      case Direction.Left => (puzzleState.frogPosition._1 - distance, puzzleState.frogPosition._2)
      case Direction.Down => (puzzleState.frogPosition._1, puzzleState.frogPosition._2 - distance)
    }

    // OK to do this on `nextPosition` as teleporters never change direction
    val nextDirection = puzzleState.actions.get(nextPositionBeforeTeleport).map {
      case Action.NoAction => ??? // Filtered out, should never happen
      case Action.MoveRight => Direction.Right
      case Action.MoveUp => Direction.Up
      case Action.MoveLeft => Direction.Left
      case Action.MoveDown => Direction.Down
      case Action.Jump => puzzleState.direction
    }.getOrElse(puzzleState.direction)

    val nextPlatform = puzzleState.puzzle.platforms.get(nextPositionBeforeTeleport)

    val (nextPosition, nextState, nextMoves, nextSeenStates) = nextPlatform match {
      case Some(PlatformType.Teleporter(destination)) => {
        val ns1 = (nextPositionBeforeTeleport, None)
        val ns2 = (destination, Some(nextDirection))
        (destination, ns2, puzzleState.moves + 2, puzzleState.seenStates + ns1 + ns2)
      }
      case _ => {
        val ns = (nextPositionBeforeTeleport, Some(nextDirection))
        (nextPositionBeforeTeleport, ns, puzzleState.moves + 1, puzzleState.seenStates + ns)
      }
    }

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