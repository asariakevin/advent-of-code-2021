import java.io.File

fun main(){

    val diagnosticReport = File("/home/asaria/Desktop/Hie/src/day_three_input_file.txt").readLines()
    val lengthOfBinaryString = diagnosticReport.first().length

    var oxygenGeneratorDiagnosticReport = diagnosticReport
    var co2ScrubberDiagnosticReport = diagnosticReport

    for( index in 0 until lengthOfBinaryString){

            val mostCommonValueInCurrentPosition = determineMostOrLeastCommonValue(index,oxygenGeneratorDiagnosticReport,"MOST")
            when(mostCommonValueInCurrentPosition){
                0 -> {
                   oxygenGeneratorDiagnosticReport = oxygenGeneratorDiagnosticReport.filter { it[index].digitToInt(2) == 0  }
                }
                1 -> {
                    oxygenGeneratorDiagnosticReport = oxygenGeneratorDiagnosticReport.filter { it[index].digitToInt(2) == 1  }
                }
                2 -> {
                    oxygenGeneratorDiagnosticReport = oxygenGeneratorDiagnosticReport.filter { it[index].digitToInt(2) == 1  }
                }
                4 -> println("Something went wrong")
            }
        if (oxygenGeneratorDiagnosticReport.size == 1){
            break
        }


    }

    for( index in 0 until lengthOfBinaryString){

        val leastCommonValueInCurrentPosition = determineMostOrLeastCommonValue(index,co2ScrubberDiagnosticReport,"LEAST")
        when(leastCommonValueInCurrentPosition){
            0 -> {
                co2ScrubberDiagnosticReport = co2ScrubberDiagnosticReport.filter { it[index].digitToInt(2) == 0  }
            }
            1 -> {
                co2ScrubberDiagnosticReport = co2ScrubberDiagnosticReport.filter { it[index].digitToInt(2) == 1  }
            }
            2 -> {
                co2ScrubberDiagnosticReport = co2ScrubberDiagnosticReport.filter { it[index].digitToInt(2) == 0  }
            }
            4 -> println("Something went wrong")
        }
        if (co2ScrubberDiagnosticReport.size == 1){
            break
        }


    }

    val lifeSupportRating = oxygenGeneratorDiagnosticReport.first().toInt(2) * co2ScrubberDiagnosticReport.first().toInt(2)

    println("Life support rating: $lifeSupportRating")

}

/**
 * Determine the most common value in the current
 * bit position
 *
 * return 0 if 0 most common , 1 if 1 most common, 2 if commonly equal
 *
 * @param option can be either Most or Least
 * **/
fun determineMostOrLeastCommonValue( bitPosition: Int , diagnosticRepostList: List<String>, option : String): Int {

    var numberOf0Bits = 0
    var numberOf1Bits = 0

    diagnosticRepostList.forEach { binaryString ->

        if (binaryString[bitPosition].digitToInt(2) == 0){
            numberOf0Bits++
        }else{
            numberOf1Bits++
        }
    }

    return when(option){
        "MOST" ->{
            if (numberOf0Bits > numberOf1Bits){
                 0
            }else if ( numberOf1Bits > numberOf0Bits){
                 1
            }else{
                 2
            }
        }
        "LEAST" -> {
            if (numberOf0Bits < numberOf1Bits){
                 0
            }else if ( numberOf1Bits < numberOf0Bits){
                 1
            }else{
                 2
            }
        }
        else -> 4
    }

}