package adventofcode

import scala.io.Source
import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.Stack
import scala.collection.mutable.Map

class Day6 {
    val fileName = "input/day6.txt"

    var input = ""
        
    def fromFile(fileName: String) = {
        for(line <- Source.fromFile(fileName).getLines) {
            input += line
        }
    }

    def problemOne: Int = {
        var number = 4
        var done = false
        while(number < input.length() && !done) {
            var substring = input.substring(number - 4, number).toSet
            if(substring.size == 4) {
                done = true
            } else {
                number += 1
            }
        }
        number
    }

    def problemTwo: Int = {
        var number = 14
        var done = false
        while(number < input.length() && !done) {
            var substring = input.substring(number - 14, number).toSet
            if(substring.size == 14) {
                done = true
            } else {
                number += 1
            }
        }
        number
    }
    
    def run = {
        fromFile(fileName)
        println(s"Problem 1: $problemOne")
        fromFile(fileName)
        println(s"Problem 2: $problemTwo")
    }
}
