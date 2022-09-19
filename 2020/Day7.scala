package adventofcode

import scala.io.Source

class Day7 {
    val input = new ArrayBuffer[String]

    def fromFile(fileName: String) = {
        for(line <- Source.fromFile(fileName).getLines) {
            input.append(line)
        }
    }

    
}
