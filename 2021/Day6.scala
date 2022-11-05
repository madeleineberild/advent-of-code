package adventofcode

import scala.io.Source
import scala.collection.mutable.ArrayBuffer

class Day6 {
    val fileName = "input/Day6.txt"
    
    val input = new ArrayBuffer[Int]()
    
    def fromFile(fileName: String) = {
        for(line <- Source.fromFile(fileName).getLines) {
            for(number <- line.split(",")) {
                input.append(number.toInt)
            }
        }
    }

    def loop(fish: ArrayBuffer[Int]): ArrayBuffer[Int] = {
        val result = new ArrayBuffer[Int]()
        val fishBabies = new ArrayBuffer[Int]()
        for(f <- fish) {
            if(f == 0) {
                result.append(6)
                fishBabies.append(8)
            } else {
                result.append(f - 1)
            }
        }
        result.appendAll(fishBabies)
        result
    }

    def problemOne(days: Int) = {
        var fishStatus = new ArrayBuffer[Int]()
        fishStatus = input
        for(i <- 0 until days) {
            fishStatus = loop(fishStatus)
        }
        fishStatus.length
    }

    def problemTwo(days: Int) = {
        //new approach, as heap memory runs out with problemOne solution above
        //very optimized
        val ages = new Array[Long](9)
        for(i <- input) {
            ages(i) += 1
        }
        for(i <- 0 until days) {
            val zero = ages(0)
            for(i <- 1 to 8) {
                ages(i-1) = ages(i)
            }
            //add new fish
            ages(8) = zero
            ages(6) += zero
        }
        ages.sum
    }
    
    def run() = {
        fromFile(fileName)
        println("Solution of problem one: " + problemOne(80).toString())
        println("Solution of problem two: " + problemTwo(256).toString())
    }
}
