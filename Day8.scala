package adventofcode

import scala.io.Source
import scala.collection.mutable.Set

class Day8 {
    var input = Vector[String]()
    var cmdLines = Vector[(String, String, Int)]()
    var tempcmdLines = Vector[(String, String, Int)]()
    var accumulated = 0
    var pc = 0
    var alreadyRun = Set[Int](0)
    var finish = false

    def fromFile(fileName: String) = {
        for(line <- Source.fromFile(fileName).getLines) {
            input = input :+ line
        }
    }

    def constructcmdLines = {
        for(i <- input) {
            val res = (i.take(3), i(4).toString, i.drop(5).toInt)
            cmdLines = cmdLines :+ res
        }
    }

    def incPC(arg: Int = 1) = {
        if(!alreadyRun.add(pc+arg)) {
            //println("Finished without terminating")
            finish = true
        }
        if((pc + arg) >= cmdLines.length) {
            println("Finished terminating")
            finish = true
        }
        pc += arg
    }

    def acc(op: String, arg: Int) = {
        if(op == "+") {
            accumulated += arg
        } else {
            accumulated -= arg
        }
        incPC()
    }

    def nop = {
        incPC()
    }

    def jmp(op: String, arg: Int) = {
        if(op == "+") {
            incPC(arg)
        } else {
            incPC(-arg)
        }
    }

    def loopProblem1(cmdLines: Vector[(String, String, Int)]) = {
        accumulated = 0
        pc = 0
        alreadyRun = Set[Int](0)
        finish = false
        while(!finish) {
            val cmd = cmdLines(pc)
            val ins = cmd._1
            val op = cmd._2
            val arg = cmd._3
            ins.toUpperCase match {
                case "JMP" => jmp(op, arg)
                case "NOP" => nop
                case "ACC" => acc(op, arg)
            }
        }
        println(accumulated + "\n")
    }

    def loopProblem2 = {
        tempcmdLines = cmdLines
        for(i <- 0 until tempcmdLines.length) {
            tempcmdLines = cmdLines
            val string = tempcmdLines(i)
            if(string._1 == "nop") {
                tempcmdLines = tempcmdLines.updated(i, ("jmp", string._2, string._3))
            } else if (string._1 == "jmp") {
                tempcmdLines = tempcmdLines.updated(i, ("nop", string._2, string._3))
            }
            //println(tempcmdLines)
            //println(s"Executing change $i")
            loopProblem1(tempcmdLines)
/*             try {
                println(s"Executing change $i")
                loopProblem1(tempcmdLines
        )
            } catch {
                case _ => println("Did not finish")
            } */
        }
    }

    def run = {
        println("Hello day 8")
        //fromFile("testinput.txt")
        fromFile("input/day8.txt")
        constructcmdLines
        println(cmdLines)
        loopProblem1(cmdLines)
        loopProblem2
    }
}