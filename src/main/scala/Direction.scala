sealed trait Direction

object Direction {
  case object Right extends Direction // +ve X
  case object Up extends Direction // +ve Y
  case object Left extends Direction // -ve X
  case object Down extends Direction // -ve Y
}