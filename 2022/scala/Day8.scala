package adventofcode

import scala.io.Source
import scala.collection.mutable.ArrayBuffer

class Day8 {
    val fileName = "input/testinput.txt"

    var input = new ArrayBuffer[String]()
        
    def fromFile(fileName: String) = {
        input = new ArrayBuffer[String]()
        for(line <- Source.fromFile(fileName).getLines) {
            input.append(line)
        }
    }

    def problemOne: Int = {
        val width = input(0).size
        val height = input.size
        var visible = width*2 + height*2 - 4
        val visibilityMatrix = new ArrayBuffer[ArrayBuffer[Int]]()

        for(i <- 1 to height) {
            val insert = new ArrayBuffer[Int]()
            for(j <- 1 to width) {
                insert.append(0)
            }
            visibilityMatrix.append(insert)
        }

        var max = 0
        //view from left
        for(i <- 1 until height - 1) {
            max = input(i)(0).asDigit
            for(j <- 1 until width-1) {
                if(input(i)(j).asDigit > max) {
                    visibilityMatrix(i).update(j, 1)
                    max = input(i)(j).asDigit
                }
            }
        }

        //from above
        for(i <- 1 until width-1) {
            max = input(0)(i).asDigit
            for(j <- 1 until height-1) {
                if(input(j)(i).asDigit > max) {
                    visibilityMatrix(j).update(i, 1)
                    max = input(j)(i).asDigit
                }
            }
        }

        //from the right
        for(i <- 1 until height - 1) {
            max = input(i)(width - 1).asDigit
            for(j <- width-2 to 1 by - 1) {
                if(input(i)(j).asDigit > max) {
                    visibilityMatrix(i).update(j, 1)
                    max = input(i)(j).asDigit
                }
            }
        }

        //from below
        for(i <- 1 until width - 1) {
            max = input(height - 1)(i).asDigit
            for(j <- height - 2 to 1 by - 1) {
                if(input(j)(i).asDigit > max) {
                    visibilityMatrix(j).update(i, 1)
                    max = input(j)(i).asDigit
                }
            }
        }
        for(array <- visibilityMatrix) {
            for(number <- array) {
                if(number == 1) {
                    visible += 1
                }
            }
        }
        visible
    }

    def problemTwo: Int = {
        val scenicScores = new ArrayBuffer[Int]()
        val width = input(0).size
        val height = input.size

        for(row <- 0 until height) {
            for(col <- 0 until width) {
                val score = scenicScore(row, col)
                println(score)
                scenicScores.append(score)
            }
        }

        println(scenicScores.toString())
        scenicScores.max
    }

    def scenicScore(i: Int, j: Int): Int = {
        val width = input(0).size
        val height = input.size
        var row = i
        var col = j
        println(s"Calculating for ${input(i)(j).asDigit}")

        //look up, keep col, decrement row
        println("Looking up")
        var max = input(i)(j).asDigit
        var scoreUp = 0
        var tallest = 0
        row = i - 1
        if(row >= 0) {
            scoreUp += 1
            while(row >= 0 && input(row)(col).asDigit <= max  && input(row)(col).asDigit > tallest) {
                //max = input(row)(col).asDigit
                scoreUp += 1
                tallest = input(row)(col).asDigit
                row -= 1
            }
        }
        println(scoreUp)

        //look down, keep col, increment row
        println("Looking down")
        var scoreDown = 0
        tallest = 0
        row = i + 1
        col = j
        if(row < height) {
            scoreDown += 1
            while(row < height && max >= input(row)(col).asDigit && input(row)(col).asDigit > tallest) {
                //max = input(row)(col).asDigit
                tallest = input(row)(col).asDigit
                scoreDown += 1
                row += 1
            }
        }
        println(scoreDown)

        //look left, keep row, decrement col
        println("Looking left")
        var scoreLeft = 0
        row = i
        col = j - 1
        if(col >= 0) {
            scoreLeft +=1
            while(col >= 0 && max >= input(row)(col).asDigit && input(row)(col).asDigit > tallest) {
                //max = input(row)(col).asDigit
                tallest = input(row)(col).asDigit
                scoreLeft += 1
                col -= 1
            }
        }
        println(scoreLeft)

        //look right, increment i, keep j
        println("Looking right")
        var scoreRight = 0
        tallest = 0
        col = j + 1
        if(col < width) {
            scoreRight += 1
            while(col < width && max >= input(row)(col).asDigit && input(row)(col).asDigit > tallest) {
                //max = input(row)(col).asDigit
                tallest = input(row)(col).asDigit
                scoreRight += 1
                col += 1
            }
        }
        println(scoreRight)
        scoreRight * scoreLeft * scoreDown * scoreUp
    }
    
    def run = {
        fromFile(fileName)
        println(s"Problem 1: $problemOne")
        fromFile(fileName)
        println(s"Problem 2: $problemTwo")
    }
}
