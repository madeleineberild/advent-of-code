package adventofcode

import scala.io.Source
import scala.collection.mutable.ArrayBuffer

class Day5 {
    val fileName = "input/Day5.txt"

    val lines = new ArrayBuffer[Line]()
    var highestX = 0
    var highestY = 0
    var diagram = Array.ofDim[Int](highestY, highestX)

    def fromFile(fileName: String) = {
        for(line <- Source.fromFile(fileName).getLines) {
            val line1 = line.split(" -> ")(0)
            val line2 = line.split(" -> ")(1)
            val x1 = line1.split(",")(0).toInt
            val y1 = line1.split(",")(1).toInt
            val x2 = line2.split(",")(0).toInt
            val y2 = line2.split(",")(1).toInt
            if(x1 > highestX){
                highestX = x1
            }
            if(x2 > highestX) {
                highestX = x2
            }
            if(y1 > highestY) {
                highestY = y1
            }
            if(y2 > highestY) {
                highestY = y2
            }
            val p1 = Point(x1, y1)
            val p2 = Point(x2, y2)
            lines.append(Line(p1, p2))
        }
    }

    def problemOne(): String = {
        val straight = lines.filter(l => l.p1.x == l.p2.x || l.p1.y == l.p2.y)
        for(line <- straight) {
            val minY = line.p1.y.min(line.p2.y)
            val maxY = line.p1.y.max(line.p2.y)
            val minX = line.p1.x.min(line.p2.x)
            val maxX = line.p1.x.max(line.p2.x)
            for(y <- Range(minY, maxY+1)) {
                for(x <- Range(minX, maxX+1)) {
                    diagram(y)(x) += 1
                }
            }
        }
        var numberOverlapping = 0
        for(i <- 0 to highestY) {
            for(j <- 0 to highestX) {
                if(diagram(i)(j) > 1) {
                    numberOverlapping += 1
                }
            }
        }
        numberOverlapping.toString
    }

    //problemOne needs to execute once before
    def problemTwo(): String = {
        val diagonal = lines.filterNot(l => l.p1.x == l.p2.x || l.p1.y == l.p2.y)
        for(line <- diagonal) {
            for(p <- line.diagPoints()) {
                diagram(p.y)(p.x) += 1
            }
        }
        var numberOverlapping = 0
        for(i <- 0 to highestY) {
            for(j <- 0 to highestX) {
                if(diagram(i)(j) > 1) {
                    numberOverlapping += 1
                }
            }
        }
        numberOverlapping.toString
    }
    
    def run = {
        fromFile("input/Day5.txt")
        diagram = Array.ofDim[Int](highestY+1, highestX+1)
        println("The solution of problem 1: " + problemOne)
        //print(diagram.map(_.mkString).mkString("\n"))
        println("The solution of problem 2: " + problemTwo)
    }
}

case class Point(x: Int, y: Int) {
    override def toString(): String = s"($x, $y)"
}

case class Line(p1: Point, p2: Point) {
    override def toString(): String = s"${p1.toString()} -> ${p2.toString()}"

    def diagPoints(): ArrayBuffer[Point] = {
        val points = new ArrayBuffer[Point]()
        if(p1.x < p2.x && p1.y < p2.y) {
            var x = p1.x
            var y = p1.y
            while(x <= p2.x && y <= p2.y) {
                points.append(Point(x, y))
                x += 1
                y += 1
            }
        } else if(p1.x < p2.x && p1.y > p2.y) {
            var x = p1.x
            var y = p1.y
            while(x <= p2.x && y >= p2.y) {
                points.append(Point(x, y))
                x += 1
                y -= 1
            }
        } else if(p1.x > p2.x && p1.y < p2.y) {
            var x = p1.x
            var y = p1.y
            while(x >= p2.x && y <= p2.y) {
                points.append(Point(x, y))
                x -= 1
                y += 1
            }
        } else {
            var x = p1.x
            var y = p1.y
            while(x >= p2.x && y >= p2.y) {
                points.append(Point(x, y))
                x -= 1
                y -= 1
            }
        }
        return points
    }
}