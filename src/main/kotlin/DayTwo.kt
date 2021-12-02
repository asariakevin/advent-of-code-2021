import java.io.File

fun main(){

    var horizontalPosition = 0
    var depth = 0
    val listOfInstructions = File("/home/asaria/Desktop/Hie/src/day_two_content.txt").readLines()

    listOfInstructions.forEach{ command ->

        var commandList = command.split(" ")

        when(commandList.first()){
            "forward" -> horizontalPosition+=commandList.last().toInt()
            "up" -> depth-=commandList.last().toInt()
            "down" -> depth+=commandList.last().toInt()
        }
    }

    val productOfHorizontalAndDepth = horizontalPosition * depth
    println("product of final horizontal and depth position: $productOfHorizontalAndDepth")
}