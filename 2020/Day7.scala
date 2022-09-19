package adventofcode

import scala.io.Source
import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.HashMap

class Day7 {
    val input = new ArrayBuffer[String]

    def fromFile(fileName: String) = {
        for(line <- Source.fromFile(fileName).getLines) {
            input.append(line)
        }
    }

    //PART 1
//You have a shiny gold bag.
//If you wanted to carry it in
//at least one other bag, how
//many different bag colors would
//be valid for the outermost bag?
    def solvePartOne: Int = {
        //color -> colors
        //find all that can lead to shiny gold bag
        //count
        val colorMapping = mapColors
        
    }

    def mapColors: HashMap = {
        val result = new HashMap()
        for(line <- input) {
            
        }
    }

    def solvePartTwo: Int = {

    }

    def run = {
        println("Hello day 7")
        println("Part one: " + solvePartOne)
        println("Part two: " + solvePartTwo)
    }
}
