package adventofcode

import scala.io.Source
import scala.collection.mutable.ArrayBuffer

class Day2 {
    val fileName = "input/Day2.txt"
    
    // Read input
    val input = new ArrayBuffer[(String, Int)]()
    
    def fromFile(fileName: String) = {
        for(line <- Source.fromFile(fileName).getLines) {
            val splitLine = line.split(" ")
            val pair = (splitLine(0), splitLine(1).toInt)
            input.append(pair)
        }
    }

    def run() = {
        fromFile(fileName)

        // Position parameters
        var horizontal = 0
        var depth = 0
        
        // Function for moving
        def move(pair: (String, Int)): Unit = {
            if(pair._1 == "forward") {
                horizontal += pair._2
            }
            if(pair._1 == "up") {
                depth -= pair._2
            }
            if(pair._1 == "down") {
                depth += pair._2
            }
        }
        
        //Problem 1
        input.foreach(x => move(x))
        val result1 = horizontal * depth
        println("Problem 1: The final product is: " + result1)
        
        //Problem 2
        horizontal = 0
        depth = 0
        var aim = 0
        
        def moveAgain(pair: (String, Int)): Unit = {
            if(pair._1 == "forward") {
                horizontal += pair._2
                depth += (pair._2 * aim)
            }
            if(pair._1 == "up") {
                aim -= pair._2
            }
            if(pair._1 == "down") {
                aim += pair._2
            }
        }
        
        input.foreach(x => moveAgain(x))
        val result2 = horizontal * depth
        println("Problem 2: The final product is: " + result2)
    }
}