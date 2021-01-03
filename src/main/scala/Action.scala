import cats.implicits._

sealed trait Action

object Action {
  case object NoAction extends Action
  case object MoveRight extends Action
  case object MoveUp extends Action
  case object MoveLeft extends Action
  case object MoveDown extends Action
  case object Jump extends Action

  private def shouldPrune(action: Action): Boolean =
    action match {
      case MoveRight | MoveUp | MoveLeft | MoveDown => true
      case NoAction | Jump => false
    }

  def nextAction(item: ((Int, Int), Action), puzzle: Puzzle): Option[Action] = {
    val oldLocation = item._1
    puzzle.nextActions(item._2).flatMap { next =>
      if (shouldPrune(next)) {
        val newLocation = next match {
          case MoveRight => (oldLocation._1 + 1, oldLocation._2)
          case MoveUp => (oldLocation._1, oldLocation._2 + 1)
          case MoveLeft => (oldLocation._1 - 1, oldLocation._2)
          case MoveDown => (oldLocation._1, oldLocation._2 - 1)
          case _ => ??? // Should never happen
        }
        if (puzzle.platforms.contains(newLocation)) next.some
        else nextAction((oldLocation, next), puzzle)
      } else next.some
    }
  }
}
