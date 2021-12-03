import java.io.File

fun main(){

    val diagnosticReport = File("/home/asaria/Desktop/Hie/src/day_three_input_file.txt").readLines()
    val lengthOfBinaryString = diagnosticReport.first().length

    var gammaRateBinaryString = ""
    var epsilonRateBinaryString = ""

    var gammaRateInteger = 0
    var epsilonRateInteger = 0
    var powerConsumptionInteger = 0

    for( index in 0..(lengthOfBinaryString - 1)){
        var numberOf0Bits = 0
        var numberOf1Bits = 0

        diagnosticReport.forEach { binaryString ->

            if (binaryString[index].digitToInt(2) == 0){
                numberOf0Bits++
            }else{
                numberOf1Bits++
            }
        }

        if (numberOf0Bits > numberOf1Bits){
            gammaRateBinaryString+="0"
            epsilonRateBinaryString+="1"
        }else{
            gammaRateBinaryString+="1"
            epsilonRateBinaryString+="0"
        }
    }

    gammaRateInteger = gammaRateBinaryString.toInt(2)
    epsilonRateInteger = epsilonRateBinaryString.toInt(2)
    powerConsumptionInteger = gammaRateInteger * epsilonRateInteger

    println("Power Consumption: $powerConsumptionInteger")
}