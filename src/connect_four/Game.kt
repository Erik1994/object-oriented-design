package connect_four

import java.util.Scanner
import kotlin.math.max

class Game (
    private val grid: Grid,
    private val connectN: Int,
    private val targetScore: Int
) {
    private val input = Scanner(System.`in`)
    private val players = arrayOf(
        Player("Player 1", GridState.YELLOW),
        Player("Player 2", GridState.RED)
    )
    private val score = mutableMapOf<String, Int>()

    init {
        for (player in players) {
            score[player.name] = 0
        }
    }

    private fun printBoard() {
        println("Board:")
        val boardGrid = grid.getGrid()
        for (i in boardGrid.indices) {
            var row = ""
            for (piece in boardGrid[i]) {
                row += when (piece) {
                    GridState.EMPTY.ordinal -> "0 "
                    GridState.YELLOW.ordinal -> "Y "
                    GridState.RED.ordinal -> "R "
                    else -> ""
                }
            }
            println(row)
        }
        println()
    }

    private fun playMove(player: Player): IntArray {
        printBoard()
        println("${player.name}'s turn")
        val colCnt = grid.columns

        print("Enter column between 0 and ${colCnt - 1} to add piece: ")
        val moveColumn = input.nextInt()
        val moveRow = grid.fillGrid(moveColumn, player.gridState)
        return intArrayOf(moveRow, moveColumn)
    }

    private fun playRound(): Player {
        while (true) {
            for (player in players) {
                val pos = playMove(player)
                val row = pos[0]
                val col = pos[1]
                val pieceColor = player.gridState
                if (grid.checkWin(connectN, row, col, pieceColor)) {
                    score[player.name] = score[player.name]!! + 1
                    return player
                }
            }
        }
    }

    fun play() {
        var maxScore = 0
        var winner: Player? = null
        while (maxScore < targetScore) {
            winner = playRound()
            println("${winner.name} won the round")
            maxScore = max(score[winner.name]!!, maxScore)
            grid.initGrid()
        }
        println("${winner!!.name} won the game")
    }
}