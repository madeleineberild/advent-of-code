package adventofcode

import scala.io.Source
import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.HashMap

class Day7 {
    val input = new ArrayBuffer[String]
    val graph = new HashMap[String, Vector[String]]

    def fromFile(fileName: String) = {
        for(line <- Source.fromFile(fileName).getLines) {
            input.append(line)
        }
    }

    def colors: ArrayBuffer[String] = {
        input.map(_.split("bags")(0).trim)
    }

    def findNeighbors(color: String): Vector[String] = {
        val string = input.find(_.startsWith(color)).getOrElse("")
        //println(string)
        var neighbors = Vector[String]()
        for(c <- colors) {
            if(string.contains(c) && c != color) {
                neighbors = neighbors :+ c
            }
        }
        neighbors
    }

    def createGraph = {
        for(c <- colors) {
            graph.put(c, findNeighbors(c))
        }
    }

    def containsShiny(color: String): Boolean = {
        if(graph(color).isEmpty) {
            //println(s"$color does not contain anything")
            //0
            false
        } else if(graph(color).contains("shiny gold")) {
            //println(s"$color contains shiny gold")
            //1
            true
        } else {
            //println(s"$color contains ${graph(color)}")
            graph(color).map(x => containsShiny(x)).contains(true)
        }
    }

    def numberOfBagsContainShiny: Int = {
        var result = 0
        for(k <- graph.keySet) {
            //println(s"Now check $k")
            val neighbors = graph(k)
            if(k != "shiny gold" && neighbors.contains("shiny gold")) {
                //println(s"$k has gold + 1")
                result += 1
            } else if (k != "shiny gold" && neighbors.map(x => containsShiny(x)).contains(true)) {
                //println(s"$k has gold + 1")
                result += 1
            }
        }
        result
    }

    def run = {
        println("Hello day 7")
        fromFile("input/day7.txt")
        createGraph
        println(numberOfBagsContainShiny + " can contain a shiny bag")
    }
}