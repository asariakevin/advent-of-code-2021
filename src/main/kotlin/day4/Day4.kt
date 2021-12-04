import day4.Board
import day4.Cell
import day4.Row
import java.io.File

fun main(){
    val wholeFile = File("/home/asaria/Desktop/Hie/src/main/kotlin/day4/day_4_input_file.txt").readLines()

    var winningBoard  = Board(mutableListOf())
    var completed = false

    var randomNumberListAsAString = ""
    var randomNumberList = mutableListOf<Int>()

    val boardWithGridAsString = mutableListOf<String>()

    var currentBoardRow = ""
    for( index in 0..wholeFile.size){
        var line = ""
        // just to enable the last grid to be saved
        if(index == wholeFile.size){
            boardWithGridAsString.add(currentBoardRow)
            break
        }
        line = wholeFile[index]


        if (index == 0){
            randomNumberListAsAString = line
        }else if (line.equals("")){
            if (currentBoardRow.equals("")){
                //do nothing, firts time round
            }else {
                boardWithGridAsString.add(currentBoardRow)
                currentBoardRow=""
            }
        }else{
            currentBoardRow+="\n$line"
        }
    }

    // turn the string of random number list to an actual list of integers
    randomNumberList = randomNumberListAsAString.split(",").map { it.toInt() } as MutableList<Int>

    // turn the list of grids which are in strings to grid of integers
    val allBoardsForTheGame = mutableListOf<Board>()
    boardWithGridAsString.forEach{ gridAsString ->
        val board  = Board(mutableListOf())
        val gridAsListOfStringForEachRow = gridAsString.trim().split("\n")
//        println(gridAsString)
//        println(gridAsListOfStringForEachRow)
        gridAsListOfStringForEachRow.forEach{ rowAsString ->
            var rowAsListOfIntStrings = rowAsString.trim().split(" ")
            rowAsListOfIntStrings = rowAsListOfIntStrings.filter { it.isNotBlank() }
            val rowAsListOfInts = rowAsListOfIntStrings.map { it.toInt() }
            val rowAsListOfCells = rowAsListOfInts.map { Cell(it)}

             board.grid.add(Row(rowAsListOfCells))
        }

        allBoardsForTheGame.add(board)
    }

//    allBoardsForTheGame.forEach{ println(it)}
//    randomNumberList.forEach { println(it) }
    var currentRandomDrawnNumber = 0
    run randomNumberListLoop@{
        randomNumberList.forEach { randomNumber ->
            println("Random number $randomNumber")

            // mark the random choosen number in all boards
            allBoardsForTheGame.forEach { board ->
                board.grid.forEach { row ->
                    row.row.forEach{ cell ->
                        if (cell.data == randomNumber) cell.marked = true
                    }
                }
            }

            //check whether any board has a whole row
            run rowLoop@{
                allBoardsForTheGame.forEach { board ->
                    board.grid.forEach { row ->
                        if ( row.row.all{ cell -> cell.marked } ){
                            winningBoard = board
                            println(winningBoard)
                            completed = true
                            return@rowLoop
                        }
                    }
                }
            }

            //check whether any board has a whole column
            run columnLoop@{
                allBoardsForTheGame.forEach { board ->
                    var columnAllTrue = true
                    val numberOfColumns = board.grid.first().row.size
                    for ( index in 0 until numberOfColumns){
                        board.grid.forEach { row: Row ->
                            columnAllTrue = columnAllTrue && row.row.get(index).marked
                        }

                        if( columnAllTrue){
                            winningBoard = board
                            println(winningBoard)
                            completed = true
                            return@columnLoop
                        }
                    }
                }
            }

            if ( completed) {
                currentRandomDrawnNumber = randomNumber
                return@randomNumberListLoop
            }

        }
    }

    // sum of unmarked numbers in winning board
   var sumOfUnmarkedNumbers = 0
   winningBoard.grid.forEach { row: Row ->
        row.row.forEach{ cell: Cell ->
            if( !cell.marked){
                sumOfUnmarkedNumbers+=cell.data
            }
        }
   }

    val score = sumOfUnmarkedNumbers * currentRandomDrawnNumber
    println("score: $score")

}