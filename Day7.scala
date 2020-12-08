package adventofcode

import scala.io.Source
import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.HashMap

class Day7 {
    val input = new ArrayBuffer[String]
    val colorGraph = new HashMap[String, Vector[String]]
    val numberGraph = new HashMap[String, Vector[Int]]
    val colorAndNumber = new HashMap[String, Vector[(Int, String)]]

    def fromFile(fileName: String) = {
        for(line <- Source.fromFile(fileName).getLines) {
            input.append(line)
        }
    }

    def colors: ArrayBuffer[String] = {
        input.map(_.split("bags")(0).trim)
    }

    def findNumbers(color: String): Vector[Int] = {
        val string = input.find(_.startsWith(color)).getOrElse("")
        string.split(' ').toVector.map(x => x.filter(_.isDigit)).filter(_.nonEmpty).map(_.toInt)
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

    def createColorGraph = {
        for(c <- colors) {
            colorGraph.put(c, findNeighbors(c))
        }
    }

    def createNumberGraph = {
        for(c <- colors) {
            numberGraph.put(c, findNumbers(c))
        }
    }

    //vi kan inte vara säkra på att siffrorna och numrena hamnar i samma ordning
    def createCombined = {
        for(color <- colors) {
            for(n <- numberGraph(color); c <- colorGraph(color)) {
                if(input.find(_.startsWith(color)).getOrElse("").contains(s"$n $c")) {
                    println("Kommer hit")
                    if(colorAndNumber.isDefinedAt(color)) {
                        colorAndNumber.put(color, colorAndNumber(c) :+ (n, c))
                    } else {
                        colorAndNumber.put(color, Vector((n, c)))
                    }
                }
            }
        }
    }

    def containsShiny(color: String): Boolean = {
        if(colorGraph(color).isEmpty) {
            //println(s"$color does not contain anything")
            //0
            false
        } else if(colorGraph(color).contains("shiny gold")) {
            //println(s"$color contains shiny gold")
            //1
            true
        } else {
            //println(s"$color contains ${colorGraph(color)}")
            colorGraph(color).map(x => containsShiny(x)).contains(true)
        }
    }

    def numberOfBagsContainShiny: Int = {
        var result = 0
        for(k <- colorGraph.keySet) {
            //println(s"Now check $k")
            val neighbors = colorGraph(k)
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

/*     def recursiveCalc(pair: (Int,String)): Int = {
        if(pair.isEmpty) {
            println("Tom väska")
            1
        } else {
            println("ej tom väska")
            pair._1 * calculate(pair._2)
        }
    } */
    
    
    def calculate(color: String, no: Int): Int = {
        var sum = 0
        if(!colorAndNumber.isDefinedAt(color)) {
            println("tom väska")
            no
        } else {
            colorAndNumber(color).map(x => calculate(x._2, x._1)).sum + no
        }
    }

    def _calculate(color: String, no: Int): String = {
        if(colorAndNumber(color).isEmpty) {
            (color * no)
        } else {
            val string = (for(p <- colorAndNumber(color)) yield _calculate(p._2, p._1))
            //val newString = no * string.mkString(" ")
            //color + newString
            "hej"
        }
    }

    def run = {
        println("Hello day 7")
        //fromFile("input/day7.txt")
        fromFile("testinput.txt")
        createColorGraph
        println(numberOfBagsContainShiny + " bags can contain a shiny bag")
        //println(findNumbers(colors(0)))
        createNumberGraph
        createCombined
        println(colorAndNumber)
        println("Summan av väskor är " + calculate("shiny gold", 1))
    }
}

//too high: 207483