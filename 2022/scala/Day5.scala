package adventofcode

import scala.io.Source
import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.Stack
import scala.collection.mutable.Map

class Day5 {
    val fileName = "input/day5.txt"

    var crates = new ArrayBuffer[Stack[Char]]()
    var instructions = new ArrayBuffer[(Int, Int, Int)]
        
    def fromFile(fileName: String) = {
        crates = new ArrayBuffer[Stack[Char]]()
        instructions = new ArrayBuffer[(Int, Int, Int)]()
        val lines = Source.fromFile(fileName).getLines.toList
        val noStacks = (lines(0).length() + 1) / 4
        var stackInstructions = new ArrayBuffer[String]()
        for(i <- 0 until noStacks) {
            crates.append(new Stack[Char]())
        }
        var line = 0
        while(!lines(line).startsWith(" 1 ")) {
            stackInstructions.append(lines(line))
            line += 1
        }
        for(i <- (0 until stackInstructions.length).reverse) {
            for(j <- 0 until noStacks) {
                val index = j*4+1
                if(stackInstructions(i)(index) != ' ') {
                    crates(j).push(stackInstructions(i)(index))
                }
            }
        }
        for(i <- line+2 until lines.length) {
            val split = lines(i).split(" from ")
            val (no, from, to) = (split(0).split(" ")(1).toInt, 
                                  split(1).split(" to ")(0).toInt, 
                                  split(1).split(" to ")(1).toInt)
            instructions.append((no, from, to))
        }
    }

    def problemOne: String = {
        for(instruction <- instructions) {
            for(i <- 0 until instruction._1) {
                val move = crates(instruction._2-1).pop()
                crates(instruction._3-1).push(move)
            }
        }
        var result = ""
        for(stack <- crates) {
            result += stack.pop()
        }
        result
    }

    def problemTwo: String = {
        for(instruction <- instructions) {
            if(instruction._1 == 1) {
                val move = crates(instruction._2-1).pop()
                crates(instruction._3-1).push(move)
            } else {
                var moving = new Stack[Char]()
                for(i <- 0 until instruction._1) {
                    moving.push(crates(instruction._2-1).pop())
                }
                for(i <- 0 until instruction._1) {
                    crates(instruction._3-1).push(moving.pop())
                }
            }
        }
        var result = ""
        for(stack <- crates) {
            result += stack.pop()
        }
        result
    }
    
    def run = {
        fromFile(fileName)
        println(s"Problem 1: $problemOne")
        fromFile(fileName)
        println(s"Problem 2: $problemTwo")
    }
}
