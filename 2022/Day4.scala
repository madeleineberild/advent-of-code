package adventofcode

import scala.io.Source
import scala.collection.mutable.ArrayBuffer

class Day4 {
    val fileName = "input/day4.txt"

    val input = new ArrayBuffer[(List[Int], List[Int])]()
        
    def fromFile(fileName: String) = {
        for(line <- Source.fromFile(fileName).getLines) {
            val ranges = line.split(",").toList
            val (r1, r2) = (ranges(0).split("-")(0).toInt, ranges(0).split("-")(1).toInt)
            val (r3, r4) = (ranges(1).split("-")(0).toInt, ranges(1).split("-")(1).toInt)
            val first = Range.inclusive(r1, r2).toList
            val last = Range.inclusive(r3, r4).toList
            input.append((first, last))
        }
    }

    def problemOne: Int = {
        var number = 0
        for((l1, l2) <- input) {
            if(l1.forall(l2.contains) || l2.forall(l1.contains)) {
                number += 1
            }
        }
        number
    }

    def problemTwo: Int = {
        var number = 0
        for((l1, l2) <- input) {
            var i = 0
            var break = false
            while(i < l1.length && !break) {
                if(l2.contains(l1(i))) {
                    break = true
                    number += 1
                }
                i+=1
            }
        }
        number
    }
    
    def run = {
        fromFile(fileName)
        println(s"Problem 1: $problemOne")
        println(s"Problem 2: $problemTwo")
    }
}
