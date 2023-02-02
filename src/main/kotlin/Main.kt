/**
 * Assignment 1 - ConnectFour
 * @Author: Emilio Cruz
 * @Date: 1/28/23
 */

import kotlin.concurrent.fixedRateTimer


val FIR_board = FourInARow()

/** The entry main method (the program starts here)  */
fun main() {
    val currentState: Int = GameConstants.PLAYING
    val userInput = ""

    //game loop
    do {
        FIR_board.printBoard()

        println("Enter the location for your move (0-35)")
        var input: Int = readLine().toString().toInt()

        while (input < 0 || input > 35)
        {
            println("ERROR: Please enter an integer between 0 and 35")
            input = readLine().toString().toInt()
        }

        if(FIR_board.getCell(input) != GameConstants.EMPTY) {
            do {
                println("Location is taken, please make another selection")
                input = readLine().toString().toInt()
            } while (FIR_board.getCell(input) != GameConstants.EMPTY)
        }

        //Player makes move
        println("PLAYER MOVE ________________")

        FIR_board.setMove(GameConstants.BLUE, input)

        if(FIR_board.checkForWinner() == GameConstants.BLUE_WON)
        {
            FIR_board.printBoard()
            println("Blue Wins!")
            return
        }

        //Computer auto-generates next available move
        println("AI MOVE ________________")

        FIR_board.setMove(GameConstants.RED, FIR_board.computerMove)

        if(FIR_board.checkForWinner() == GameConstants.RED_WON)
        {
            FIR_board.printBoard()
            println("Red Wins!")
            return
        }

        if(FIR_board.checkForWinner() == GameConstants.TIE)
        {
            FIR_board.printBoard()
            println("It's a Tie!")
            return
        }
    } while (currentState == GameConstants.PLAYING && userInput != "q")
// repeat if not game-over
}
 