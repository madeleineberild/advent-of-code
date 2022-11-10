package adventofcode

import scala.io.Source
import scala.collection.mutable.ArrayBuffer

class Day8 {
    val fileName = "input/testinput.txt"
    
    val input = new ArrayBuffer[Int]()
    
    def fromFile(fileName: String) = {
        for(line <- Source.fromFile(fileName).getLines) {
            for(number <- line.split(",")) {
                input.append(number.toInt)
            }
        }
    }


    def run() = {
        fromFile(fileName)
        println("Solution of problem one: " + problemOne().toString())
        println("Solution of problem two: " + problemTwo().toString())
    }

}
