package com.mu42.ticktac

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class AiModeActivity : AppCompatActivity() {

    // Game state
    private val board = Array(3) { CharArray(3) { ' ' } }
    private var playerTurn = true // true = player (X), false = AI (O)
    private lateinit var buttons: Array<Array<Button>>
    private lateinit var resetButton: Button
    private lateinit var spinner: Spinner
    private var currentDifficulty = "Easy"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.aimode)

        // Initialize 3x3 button grid
        buttons = Array(3) { row ->
            Array(3) { col ->
                val btnId = resources.getIdentifier("btn$row$col", "id", packageName)
                findViewById<Button>(btnId).apply {
                    setOnClickListener { onCellClicked(row, col) }
                }
            }
        }

        // Spinner for difficulty
        spinner = findViewById(R.id.difficultySpinner)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>, view: View?, position: Int, id: Long
            ) {
                currentDifficulty = parent.getItemAtPosition(position).toString()
                resetGame() // optional: restart game on difficulty change
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        // Reset button
        resetButton = findViewById(R.id.btnReset)
        resetButton.setOnClickListener { resetGame() }

        resetGame() // Start the game fresh
    }

    // Handle user tap
    private fun onCellClicked(row: Int, col: Int) {
        if (board[row][col] != ' ' || !playerTurn) return

        board[row][col] = 'X'
        buttons[row][col].text = "X"
        playerTurn = false

        if (checkGameOver()) return

        // Delay AI move slightly
        buttons[0][0].postDelayed({ aiMove() }, 900)
    }

    // AI move based on difficulty
    private fun aiMove() {
        when (currentDifficulty) {
            "Easy" -> makeRandomMove()
            "Medium" -> makeMediumMove()
            "Hard" -> makeBestMove()
        }

        if (checkGameOver()) return
        playerTurn = true
    }

    // Easy AI: Random move
    private fun makeRandomMove() {
        val empty = getEmptyCells()
        if (empty.isNotEmpty()) {
            val (row, col) = empty.random()
            board[row][col] = 'O'
            buttons[row][col].text = "O"
        }
    }

    // Medium AI: Win or block if possible, else random
    private fun makeMediumMove() {
        val move = getWinningOrBlockingMove('O') ?: getWinningOrBlockingMove('X') ?: getEmptyCells().random()
        board[move.first][move.second] = 'O'
        buttons[move.first][move.second].text = "O"
    }

    // Hard AI: Minimax
    private fun makeBestMove() {
        var bestScore = Int.MIN_VALUE
        var bestMove = Pair(-1, -1)

        for ((row, col) in getEmptyCells()) {
            board[row][col] = 'O'
            val score = minimax(0, false)
            board[row][col] = ' '
            if (score > bestScore) {
                bestScore = score
                bestMove = Pair(row, col)
            }
        }

        val (row, col) = bestMove
        board[row][col] = 'O'
        buttons[row][col].text = "O"
    }

    // Minimax algorithm
    private fun minimax(depth: Int, isMaximizing: Boolean): Int {
        val winner = getWinner()
        if (winner == 'X') return -10 + depth
        if (winner == 'O') return 10 - depth
        if (getEmptyCells().isEmpty()) return 0

        return if (isMaximizing) {
            var maxEval = Int.MIN_VALUE
            for ((row, col) in getEmptyCells()) {
                board[row][col] = 'O'
                maxEval = maxOf(maxEval, minimax(depth + 1, false))
                board[row][col] = ' '
            }
            maxEval
        } else {
            var minEval = Int.MAX_VALUE
            for ((row, col) in getEmptyCells()) {
                board[row][col] = 'X'
                minEval = minOf(minEval, minimax(depth + 1, true))
                board[row][col] = ' '
            }
            minEval
        }
    }

    // Return 'X', 'O' or null
    private fun getWinner(): Char? {
        for (i in 0..2) {
            if (board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2])
                return board[i][0]
            if (board[0][i] != ' ' && board[0][i] == board[1][i] && board[1][i] == board[2][i])
                return board[0][i]
        }

        if (board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2])
            return board[0][0]
        if (board[0][2] != ' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0])
            return board[0][2]

        return null
    }

    // Check for winner or draw
    private fun checkGameOver(): Boolean {
        val winner = getWinner()
        return when {
            winner == 'X' -> {
                showToast("You Win!")
                true
            }
            winner == 'O' -> {
                showToast("AI Wins!")
                true
            }
            getEmptyCells().isEmpty() -> {
                showToast("It's a Draw!")
                true
            }
            else -> false
        }
    }

    // Find win/block move for Medium AI
    private fun getWinningOrBlockingMove(char: Char): Pair<Int, Int>? {
        for ((row, col) in getEmptyCells()) {
            board[row][col] = char
            val win = getWinner() == char
            board[row][col] = ' '
            if (win) return Pair(row, col)
        }
        return null
    }

    // List of empty cells
    private fun getEmptyCells(): List<Pair<Int, Int>> {
        val empty = mutableListOf<Pair<Int, Int>>()
        for (i in 0..2) {
            for (j in 0..2) {
                if (board[i][j] == ' ') empty.add(Pair(i, j))
            }
        }
        return empty
    }

    // Clear board and UI
    private fun resetGame() {
        for (i in 0..2) {
            for (j in 0..2) {
                board[i][j] = ' '
                buttons[i][j].text = ""
            }
        }
        playerTurn = true
    }

    // Simple toast
    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}
