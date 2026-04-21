import connect_four.Game
import connect_four.Grid

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    // Connect four
    val grid = Grid(6, 7)
    val game = Game(grid, 4, 10)
    game.play()
}