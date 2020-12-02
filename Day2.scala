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

    def isValidPart1(x: String): Boolean = {
        val rangeString = x.split(':')(0)
        val password = x.split(':')(1).trim
        val low = rangeString.split('-')(0).toInt
        val letter = rangeString.split('-').mkString.trim.last
        val high = rangeString.split('-')(1).dropRight(1).trim.toInt
        val range = low to high
        val count = password.filter(x => x == letter).size
        range.contains(count)
    }

    def isValidPart2(x: String): Boolean = {
        val rangeString = x.split(':')(0)
        val password = x.split(':')(1).trim
        val px = rangeString.split('-')(0).toInt - 1
        val py = rangeString.split('-')(1).dropRight(1).trim.toInt - 1
        val letter = rangeString.split('-').mkString.trim.last
        val firstpos = password(px).equals(letter)
        val secondpos = password(py).equals(letter)

        xor(firstpos, secondpos)
    }

    def xor(x: Boolean, y: Boolean): Boolean = {
        (x || y) && !(x && y)
    }

    def numberOfValid: Int = {
        val partial = input.filter(x => isValidPart2(x))
        partial.size
    }

    def run = {
        fromFile("day2.txt")
        println(s"Only $numberOfValid passwords are valid in this database")
    }
}