import scala.io.Source

/**
  * Created by Dima on 5/5/2017.
  */
case class Node(value:Int, var sum: Int)

object Boot {
  def main(args: Array[String]): Unit = {
    val tree = loadTree(Source.fromResource("triangle.txt").getLines)
    println(s"max found ${tree.last.map(i => i.sum).max}")
  }

  def loadTree(lines: Iterator[String]): Array[Array[Node]] = {
    val tree = lines.map(i => i.split(" ").map(j => Node(j.toInt, j.toInt))).toArray
    tree.zipWithIndex.take(tree.length-1).foreach(i => updateWeight(tree, i._2))
    tree
  }

  def updateWeight(lines:Array[Array[Node]], index: Int): Unit = {
    val next = lines(index + 1)
    lines(index).zipWithIndex.foreach(i =>
      updateChildren(i._1.sum, next(i._2), next(i._2 + 1)))
  }

  def updateChildren(value: Int, child1: Node, child2: Node): Unit = {
    child1.sum = Math.max(child1.sum, child1.value + value)
    child2.sum = Math.max(child2.sum, child2.value + value)
  }
}
