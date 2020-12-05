package adventofcode

import scala.io.Source
import scala.collection.ArrayBuffer

class Day5 {
    val input = new ArrayBuffer[String]()

    def fromFile(fileName: String) = {
        for(line <- Source.fromFile(fileName).getLines) {
            input.append(line)
        }
    }

    def run = {
        println("Hello day 5")
        fromFile
        println(input.toString)
    }

}

case class BoardingPass(input: String) {
    val seatRow = row
    val seatCol = col

    def seatID = seatRow * 8 + seatCol

    def row: Int = {
        
    }

    def col: Int = {

    }
}