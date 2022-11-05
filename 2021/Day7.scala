package adventofcode

import scala.io.Source
import scala.collection.mutable.ArrayBuffer

class Day7 {
    val fileName = "input/Day7.txt"
    
    val input = new ArrayBuffer[Int]()
    
    def fromFile(fileName: String) = {
        for(line <- Source.fromFile(fileName).getLines) {
            for(number <- line.split(",")) {
                input.append(number.toInt)
            }
        }
    }

    def problemOne() = {
        var leastFuel = (Int.MaxValue, Int.MaxValue)
        val minPos = input.min
        val maxPos = input.max
        val cost = new ArrayBuffer[Int]()
        cost.appendAll(input)
        for(i <- minPos to maxPos) {
            for(j <- 0 until input.size) {
                cost(j) = Math.abs(input(j) - i)
            }
            if(cost.sum < leastFuel._2) {
                leastFuel = (i, cost.sum)
            }
        }
        println(leastFuel.toString())
        leastFuel._2
    }

    def problemTwo() = {
        var leastFuel = (Int.MaxValue, Int.MaxValue)
        val minPos = input.min
        val maxPos = input.max
        val cost = new ArrayBuffer[Int]()
        cost.appendAll(input)
        for(i <- minPos to maxPos) {
            for(j <- 0 until input.size) {
                val steps = Math.abs(input(j) - i)
                cost(j) = Range.inclusive(0, steps).sum
            }
            if(cost.sum < leastFuel._2) {
                leastFuel = (i, cost.sum)
            }
        }
        println(leastFuel.toString())
        leastFuel._2
    }

    def run() = {
        fromFile(fileName)
        println("Solution of problem one: " + problemOne().toString())
        println("Solution of problem two: " + problemTwo().toString())
    }

}
