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

    def checkSum(x: Int, y: Int): Boolean = {
        x + y == 2020
    }

    def findPair: (Int, Int) = {
        var result = (0,0)
        for(x <- integers) {
            for(y <- integers) {
                if(checkSum(x, y)) {
                    result = (x, y)
                }
            }
        }
        result
    }

    def run() = {
        fromFile("day1.txt")
        val x = findPair._1
        val y = findPair._2
        println(s"The numbers are $x and $y. The sum is ${x * y}")
    }

}