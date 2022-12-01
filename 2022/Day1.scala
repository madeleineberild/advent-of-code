package adventofcode

import scala.io.Source
import scala.collection.mutable.ArrayBuffer

class Day1 {
    val fileName = "input/day1.txt"
    
    def run = {
        var number = 0
        var max = 0
        var food = new ArrayBuffer[Int]()

        for(line <- Source.fromFile(fileName).getLines) {
            if(line.isEmpty()) {
                if(number > max) {
                    max = number
                }
                food.append(number)
                number = 0
            } else {
                number += line.toInt
            }
        }
        food.append(number)

        println(s"Problem 1: $max")

        food = food.sortWith(_ > _)
        //println(food.toString())

        max = food(0) + food(1) + food(2)
        println(s"Problem 2: $max")

    }
}
