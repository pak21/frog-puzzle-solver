sealed trait Action

object Action {
  case object NoAction extends Action
  case object MoveRight extends Action
  case object MoveUp extends Action
  case object MoveLeft extends Action
  case object MoveDown extends Action

  def nextAction(item: ((Int, Int), Action), puzzle: Puzzle): Option[Action] = {
    val oldLocation = item._1
    val newAction: Option[Action] = item._2 match {
      case NoAction => Some(MoveRight)
      case MoveRight => Some(MoveUp)
      case MoveUp => Some(MoveLeft)
      case MoveLeft => Some(MoveDown)
      case MoveDown => None
    }
    newAction.flatMap { na =>
      val newLocation = na match {
        case MoveRight => (oldLocation._1 + 1, oldLocation._2)
        case MoveUp => (oldLocation._1, oldLocation._2 + 1)
        case MoveLeft => (oldLocation._1 - 1, oldLocation._2)
        case MoveDown => (oldLocation._1, oldLocation._2 - 1)
        case _ => ???
      }
      if (puzzle.platforms.contains(newLocation)) newAction
      else nextAction((oldLocation, na), puzzle)
    }
  }
}
