import FrogPuzzleSolver.Actions
import cats.implicits._

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
      case h :: t => {
        Action.nextAction(h, puzzle) match {
          case None => next(puzzle, t).map { newT => (h._1, Action.NoAction) :: newT }
          case Some(action) => ((h._1, action) :: t).some
        }
      }
    }
  }
}
