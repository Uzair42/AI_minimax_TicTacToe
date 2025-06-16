package com.mu42.ticktac

import android.content.Context
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Button
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class TwoPlayerActivity : AppCompatActivity() {

    private val board = Array(3) { CharArray(3) { ' ' } }
    private var player1Turn = true
    private var p1Score = 0
    private var p2Score = 0
    private lateinit var buttons: Array<Array<Button>>
     lateinit var vibrator :Vibrator
     lateinit var mediaPlayer :MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_two_player)
       vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        mediaPlayer= MediaPlayer.create(this, R.raw.beep)

        buttons = Array(3) { row ->
            Array(3) { col ->
                val id = resources.getIdentifier("btn$row$col", "id", packageName)
                findViewById<Button>(id).apply {
                    setOnClickListener { handleClick(row, col) }
                }
            }
        }

        findViewById<Button>(R.id.btnResetScore).setOnClickListener {
            p1Score = 0; p2Score = 0
            updateScoreUI()
        }

        resetBoard()
    }

    private fun handleClick(row: Int, col: Int) {
        if (board[row][col] != ' ') return
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            vibrator.vibrate(100)
        }
        MediaPlayer.create(this, R.raw.beep).start()

        board[row][col] = if (player1Turn) 'x' else 'o'
        buttons[row][col].text = board[row][col].toString()
        buttons[row][col].isEnabled = false

        if (checkWinner()) {
            if (player1Turn) {
                p1Score++
                showWinner("Player 1 Wins!")
            } else {
                p2Score++
                showWinner("Player 2 Wins!")
            }
            updateScoreUI()
            resetBoard()
        } else if (getEmptyCells().isEmpty()) {
            showWinner("Draw!")
            resetBoard()
        } else {
            player1Turn = !player1Turn
            updateTurnUI()
        }
    }

    private fun showWinner(message: String) {
       Toast.makeText(this,"$message",Toast.LENGTH_LONG)
            .show()
    }

    private fun checkWinner(): Boolean {
        for (i in 0..2) {
            if (board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) return true
            if (board[0][i] != ' ' && board[0][i] == board[1][i] && board[1][i] == board[2][i]) return true
        }
        if (board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) return true
        if (board[0][2] != ' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) return true
        return false
    }

    private fun getEmptyCells(): List<Pair<Int, Int>> =
        (0..2).flatMap { i -> (0..2).map { j -> i to j }.filter { board[it.first][it.second] == ' ' } }

    private fun resetBoard() {
        for (i in 0..2) for (j in 0..2) {
            board[i][j] = ' '
            buttons[i][j].text = ""
            buttons[i][j].isEnabled = true
        }
        player1Turn = true
        updateTurnUI()
    }

    private fun updateScoreUI() {
        findViewById<TextView>(R.id.txtP1Score).text = " $p1Score"
        findViewById<TextView>(R.id.txtP2Score).text = " $p2Score"
    }

    private fun updateTurnUI() {
        val txtTurn = findViewById<Button>(R.id.txtTurn)
        txtTurn.text = if (player1Turn) "Player#1 Turn (X)" else "Player#2 Turn (O)"
        txtTurn.setBackgroundColor(if (player1Turn) Color.parseColor("#FF9800") else Color.parseColor("#FFEB3B"))
    }
}
