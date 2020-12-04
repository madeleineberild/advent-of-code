package adventofcode

import scala.io.Source
import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.HashMap

class Day4 {
    var input: String = ""
    val passports = new ArrayBuffer[Passport]

    def fromFile(fileName: String): Unit = {
        for(line <- Source.fromFile(fileName).getLines) {
            if(line.isEmpty) {
                input += '|'
            } else {
                input += (' ' +line)
            }
        }
    }

    def makePassports = {
        val inputVector = input.split('|').toVector
        for(i <- inputVector.indices) passports.append(Passport(inputVector(i)))
    }

    def count = {
        passports.foreach(x => x.initiate)
        passports.filter(_.isValid).size
    }

    def run = {
        fromFile("input/Day4.txt")
        makePassports
        println("Number of valid passports: " + count)
    }
}

case class Passport(input: String) {
    val contents = new HashMap[String, String]

    def initiate = {
        val parseString = input.split(' ').toVector.filter(_.nonEmpty)
        for(s <- parseString) {
            val keyValue = s.split(':').toVector
            contents.put(keyValue(0), keyValue(1))
        }
    }

    def isValid(): Boolean = {
        contents.keySet.size == 8 || {
            contents.keySet.size == 7 && !contents.keySet("cid")
        }
    }
}