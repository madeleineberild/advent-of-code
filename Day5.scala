package adventofcode

import scala.io.Source
import scala.collection.mutable.ArrayBuffer

class Day5 {
    val input = new ArrayBuffer[String]()
    val boardingPasses = new ArrayBuffer[BoardingPass]()

    val maxSeatID: Int = 127 * 8 + 7

    def fromFile(fileName: String) = {
        for(line <- Source.fromFile(fileName).getLines) {
            input.append(line)
        }
    }

    def generateBoardingPasses = {
        for(i <- input) {
            boardingPasses.append(BoardingPass(i))
        }
    }

    def findMaxSeatID: Int = {
        allExistentSeatIDs.max
    }

    def allExistentSeatIDs: Vector[Int] = boardingPasses.map(x => x.seatID).toVector

    val allPossibleSeatIDs: Vector[Int] = {
        val res = for(row <- 0 to 127; col <- 0 to 7) yield row * 8 + col
        res.toVector
    }

    def compare: Vector[Int] = {
        allPossibleSeatIDs.diff(allExistentSeatIDs)
    }

    def run = {
        fromFile("input/day5.txt")
        generateBoardingPasses
        println("The highest seatID is: " + findMaxSeatID)
        println("The empty seats are the following, now you do the work: \n" + compare.toString)
    }

}

case class BoardingPass(input: String) {
    val seatRow = row(input.substring(0, 7))
    val seatCol = col(input.substring(7, 10))

    def seatID = seatRow * 8 + seatCol

    def recursiveGen(range: Range, seq: String): Int = {
        var i = 0
        var pivot = range.length / 2
        var newRange = range
        var result = -1
        while(i < seq.length) {
            if(newRange.length == 2) {
                if(seq(i).equals('F') || seq(i).equals('L')) {
                    result = newRange.head
                } else {
                    result = newRange(1)
                }
            } else {
                if(seq(i).equals('F') || seq(i).equals('L')) {
                    newRange = newRange.take(pivot)
                } else {
                    newRange = newRange.drop(pivot)
                }
                pivot = newRange.length / 2
            }
            i += 1
        }
        result
    }

    def row(input: String): Int = {
        recursiveGen(0 to 127, input)
    }

    def col(input: String): Int = {
        recursiveGen(0 to 7, input)
    }
}