package adventofcode

import scala.io.Source
import scala.collection.mutable.ArrayBuffer
import scala.collection.immutable.HashSet
import scala.collection.mutable.HashMap

class Day4 {
    val input = new ArrayBuffer[String]()
    val numbers = new ArrayBuffer[Int]()
    val boards = new ArrayBuffer[Board]()
    

    def fromFile(fileName: String) = {
        for(line <- Source.fromFile(fileName).getLines) {
            input.append(line)
        }

        val inputSize = input.size

        val numberLine = input(0)
        for(number <- numberLine.split(",")) {
            numbers.append(number.toInt)
        }

        var currentLine = 2
        while(currentLine <= inputSize) {
            var boardNumbers = new ArrayBuffer[Int]
            for(i <- 0 until 5) {
                for(n <- input(currentLine+i).split(" ").filter(_.nonEmpty)) {
                    boardNumbers.append(n.toInt)
                }
            }
            boards.append(new Board(boardNumbers))
            currentLine += 6
        }
    }

    def gameLoop(one: Boolean): (Board, Int) = {
        if(one) {
            var win = false
            var iteration = 0
            var lastDrawn = 0
            var winningBoard = new Board(new ArrayBuffer())
            while(!win && iteration < numbers.size) {
                lastDrawn = numbers(iteration)
                for(b <- boards) {
                    b.call(lastDrawn)
                    if(b.hasBingo) {
                        winningBoard = b
                        win = true
                    }
                }
                iteration += 1
            }
            (winningBoard, lastDrawn)
        } else {
            var win = false
            var iteration = 0
            var lastDrawn = 0
            var boardsLeft = boards.toSet
            var lastWinningBoard = new Board(new ArrayBuffer())
            while(iteration < numbers.size) {
                for(b <- boards) {
                    if(boardsLeft.contains(b)) {
                        b.call(numbers(iteration))
                        if(b.hasBingo) {
                            lastWinningBoard = b
                            lastDrawn = numbers(iteration)
                            boardsLeft -= b
                        }
                    }
                }
                iteration += 1
            }
            (lastWinningBoard, lastDrawn)
        }
    }

    def problemOne(): String = {
        val loop = gameLoop(true)
        val winningBoard = loop._1
        val lastDrawn = loop._2
        winningBoard.calculateUncalled(lastDrawn)
    }

    def problemTwo(): String = {
        val loop = gameLoop(false)
        val winningBoard = loop._1
        val lastDrawn = loop._2
        winningBoard.calculateUncalled(lastDrawn)
    }

    def run() = {
        fromFile("input/Day4.txt")
        println("The solution of problem 1: " + problemOne)
        println("The solution of problem 2: " + problemTwo)
    }
}

class Board(numbers: ArrayBuffer[Int]) {
    override def toString(): String = {
        s"""Numbers: ${numbers.mkString(" ")}
            Called: ${called.mkString(" ")}
            Uncalled: ${uncalled.mkString(" ")}
            HasBingo: $hasBingo"""
    }
    var uncalled = numbers.toSet
    var called = new HashSet[Int]()
    var rows = new Array[Int](5)
    var cols = new Array[Int](5)
 
    def call(number: Int) = {
        if(uncalled.contains(number)) {
            uncalled = uncalled - number
            called = called + number
            val index = numbers.indexOf(number)
            val row = (index/5).toInt
            val col = index % 5
            rows(row) +=1
            cols(col) +=1
        }
    }

    def hasBingo: Boolean = {
        rows.contains(5) || cols.contains(5)
    }

    def calculateUncalled(lastDrawn: Int): String = {
        val result = uncalled.sum * lastDrawn
        result.toString
    }
}
