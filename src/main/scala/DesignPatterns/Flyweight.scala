package DesignPatterns

class Mesh
class Texture
class Vector
class Color

// Limit the creation of instances that share the common data
object TreeModel {
  val mesh: Mesh = new Mesh
  val bark: Texture = new Texture
  val leaves: Texture = new Texture
}

// Allow variance in parameters that are specific to an instance
class Tree (
  val position: Vector,
  val height: Double,
  val thickness: Double,
  barkTint: Color,
  leafTint: Color
) {
  val treeModel = TreeModel
}

case class Terrain (
  movementCost: Int,
  isWater: Boolean,
  texture: Texture,
  name: String
)

object Terrain {
  val grass = Terrain(1, false, new Texture, "grass")
  val hill = Terrain(2, false, new Texture, "hill")
  val river = Terrain(3, false, new Texture, "river")
}

class World {

  val width: Int = 20
  val height: Int = 20
  val terrain = Terrain
  val tiles = Array.ofDim[Terrain](width, height)

  val random = scala.util.Random


  def generateTerrain(): Unit = {
    for (x <- 0 to width - 1) {
      for (y <- 0 to height - 1) {
        if (random.nextInt(10) == 10) {
          tiles(x)(y) = terrain.hill
        } else {
          tiles(x)(y) = terrain.grass
        }
      }
    }

    val riverX = random.nextInt(width - 1)
    for (y <- 0 to height - 1 ) {
      tiles(riverX)(y) = terrain.river
    }
  }

  def getTile(x: Int, y: Int): Terrain = {
    tiles(x)(y)
  }
}

object Flyweight extends App {

  val tree = new Tree(new Vector, 1.0, 1.0, new Color, new Color)
  println(tree)

  val world = new World()

  world.generateTerrain()

  println(world.getTile(1,1).name)
  println(world.getTile(1,1).isWater)
  println(world.getTile(1,1).movementCost)

}