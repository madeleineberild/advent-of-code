package adventofcode

import scala.collection.mutable.ArrayBuffer
import scala.io.Source

class Day3 {
    val input = new ArrayBuffer[String]
    val start = 0
    var current = 0
    var length = 31
    var path = new ArrayBuffer[Char]

    def countTrees: Long = {
        path.filter(_ == '#').size.toLong
    }

    def generatePos(step: Int): Int = {
        current = (current + step) % length
        current
    }

    def parseOneDown(step: Int) = {
        current = start
        path.append(input(start)(0))
        for(x <- 1 until input.size) {
            path.append(input(x)(generatePos(step)))
        }
    }
    
    def parseTwoDown(step: Int) = {
        current = start
        path.append(input(start)(0))
        var x = 2
        println("Input size är: " + input.size)
        while(x < input.size) {
            path.append(input(x)(generatePos(step)))
            x = x + 2
        }
    }
    
    def fromFile(fileName: String): Unit = {
        for(line <- Source.fromFile(fileName).getLines) {
            input.append(line)
        }
    }

    def oneDownOne: Long = {
        path = ArrayBuffer.empty
        parseOneDown(1)
        countTrees
    }

    def threeDownOne: Long = {
        path = ArrayBuffer.empty
        parseOneDown(3)
        countTrees
    }

    def fiveDownOne: Long = {
        path = ArrayBuffer.empty
        parseOneDown(5)
        countTrees
    }

    def sevenDownOne: Long = {
        path = ArrayBuffer.empty
        parseOneDown(7)
        countTrees
    }

    def oneDownTwo: Long = {
        path = ArrayBuffer.empty
        parseTwoDown(1)
        countTrees
    }

    def run = {
        println("hello day 3")
        fromFile("input/day3.txt")
        val a = oneDownOne
        val b = threeDownOne
        val c = fiveDownOne
        val d = sevenDownOne
        val e = oneDownTwo
        val result: Long = a * b * c * d * e
        println(s"The result is $result")
    }

}