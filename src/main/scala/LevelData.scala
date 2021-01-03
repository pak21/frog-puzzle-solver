object LevelData {
  val level1Platforms = Map(
    (0, 1) -> PlatformType.NormalPlatform,
    (0, 2) -> PlatformType.NormalPlatform,
    (1, 2) -> PlatformType.NormalPlatform,
    (2, 2) -> PlatformType.EndPlatform,
  )

  val level2Platforms = Map(
    (0, 1) -> PlatformType.NormalPlatform,
    (0, 2) -> PlatformType.NormalPlatform,
    (1, 2) -> PlatformType.NormalPlatform,
    (2, 2) -> PlatformType.NormalPlatform,
    (2, 3) -> PlatformType.NormalPlatform,
    (2, 4) -> PlatformType.EndPlatform,
  )

  val level3Platforms = Map(
    (0, 1) -> PlatformType.NormalPlatform,
    (0, 2) -> PlatformType.NormalPlatform,
    (0, 3) -> PlatformType.NormalPlatform,
    (1, 2) -> PlatformType.NormalPlatform,
    (1, 3) -> PlatformType.NormalPlatform,
    (2, 2) -> PlatformType.NormalPlatform,
    (2, 3) -> PlatformType.NormalPlatform,
    (2, 4) -> PlatformType.EndPlatform,
  )

  val level4Platforms = Map(
    (-1, 1) -> PlatformType.NormalPlatform,
    (-1, 2) -> PlatformType.NormalPlatform,
    (-1, 3) -> PlatformType.NormalPlatform,
    (0, 1) -> PlatformType.NormalPlatform,
    (0, 2) -> PlatformType.NormalPlatform,
    (0, 3) -> PlatformType.NormalPlatform,
    (1, 1) -> PlatformType.NormalPlatform,
    (1, 2) -> PlatformType.NormalPlatform,
    (2, 1) -> PlatformType.EndPlatform,
  )

  val level5Platforms = Map(
    (-1, 2) -> PlatformType.NormalPlatform,
    (-1, 3) -> PlatformType.NormalPlatform,
    (0, 1) -> PlatformType.NormalPlatform,
    (0, 2) -> PlatformType.NormalPlatform,
    (0, 3) -> PlatformType.NormalPlatform,
    (0, 4) -> PlatformType.EndPlatform,
    (1, 1) -> PlatformType.NormalPlatform,
    (1, 2) -> PlatformType.NormalPlatform,
    (1, 3) -> PlatformType.NormalPlatform,
    (2, 1) -> PlatformType.NormalPlatform,
    (2, 2) -> PlatformType.NormalPlatform,
    (2, 3) -> PlatformType.NormalPlatform,
  )

  val level6Platforms = Map(
    (-1, 1) -> PlatformType.NormalPlatform,
    (-1, 2) -> PlatformType.NormalPlatform,
    (-1, 3) -> PlatformType.NormalPlatform,
    (0, 1) -> PlatformType.NormalPlatform,
    (0, 2) -> PlatformType.NormalPlatform,
    (0, 3) -> PlatformType.NormalPlatform,
    (1, 2) -> PlatformType.NormalPlatform,
    (1, 3) -> PlatformType.EndPlatform,
    (1, 4) -> PlatformType.NormalPlatform,
    (2, 2) -> PlatformType.NormalPlatform,
    (2, 3) -> PlatformType.NormalPlatform,
    (2, 4) -> PlatformType.NormalPlatform,
    (3, 2) -> PlatformType.NormalPlatform,
    (3, 3) -> PlatformType.NormalPlatform,
  )

  val level7Platforms = Map(
    (-1, 2) -> PlatformType.Teleporter((1, 3)),
    (0, 1) -> PlatformType.NormalPlatform,
    (0, 2) -> PlatformType.NormalPlatform,
    (0, 3) -> PlatformType.EndPlatform,
    (1, 3) -> PlatformType.Teleporter((-1, 2)),
  )

  val level8Platforms = Map(
    (-1, 1) -> PlatformType.Teleporter((1, 3)),
    (-1, 2) -> PlatformType.NormalPlatform,
    (-1, 3) -> PlatformType.NormalPlatform,
    (0, 1) -> PlatformType.NormalPlatform,
    (0, 3) -> PlatformType.NormalPlatform,
    (1, 0) -> PlatformType.EndPlatform,
    (1, 1) -> PlatformType.NormalPlatform,
    (1, 2) -> PlatformType.NormalPlatform,
    (1, 3) -> PlatformType.Teleporter((-1, 1))
  )

  val level9Platforms = Map(
    (-1, 1) -> PlatformType.NormalPlatform,
    (-1, 2) -> PlatformType.NormalPlatform,
    (0, 1) -> PlatformType.NormalPlatform,
    (0, 2) -> PlatformType.Teleporter((2, 4)),
    (1, 1) -> PlatformType.NormalPlatform,
    (1, 2) -> PlatformType.NormalPlatform,
    (1, 4) -> PlatformType.NormalPlatform,
    (1, 5) -> PlatformType.NormalPlatform,
    (2, 1) -> PlatformType.EndPlatform,
    (2, 4) -> PlatformType.Teleporter((0, 2)),
    (2, 5) -> PlatformType.NormalPlatform,
    (3, 4) -> PlatformType.NormalPlatform,
    (3, 5) -> PlatformType.NormalPlatform,
  )

  val level10Platforms = Map(
    (-2, 3) -> PlatformType.NormalPlatform,
    (-2, 4) -> PlatformType.Teleporter((-1, 1)),
    (-1, 1) -> PlatformType.Teleporter((-2, 4)),
    (-1, 2) -> PlatformType.NormalPlatform,
    (-1, 3) -> PlatformType.NormalPlatform,
    (-1, 4) -> PlatformType.Teleporter((2, 2)),
    (0, 1) -> PlatformType.NormalPlatform,
    (0, 2) -> PlatformType.NormalPlatform,
    (1, 1) -> PlatformType.EndPlatform,
    (1, 2) -> PlatformType.NormalPlatform,
    (1, 3) -> PlatformType.NormalPlatform,
    (2, 2) -> PlatformType.Teleporter((-1, 4)),
    (2, 3) -> PlatformType.NormalPlatform,
  )

  val level11Platforms = Map(
    (-1, 1) -> PlatformType.Teleporter((0, 5)),
    (-1, 2) -> PlatformType.NormalPlatform,
    (-1, 4) -> PlatformType.Teleporter((2, 2)),
    (0, 1) -> PlatformType.NormalPlatform,
    (0, 2) -> PlatformType.Teleporter((2, 1)),
    (0, 4) -> PlatformType.NormalPlatform,
    (0, 5) -> PlatformType.Teleporter((-1, 1)),
    (1, 1) -> PlatformType.NormalPlatform,
    (1, 2) -> PlatformType.NormalPlatform,
    (2, 1) -> PlatformType.Teleporter((0, 2)),
    (2, 2) -> PlatformType.Teleporter((-1, 4)),
    (3, 1) -> PlatformType.NormalPlatform,
    (3, 2) -> PlatformType.EndPlatform,
  )

  val level12Platforms = Map(
    (-1, 1) -> PlatformType.Teleporter((1, 4)),
    (-1, 2) -> PlatformType.NormalPlatform,
    (-1, 3) -> PlatformType.Teleporter((1, 2)),
    (-1, 4) -> PlatformType.NormalPlatform,
    (-1, 5) -> PlatformType.EndPlatform,
    (0, 1) -> PlatformType.NormalPlatform,
    (0, 2) -> PlatformType.NormalPlatform,
    (0, 3) -> PlatformType.NormalPlatform,
    (0, 4) -> PlatformType.NormalPlatform,
    (1, 1) -> PlatformType.NormalPlatform,
    (1, 2) -> PlatformType.Teleporter((-1, 3)),
    (1, 3) -> PlatformType.NormalPlatform,
    (1, 4) -> PlatformType.Teleporter((-1, 1)),
  )

  val level13Platforms = Map(
    (0, 1) -> PlatformType.NormalPlatform,
    (0, 3) -> PlatformType.EndPlatform
  )

  val level14Platforms = Map(
    (0, 1) -> PlatformType.NormalPlatform,
    (1, 1) -> PlatformType.NormalPlatform,
    (1, 2) -> PlatformType.NormalPlatform,
    (1, 4) -> PlatformType.EndPlatform
  )

  val level15Platforms = Map(
    (0, 1) -> PlatformType.NormalPlatform,
    (0, 2) -> PlatformType.NormalPlatform,
    (0, 3) -> PlatformType.NormalPlatform,
    (1, 2) -> PlatformType.EndPlatform
  )

  val level16Platforms = Map(
    (0, 1) -> PlatformType.NormalPlatform,
    (0, 2) -> PlatformType.NormalPlatform,
    (1, 1) -> PlatformType.NormalPlatform,
    (1, 2) -> PlatformType.NormalPlatform,
    (1, 3) -> PlatformType.NormalPlatform,
    (1, 4) -> PlatformType.NormalPlatform,
    (2, 1) -> PlatformType.NormalPlatform,
    (2, 3) -> PlatformType.EndPlatform,
    (3, 1) -> PlatformType.NormalPlatform,
    (3, 2) -> PlatformType.NormalPlatform,
  )

  val level17Platforms = Map(
    (-1, 2) -> PlatformType.NormalPlatform,
    (-1, 3) -> PlatformType.NormalPlatform,
    (0, 1) -> PlatformType.NormalPlatform,
    (0, 2) -> PlatformType.NormalPlatform,
    (0, 3) -> PlatformType.NormalPlatform,
    (1, 1) -> PlatformType.NormalPlatform,
    (1, 2) -> PlatformType.NormalPlatform,
    (1, 4) -> PlatformType.EndPlatform,
    (2, 1) -> PlatformType.NormalPlatform,
    (2, 2) -> PlatformType.NormalPlatform,
    (2, 3) -> PlatformType.NormalPlatform,
  )

  val level18Platforms = Map(
    (-1, 2) -> PlatformType.NormalPlatform,
    (-1, 3) -> PlatformType.NormalPlatform,
    (0, 1) -> PlatformType.NormalPlatform,
    (0, 2) -> PlatformType.NormalPlatform,
    (0, 3) -> PlatformType.NormalPlatform,
    (1, 1) -> PlatformType.NormalPlatform,
    (1, 2) -> PlatformType.NormalPlatform,
    (1, 4) -> PlatformType.EndPlatform,
    (2, 1) -> PlatformType.NormalPlatform,
    (2, 2) -> PlatformType.NormalPlatform,
  )

  val level19Platforms = Map(
    (-1, 2) -> PlatformType.NormalPlatform,
    (-1, 3) -> PlatformType.NormalPlatform,
    (0, 1) -> PlatformType.NormalPlatform,
    (0, 2) -> PlatformType.VanishingPlatform,
    (0, 3) -> PlatformType.NormalPlatform,
    (1, 2) -> PlatformType.EndPlatform,
  )

  val level20Platforms = Map(
    (-1, 2) -> PlatformType.NormalPlatform,
    (-1, 3) -> PlatformType.NormalPlatform,
    (0, 1) -> PlatformType.NormalPlatform,
    (0, 2) -> PlatformType.VanishingPlatform,
    (0, 3) -> PlatformType.NormalPlatform,
    (2, 2) -> PlatformType.NormalPlatform,
    (2, 3) -> PlatformType.NormalPlatform,
    (2, 4) -> PlatformType.VanishingPlatform,
    (2, 5) -> PlatformType.NormalPlatform,
    (3, 4) -> PlatformType.EndPlatform
  )

  val level21Platforms = Map(
    (-2, 2) -> PlatformType.NormalPlatform,
    (-2, 3) -> PlatformType.NormalPlatform,
    (-1, 2) -> PlatformType.NormalPlatform,
    (-1, 3) -> PlatformType.VanishingPlatform,
    (-1, 4) -> PlatformType.NormalPlatform,
    (0, 1) -> PlatformType.NormalPlatform,
    (0, 2) -> PlatformType.VanishingPlatform,
    (0, 3) -> PlatformType.NormalPlatform,
    (0, 4) -> PlatformType.VanishingPlatform,
    (0, 5) -> PlatformType.EndPlatform,
    (1, 2) -> PlatformType.NormalPlatform,
    (1, 3) -> PlatformType.VanishingPlatform,
    (1, 4) -> PlatformType.NormalPlatform,
    (1, 5) -> PlatformType.NormalPlatform,
    (2, 2) -> PlatformType.NormalPlatform,
    (2, 3) -> PlatformType.NormalPlatform,
  )
}
