package adventofcode

import scala.io.Source
import scala.collection.mutable.ArrayBuffer

val fileName = "input/Day3.txt"

// Read input
val input = new ArrayBuffer[String]()

for(line <- Source.fromFile(fileName).getLines) {
    input.append(line)
}

//Calculate gamma rate
val numberOfLines = input.size
val sizeOfBinary = input(0).length
var pos = Vector.fill(sizeOfBinary)(0)

def parsePos(s: String) = {
    for(i <- 0 until sizeOfBinary) {
        if(s(i) == '1') {
            pos = pos.updated(i, pos(i) + 1)
        }
    }
}

input.foreach(b => parsePos(b))

var gammaBin = ""

for(i <- 0 until sizeOfBinary) {
    if(pos(i) > numberOfLines/2) {
        gammaBin = gammaBin + "1"
    } else {
        gammaBin = gammaBin + "0"
        }
}

val gamma = Integer.parseInt(gammaBin, 2)

println("Gamma rate is: " + gamma)

//Calculate epsilon rate
var epsilonBin = ""
gammaBin.foreach(c => if(c == '1') {epsilonBin = epsilonBin + "0"} else {epsilonBin = epsilonBin + "1"})

val epsilon = Integer.parseInt(epsilonBin, 2)

println("Epsilon rate is: " + epsilon)

println("Problem 1: " + gamma * epsilon)