//Triangle, square, pentagonal, hexagonal, heptagonal, and octagonal numbers are all figurate (polygonal) numbers and are generated by the following formulae:
//
//  Triangle	 	P3,n=n(n+1)/2	 	1, 3, 6, 10, 15, ...
//Square	 	P4,n=n2	 	1, 4, 9, 16, 25, ...
//Pentagonal	 	P5,n=n(3n−1)/2	 	1, 5, 12, 22, 35, ...
//Hexagonal	 	P6,n=n(2n−1)	 	1, 6, 15, 28, 45, ...
//Heptagonal	 	P7,n=n(5n−3)/2	 	1, 7, 18, 34, 55, ...
//Octagonal	 	P8,n=n(3n−2)	 	1, 8, 21, 40, 65, ...
//The ordered set of three 4-digit numbers: 8128, 2882, 8281, has three interesting properties.
//
//The set is cyclic, in that the last two digits of each number is the first two digits of the next number (including the last number with the first).
//Each polygonal type: triangle (P3,127=8128), square (P4,91=8281), and pentagonal (P5,44=2882), is represented by a different number in the set.
//  This is the only set of 4-digit numbers with this property.
//Find the sum of the only ordered set of six cyclic 4-digit numbers for which each polygonal type: triangle, square, pentagonal, hexagonal, heptagonal, and octagonal, is represented by a different number in the set.

object Problem061 {

  class Node(var value: Int) {}

  class Edge(var source: Int, var target: Int, var tag: Int) {
    override def toString() = {
      s"e($source, $target, $tag)"
    }
  }

  case class GraphPartition(visited: List[Int], path: List[List[Edge]])

  class Graph {
    var nodes = List[Node]()
    var edges = List[Edge]()

    def addNode(node: Node): Unit = {
      nodes = nodes :+ node
    }

    def addNode(n: Int): Unit = {
      addNode(new Node(n))
    }

    def addNodes(nodes: List[Int]) = {
      nodes.foreach(addNode)
    }

    def addEdge(edge: Edge): Unit = {
      edges = edges :+ edge
    }

    def addEdge(source: Int, target: Int, tag: Int): Unit = {
      addEdge(new Edge(source, target, tag))
    }

    def findNode(node: Int) = {
      nodes.find(_.value == node)
    }

    def findEdges(source: Int) = {
      edges.filter( _.source == source)
    }

    def removeNodes(ns: List[Int]): Unit = {
      nodes = nodes.filter(e => !ns.contains(e.value))
      edges = edges.filter(e => !ns.contains(e.source) && !ns.contains(e.target)) // once nodes removed, associated nodes also removed
    }


    def traversePath(edge: Edge, visited: List[Int], path: List[Edge], maxLength: Int): List[List[Edge]] = {
      val s = findNode(edge.target).get

      val tags = path.map(_.tag)
      if(tags.distinct.length != tags.length || maxLength <= path.length) return List() // forget about this path if it contains nodes from the same batch

      if(visited.contains(s.value)) return List(path :+ edge)

      val newVisited = s.value :: visited
      val newPath = edge.tag == -1 match  {
        case true => path
        case false => path :+ edge
      }

      val edges = findEdges(s.value)
      if(edges.isEmpty) return List(newPath)

      edges.flatMap(e => traversePath(e, newVisited, newPath, maxLength)).filter(l => l.map(_.tag).distinct.length == l.length)
    }

    def traversePath(start: Int, maxLength: Int) : List[List[Edge]] = {
      traversePath(new Edge(-1, start, -1), List(), List(), maxLength)
    }

    def getAllPaths(maxLength: Int) = {
      nodes.flatMap(n => traversePath(n.value, maxLength))
    }

    def isCircularPath(path: List[Edge]): Boolean = {
      if(path.length <= 1) return false
      path.head.source == path.last.target
    }

    def getAllCircularPaths(length: Int) = {
      getAllPaths(length).filter(_.length == length).filter(isCircularPath)
    }
  }


  def nRange(f: Int => Int, min: Int, max: Int): List[Int] = {
    var n = 1
    while(f(n) < min) n += 1
    var r = List[Int]()
    while(f(n) <= max) {
      r = f(n) :: r
      n += 1
    }

    r.reverse.filter(_ % 100 >= 10) // only take those who are able to chain up to become 4-digit
  }

  def pFunction(n: Int): Int => Int = {
    n match {
      case 3 => n => (n * (n + 1) / 2)
      case 4 => n => (n * n)
      case 5 => n => (n * (3 * n - 1) / 2)
      case 6 => n => (n * (2 * n - 1))
      case 7 => n => (n * (5 * n - 3) / 2)
      case 8 => n => (n * (3 * n - 2))
    }
  }

  def main(args: Array[String]): Unit = {
    val g = new Graph()

    val pList =  Range(3, 9).map(e => nRange(pFunction(e), 1000, 9999)).toList
    val pNodes = pList.flatten.flatMap(e => List(e % 100, e / 100)).distinct // get all nodes as 2-digit numbers
    g.addNodes(pNodes) // add nodes into graph

    pList.zipWithIndex.foreach{
      case (l, index) =>
        l.foreach(e => {
          g.addEdge(e / 100, e % 100, index + 3) // add edges
        })
    }

    g.removeNodes(pNodes.diff(g.edges.map(_.target))) // find all nodes having no coming path and remove the outgoing edges

    val sum = g.getAllCircularPaths(pList.length).head.map(e => e.source * 100 + e.target).sum
    println(s"Solution is $sum")
  }
}
