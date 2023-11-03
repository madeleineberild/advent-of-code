package adventofcode

import scala.io.Source
import scala.collection.mutable.ArrayBuffer

class Day3 {
    val fileName = "input/day3.txt"

    //each compartment is half the input
    //a-z => 1-26, A-Z => 27-52
    //Problem 1: find items in both and combine their values
    //Problem 2: find unique items in groups by 3, combine all group values 
    val input = new ArrayBuffer[(String, String)]()
    var lines = new ArrayBuffer[String]()
        
    def fromFile(fileName: String) = {
        for(line <- Source.fromFile(fileName).getLines) {
            lines.append(line)
            //println(input2.toString())
            val length = line.length() / 2
            val split = (line.substring(0, length), line.substring(length, 2*length))
            input.append(split)
        }
    }

    def value(c: Char): Int = {
        if(c.isLower) {
            c.toInt - 96
        } else {
            c.toInt - 64 + 26
        }
    }

    def problemOne: Int = {
        var number = 0
        for((xs, ys) <- input) {
            val overlap = xs.intersect(ys).distinct
            for(x <- overlap) {
                number += value(x)
            }
        }
        number
    }

    def problemTwo: Int = {
        var number = 0
        var list = lines.toList.grouped(3).toList
        for(x <- list) {
            var overlap = x(0).intersect(x(1)).distinct
            overlap = overlap.intersect(x(2)).distinct
            for(y <- overlap) {
                number += value(y)
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
