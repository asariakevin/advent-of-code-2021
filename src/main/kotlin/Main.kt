import java.io.File

fun main(){
    var numbers  = mutableListOf<Int>()
    File("src/content.txt").forEachLine { numbers.add(it.toInt()) }

    var previous = 0
    var numberOfIncrease = 0
    numbers.forEachIndexed{ index, number ->
        if (index == 0){
            previous = number
            println("$number (no previous measurement")
        }else{
            if( number < previous){
                println("$number (decreased)")
            }else if (number > previous){
                println("$number (increased)")
                numberOfIncrease++
            }else{
                println("$number (same)")
            }
            previous = number
        }
    }

    println("number of increases: $numberOfIncrease")
}