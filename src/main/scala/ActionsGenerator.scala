import FrogPuzzleSolver.Actions
import cats.implicits._

object ActionsGenerator {
  def initialState(puzzle: Puzzle): Actions = {
    puzzle.platforms.filter { _._2 == PlatformType.NormalPlatform  }.keys.map { (_, Action.NoAction) }.toList
  }

  def next(actions: Actions): Option[Actions] = {
    actions match {
      case Nil => None
      case h :: t => {
        Action.nextAction(h._2) match {
          case None => next(t).map { newT => (h._1, Action.NoAction) :: newT }
          case Some(action) => ((h._1, action) :: t).some
        }
      }
    }
  }
}
