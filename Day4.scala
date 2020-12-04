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
        val validOne = passports.filter(_.isValidPartOne)
        val validtwo = validOne.filter(_.isValidPartTwo)
        validtwo.size
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

    def isValid: Boolean = {
        isValidPartOne && isValidPartTwo
    }

    def isValidPartOne: Boolean = {
        contents.keySet.size == 8 || {
            contents.keySet.size == 7 && !contents.keySet("cid")
        }
    }

    def isValidPartTwo: Boolean = {
        !contents.keySet.map(x => validField(x)).contains(false)
    }

    def validField(field: String): Boolean = {
        field.toUpperCase match {
            case "BYR" => contents(field).toInt <= 2002 && contents(field).toInt >= 1920
            case "IYR" => contents(field).toInt <= 2020 && contents(field).toInt >= 2010
            case "EYR" => contents(field).toInt <= 2030 && contents(field).toInt >= 2020
            case "HGT" => { val hgt = contents(field)
                            val int = hgt.substring(0, hgt.length - 2).toInt
                            if(hgt.contains("cm")) {
                                int <= 193 && int >= 150
                            } else if (hgt.contains("in")) {
                                int <= 76 && int >= 59
                            } else {
                                false
                            }
                        }
            case "HCL" => { val hcl = contents(field)
                            val chars = Set('0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                                            'a', 'b', 'c', 'd', 'e', 'f')
                            def charsOk: Boolean = {
                                !hcl.map(x => chars(x)).tail.contains(false)
                            }
                            hcl(0) == '#' && hcl.length == 7 && charsOk
                        }
            case "ECL" => Vector("amb", "blu", "brn", "gry", "grn", "hzl", "oth").contains(contents(field))
            case "PID" => contents(field).filter(_.isDigit).size == 9
            case "CID" => true
        }
    }
}