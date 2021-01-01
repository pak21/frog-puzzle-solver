sealed trait PuzzleCompletion

object PuzzleCompletion {
  case object Incomplete extends PuzzleCompletion
  case object Success extends PuzzleCompletion
  case object InWater extends PuzzleCompletion
  case object UnvisitedPlatforms extends PuzzleCompletion
  case object TimedOut extends PuzzleCompletion
}