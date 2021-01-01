sealed trait Action

object Action {
  case object NoAction extends Action
  case object MoveRight extends Action
  case object MoveUp extends Action
  case object MoveLeft extends Action
  case object MoveDown extends Action

  def nextAction(action: Action): Option[Action] =
    action match {
      case NoAction => Some(MoveRight)
      case MoveRight => Some(MoveUp)
      case MoveUp => Some(MoveLeft)
      case MoveLeft => Some(MoveDown)
      case MoveDown => None
    }
}
