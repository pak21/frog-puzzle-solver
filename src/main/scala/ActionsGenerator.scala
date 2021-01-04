import FrogPuzzleSolver.Actions

object ActionsGenerator {
  def initialState(puzzle: Puzzle): Actions = {
    puzzle.platforms.filter { platform =>
      platform._2 match {
        case PlatformType.NormalPlatform | PlatformType.VanishingPlatform => true
        case _ => false
      }
    }.keys.map { (_, Action.NoAction) }.toList
  }

  def next(puzzle: Puzzle, actions: Actions): Option[Actions] = {
    actions match {
      case Nil => None
      case h :: t =>
        Action.nextAction(h, puzzle) match {
          case None => next(puzzle, t).map { (h._1, Action.NoAction) :: _ }
          case Some(action) => Some((h._1, action) :: t)
        }
    }
  }
}
