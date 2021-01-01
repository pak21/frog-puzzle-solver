sealed trait PlatformType

object PlatformType {
  case object NormalPlatform extends PlatformType
  case object StartPlatform extends PlatformType
  case object EndPlatform extends PlatformType

  case class Teleporter(destination: (Int, Int)) extends PlatformType
}
