package adventofcode

import scala.io.Source
import scala.collection.mutable.ArrayBuffer

class Day7 {
    val fileName = "input/day7.txt"

    val input = new ArrayBuffer[String]()
    val homeDir = new Folder("/")
    var current = homeDir
        
    def fromFile(fileName: String) = {
        for(line <- Source.fromFile(fileName).getLines) {
            input.append(line)
        }
                var i = 0
        var added = false
        while(i < input.size) {
            added = false
            if(input(i) == "$ cd /")
            {
                current = homeDir
            }
            else if(input(i) == "$ cd ..")
            {
                current = current.parent.get
            }
            else if(input(i).startsWith("$ cd"))
            {
                val name = current + input(i).split(" cd ")(1) + "/"
                current = current.getFolder(name)
            }
            else if(input(i).startsWith("$ ls"))
            {
                i += 1
                added = true
                while(i < input.size && !input(i).startsWith("$"))
                {
                    if(input(i).startsWith("dir")) {
                        val name = current.name + input(i).split(" ")(1) + "/" 
                        current.folderList.append(new Folder(name, Some(current)))
                    } else {
                        val size = input(i).split(" ")(0).toInt
                        val name = current.name +  input(i).split(" ")(1)
                        current.filesList.append((name, size))
                    }
                    i += 1
                }
            }
            if(!added) {
                i += 1
            }
        }
    }

    def problemOne: Int = {
        var sizes = homeDir.folderSizes
        sizes.append(homeDir.size)
        sizes = sizes.filter(_ <= 100000)
        println(sizes.toString())
        sizes.sum
    }

    def problemTwo: Int = {
        val totalSize = 70000000
        val targetSize = 30000000
        val free = totalSize - homeDir.size
        val min = targetSize - free //need to delete
        var sizes = homeDir.folderSizes
        sizes.append(homeDir.size)
        sizes = sizes.filter(_ >= min)
        sizes.min
    }
    
    def run = {
        fromFile(fileName)
        println(s"Problem 1: $problemOne")
        //fromFile(fileName)
        println(s"Problem 2: $problemTwo")
    }
}

class Folder(var name: String, val parent: Option[Folder] = None) {
    var folderList = new ArrayBuffer[Folder]()
    var filesList = new ArrayBuffer[(String, Int)]()
    private var _size = -1
    
    def size: Int = {
        if(_size != -1)
        {
            _size;
        } else {
            var number = 0
            for(file <- filesList)
            {
                number += file._2
            }
            for(folder <- folderList)
            {
                number += folder.size
            }
            _size = number
            _size
        }
    }

    def getFolder(name: String): Folder = {
        folderList.find(p => p.name == name).get
    }

    def print(ind: String): Unit = {
        println(s"$ind- $name (dir)")
        val newind = ind + "  "
        for(file <- filesList) {
            println(s"$newind- ${file._1} (file, size=${file._2})")
        }
        for(folder <- folderList) {
            folder.print(newind)
        }
    }

    def folderSizes: ArrayBuffer[Int] = {
        val result = new ArrayBuffer[Int]()
        for(folder <- folderList) {
            result.append(folder.size)
            result.addAll(folder.folderSizes)
        }
        result
    }

    override def toString(): String = name
}
