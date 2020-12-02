package adventofcode
import scala.collection.mutable.ArrayBuffer
import scala.io.Source

class Day2 {
    val input = new ArrayBuffer[String]()

    def fromFile(fileName: String) = {
        for(line <- Source.fromFile(fileName).getLines) {
            input.append(line)
        }
    }

    def isValid(x: String): Boolean = {
        val rangeString = x.split(':')(0)
        val password = x.split(':')(1).trim
        val low = rangeString.split('-')(0).toInt
        val letter = rangeString.split('-').mkString.trim.last
        val high = rangeString.split('-')(1).dropRight(1).trim.toInt
        val range = low to high
        val count = password.filter(x => x == letter).size
        range.contains(count)
    }

    def numberOfValid: Int = {
        println(input.size)
        val partial = input.filter(x => isValid(x))
        partial.size
    }

    def run = {
        fromFile("day2.txt")
        println(s"Only $numberOfValid passwords are valid in this database")
    }
}