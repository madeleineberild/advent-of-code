package adventofcode

import scala.io.Source
import scala.collection.mutable.ArrayBuffer

class Day1 {
    val integers = new ArrayBuffer[Int]()

    def fromFile(fileName: String) = {
        for(line <- Source.fromFile(fileName).getLines) {
            integers.append(line.toInt)
        }
    }

    def run() = {
        fromFile("day1.txt")
        
    }

}