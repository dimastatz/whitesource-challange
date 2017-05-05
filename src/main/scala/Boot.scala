import scala.io.Source

/**
  * Created by Dima on 5/5/2017.
  */
case class Node(value:Int, left: Option[Node], right: Option[Node])

class Boot {
  def main(args: Array[String]): Unit = {
    val root = loadTree(Source.fromResource("triangle.txt").getLines)
    println(s"max found ${findMax(root)}")
  }

  def findMax(node: Node): Int = {
    if(node.left.isEmpty || node.right.isEmpty) node.value
    else node.value + Math.max(findMax(node.left.get), findMax(node.right.get))
  }

  def loadTree(lines: Iterator[String]): Node = ???
}
