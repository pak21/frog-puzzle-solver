import cats.implicits._

case class Puzzle(
  platforms: Map[(Int, Int), PlatformType],
  nextActions: Map[Action, Option[Action]]
)

object Puzzle {
  def apply(platforms: Map[(Int, Int), PlatformType], availableActions: List[Action]): Puzzle = {
    val actionsWithNone = Action.NoAction +: availableActions
    val nextActions = availableActions.some.sequence :+ None
    Puzzle(platforms, actionsWithNone.zip(nextActions).toMap)
  }
}