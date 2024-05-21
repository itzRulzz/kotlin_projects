package m.e.styazhkin.rpss

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var computerChoiceImage: ImageView
    private lateinit var playerChoiceImage: ImageView
    private lateinit var resultText: TextView

    private var playerChoice: Choice? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        computerChoiceImage = findViewById(R.id.computerChoiceImage)
        playerChoiceImage = findViewById(R.id.playerChoiceImage)
        resultText = findViewById(R.id.resultText)

        findViewById<Button>(R.id.buttonRock).setOnClickListener { onChoiceSelected(Choice.ROCK) }
        findViewById<Button>(R.id.buttonPaper).setOnClickListener { onChoiceSelected(Choice.PAPER) }
        findViewById<Button>(R.id.buttonScissors).setOnClickListener { onChoiceSelected(Choice.SCISSORS) }
        findViewById<Button>(R.id.buttonLizard).setOnClickListener { onChoiceSelected(Choice.LIZARD) }
        findViewById<Button>(R.id.buttonSpock).setOnClickListener { onChoiceSelected(Choice.SPOCK) }
        findViewById<Button>(R.id.buttonPlay).setOnClickListener { onPlayButtonClicked() }
    }

    private fun onChoiceSelected(choice: Choice) {
        playerChoice = choice
        playerChoiceImage.setImageResource(choice.drawableRes)
    }

    private fun onPlayButtonClicked() {
        if (playerChoice == null) {
            Toast.makeText(this, "Необходимо сделать выбор", Toast.LENGTH_SHORT).show()
            return
        }

        val computerChoice = Choice.values().random()
        computerChoiceImage.setImageResource(computerChoice.drawableRes)

        val result = getResult(playerChoice!!, computerChoice)
        resultText.text = when (result) {
            Result.WIN -> "Вы выиграли!"
            Result.LOSE -> "Вы проиграли!"
            Result.DRAW -> "Ничья!"
        }
    }

    private fun getResult(player: Choice, computer: Choice): Result {
        return if (player == computer) {
            Result.DRAW
        } else when (player) {
            Choice.ROCK -> if (computer == Choice.SCISSORS || computer == Choice.LIZARD) Result.WIN else Result.LOSE
            Choice.PAPER -> if (computer == Choice.ROCK || computer == Choice.SPOCK) Result.WIN else Result.LOSE
            Choice.SCISSORS -> if (computer == Choice.PAPER || computer == Choice.LIZARD) Result.WIN else Result.LOSE
            Choice.LIZARD -> if (computer == Choice.SPOCK || computer == Choice.PAPER) Result.WIN else Result.LOSE
            Choice.SPOCK -> if (computer == Choice.SCISSORS || computer == Choice.ROCK) Result.WIN else Result.LOSE
        }
    }
}

enum class Choice(val drawableRes: Int) {
    ROCK(R.drawable.rock),
    PAPER(R.drawable.paper),
    SCISSORS(R.drawable.scissors),
    LIZARD(R.drawable.lizard),
    SPOCK(R.drawable.spock)
}

enum class Result {
    WIN, LOSE, DRAW
}
