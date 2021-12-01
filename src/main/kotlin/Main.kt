import java.io.File

fun main(){
    val numbers  = mutableListOf<Int>()
    File("src/content.txt").forEachLine { numbers.add(it.toInt()) }

    val numberListOfThreeWindowSums = mutableListOf<Int>()

    for( index in numbers.indices){

        if ( (index + 3) > numbers.size){
            break
        }

        numberListOfThreeWindowSums.add( numbers[index] + numbers[index + 1] + numbers[index + 2])
    }
    val numberOfIncrease = determineNumberOfIncreasedMeasurements(numberListOfThreeWindowSums)

    println("number of increases: $numberOfIncrease")
}

/**
 * Compares the list of integers determining whether a successive number increased
 * returns the number of increases
 * */
fun determineNumberOfIncreasedMeasurements( numbersList: MutableList<Int> ) : Int{

    var previous = 0
    var numberOfIncrease = 0
    numbersList.forEachIndexed{ index, number ->
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

    return numberOfIncrease

}