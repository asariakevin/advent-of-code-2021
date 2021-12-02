import java.io.File

fun main(){

    var horizontalPosition = 0
    var depth = 0
    var aim = 0
    val listOfInstructions = File("/home/asaria/Desktop/Hie/src/day_two_content.txt").readLines()

    listOfInstructions.forEach{ command ->

        val commandList = command.split(" ")

        when(commandList.first()){
            "forward" ->{
                horizontalPosition+=commandList.last().toInt()
                depth+=(aim * commandList.last().toInt())
            }
            "up" -> aim-=commandList.last().toInt()
            "down" -> aim+=commandList.last().toInt()
        }
    }

    val productOfHorizontalAndDepth = horizontalPosition * depth
    println("product of final horizontal and depth position: $productOfHorizontalAndDepth")
}