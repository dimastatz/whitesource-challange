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
    tree.zipWithIndex.take(tree.length-1).foreach(i => updateWeight(i._1, tree(i._2 + 1)))
    tree
  }

  def updateWeight(current: Array[Node], next: Array[Node]): Unit = {
    current.zipWithIndex.foreach(i => {
      next(i._2).sum = Math.max(next(i._2).sum, next(i._2).value + i._1.sum)
      next(i._2 + 1).sum = Math.max(next(i._2 + 1).sum, next(i._2 + 1).value + i._1.sum)
    })
  }
}
