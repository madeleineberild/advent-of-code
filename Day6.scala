package adventofcode

import scala.io.Source
import scala.collection.mutable.HashMap

class Day6 {
    var result = ""

    def groups = result.split('|').toVector

    def people = groups.map(_.split(' ').toVector.filter(_.nonEmpty))

    def numberOfPeopleWithCollectedString: Vector[(Int, String)] = people.map(x => (x.length, x.mkString.sorted))

    def fromFile(fileName: String) = {
        for(line <- Source.fromFile(fileName).getLines) {
            if(line == "") {
                result += '|'
            } else {
                result += (' ' + line)
            }
        }
    }

    def charFreq(x: String): HashMap[Char, Int] = {
        val result = new HashMap[Char, Int]
        for(c <- x) {
            if(result.contains(c)) {
                result.put(c, result(c) + 1)
            } else {
                result.put(c, 1)
            }
        }
        result
    }

    def numberByGroup(tuple: (Int, String)): Int = {
        charFreq(tuple._2).toVector.map(_._2).filter( _ == tuple._1).length
    }

    def sumAnyoneSaidYes: Int = {
        groups.map(_.distinct).map(x => x.filterNot(_ == ' ')).map(_.length).sum
    }

    def sumEveryoneSaidYes: Int = {
        numberOfPeopleWithCollectedString.map(x => numberByGroup(x)).sum
    }

    def run = {
        println("Hello day 6")
        fromFile("input/Day6.txt")
        println("The number of questions anyone said yes: " + sumAnyoneSaidYes)
        println("The number of questions everyone said yes: " + sumEveryoneSaidYes)
    }
}