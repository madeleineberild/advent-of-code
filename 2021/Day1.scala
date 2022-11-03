package adventofcode

import scala.io.Source
import scala.collection.mutable.ArrayBuffer

class Day1 {
    val fileName = "input/Day1.txt"
    
    // Read input
    val input = new ArrayBuffer[Int]()
    val testInput = List(199, 200, 208, 210, 200, 207, 240, 269, 260, 263)
    
    def fromFile(fileName: String) = {
        for(line <- Source.fromFile(fileName).getLines) {
            input.append(line.toInt)
        }
    }
    
    def run = {
        fromFile(fileName)

        //Problem 1
        var increasing = 0
        var prev = 0
        var current = 1
        
        while(current < input.size) {
            if(input(current) > input(prev)) {
                increasing += 1
            }
            prev +=1
            current +=1
        }
        
        println("Problem 1: Number of increasing: " + increasing)
        
        //Problem 2
        increasing = 0
        prev = 0
        current = 1
        
        val slidingSum = input.toList.sliding(3).toList.map(x => x.sum)
        
        while(current < slidingSum.size) {
            if(slidingSum(current) > slidingSum(prev)) {
                increasing += 1
            }
            prev +=1
            current +=1
        }
        
        println("Problem 2: Number of increasing: " + increasing)
    }
}