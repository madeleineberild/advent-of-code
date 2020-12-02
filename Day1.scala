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

    def checkSum(x: Int, y: Int, z: Int): Boolean = {
        x + y + z == 2020
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

    def findThrees: (Int, Int, Int) = {
        var result = (0, 0, 0)
        for(x <- integers; y <- integers; z <- integers) {
            if(checkSum(x, y, z)) result = (x, y, z)
        }
        result
    }

    def run() = {
        //part 1
        fromFile("day1.txt")
        val x = findPair._1
        val y = findPair._2
        println(s"The numbers are $x and $y. The product is ${x * y}")

        //part 2
        val a = findThrees._1
        val b = findThrees._2
        val c = findThrees._3
        println(s"The numbers are $a, $b and $c. The product is ${a*b*c}.")
    }

}