package adventofcode

import scala.io.Source
import scala.collection.mutable.ArrayBuffer

class Day2 {
    val fileName = "input/day2.txt"

    val input = new ArrayBuffer[(Char, Char)]()
        
    def fromFile(fileName: String) = {
        for(line <- Source.fromFile(fileName).getLines) {
            var split = line.split(" ")
            val them = split(0)(0)
            val me = split(1)(0)
            input.append((them, me))
        }
    }
    
    def run = {
        fromFile(fileName)

        var points = 0
        var (lose, draw, win) = (0, 3, 6)
        var (rock, paper, scissors) = (1, 2, 3)

        for((them, me) <- input) {
            if(me == 'X') { //rock
                points += rock
                if(them == 'A') {
                    points += draw
                }
                if(them == 'B') {
                    points += lose
                }
                if(them == 'C') {
                    points += win
                }
            } else if(me == 'Y') { //paper
                points += paper
                if(them == 'A') {
                    points += win
                }
                if(them == 'B') {
                    points += draw
                }
                if(them == 'C') {
                    points += lose
                }
            } else if(me == 'Z') { //scissors
                points += scissors
                if(them == 'A') {
                    points += lose
                }
                if(them == 'B') {
                    points += win
                }
                if(them == 'C') {
                    points += draw
                }
            }
        }

        println(s"Problem 1: $points")

        points = 0
        for((them, outcome) <- input) {
            if(them == 'A') { //they play rock
                if(outcome == 'X') { //lose => scissors
                    points += lose + scissors
                }
                if(outcome == 'Y') { //draw => rock
                    points += draw + rock
                }
                if(outcome == 'Z') { //win => paper
                    points += win + paper
                }
            } else if(them == 'B') { // they play paper
                if(outcome == 'X') { // lose => rock
                    points += lose + rock
                }
                if(outcome == 'Y') { //draw => paper
                    points += draw + paper
                }
                if(outcome == 'Z') { // win => scissors
                    points += win + scissors
                }
            } else if (them == 'C') { // they play scissors
                if(outcome == 'X') { //lose => paper
                    points += lose + paper
                }
                if(outcome == 'Y') { //draw => scissors
                    points += draw + scissors
                }
                if(outcome == 'Z') { //win => rock
                    points += win + rock
                }
            }
        }

        println(s"Problem 2: $points")
    }
}
