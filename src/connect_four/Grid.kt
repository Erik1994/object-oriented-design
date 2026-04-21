package connect_four

class Grid(val rows: Int, val columns: Int) {
    private var grid: Array<IntArray> = Array(rows) { IntArray(columns) }

    init {
        initGrid()
    }

    fun initGrid() {
        for (i in 0 until rows) {
            for (j in 0 until columns) {
                grid[i][j] = GridState.EMPTY.ordinal
            }
        }
    }

    fun getGrid(): Array<IntArray> = grid

    fun fillGrid(column: Int, state: GridState): Int {
        if (column !in 0..<columns) {
            throw Error("Invalid column")
        }
        if (state == GridState.EMPTY) {
            throw Error("Invalid piece")
        }

        for (row in rows - 1 downTo 0) {
            if (grid[row][column] == GridState.EMPTY.ordinal) {
                grid[row][column] = state.ordinal
                return row
            }
        }

        return -1
    }

    fun checkWin(connectN: Int, row: Int, col: Int, piece: GridState): Boolean {
        // Check horizontal
        var count = 0
        for (c in 0 until columns) {
            if (grid[row][c] == piece.ordinal) {
                count++
            } else {
                count = 0
            }
            if (count == connectN) {
                return true
            }
        }

        // Check vertical
        count = 0
        for (r in 0 until rows) {
            if (grid[r][col] == piece.ordinal) {
                count++
            } else {
                count = 0
            }
            if (count == connectN) {
                return true
            }
        }

        // Check diagonal
        count = 0
        for (r in 0 until rows) {
            val c = row + col - r
            if (c in 0..<columns && grid[r][c] == piece.ordinal) {
                count++
            } else {
                count = 0
            }
            if (count == connectN) {
                return true
            }
        }

        // Check anti-diagonal
        count = 0
        for (r in 0 until rows) {
            val c = col - row + r
            if (c in 0..<columns && grid[r][c] == piece.ordinal) {
                count++
            } else {
                count = 0
            }
            if (count == connectN) {
                return true
            }
        }
        return false
    }
}