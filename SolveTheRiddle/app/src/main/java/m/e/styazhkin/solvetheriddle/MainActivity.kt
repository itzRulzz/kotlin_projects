package m.e.styazhkin.solvetheriddle

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import data.Riddle
import data.riddles

class MainActivity : AppCompatActivity() {

    private lateinit var riddleText: TextView
    private lateinit var riddleCount: TextView
    private lateinit var resultText: TextView
    private lateinit var buttonNextRiddle: Button
    private lateinit var buttonAnswer: Button
    private lateinit var buttonStatistics: Button

    private val askedRiddles = mutableListOf<Riddle>()
    private var currentRiddle: Riddle? = null
    private var riddleIndex = 0
    private var correctAnswers = 0
    private var incorrectAnswers = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        riddleText = findViewById(R.id.riddleText)
        riddleCount = findViewById(R.id.riddleCount)
        resultText = findViewById(R.id.resultText)
        buttonNextRiddle = findViewById(R.id.buttonNextRiddle)
        buttonAnswer = findViewById(R.id.buttonAnswer)
        buttonStatistics = findViewById(R.id.buttonStatistics)

        buttonNextRiddle.setOnClickListener { showNextRiddle() }
        buttonAnswer.setOnClickListener { showAnswerScreen() }
        buttonStatistics.setOnClickListener { showStatisticsScreen() }
    }

    private fun showNextRiddle() {
        if (riddleIndex < 10) {
            currentRiddle = riddles.filter { it !in askedRiddles }.random()
            askedRiddles.add(currentRiddle!!)
            riddleText.text = currentRiddle?.question
            riddleCount.text = "Загадка ${riddleIndex + 1} из 10"
            buttonAnswer.isEnabled = true
            resultText.text = ""
            riddleIndex++
        } else {
            buttonNextRiddle.isEnabled = false
            buttonStatistics.isEnabled = true
        }
    }

    private fun showAnswerScreen() {
        val intent = Intent(this, AnswerActivity::class.java)
        intent.putExtra("riddleQuestion", currentRiddle?.question)
        intent.putExtra("riddleAnswer", currentRiddle?.answer)
        startActivityForResult(intent, 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK) {
            val userAnswer = data?.getStringExtra("userAnswer")
            val correct = data?.getBooleanExtra("isCorrect", false) ?: false
            if (correct) {
                resultText.text = "Ваш ответ: $userAnswer\nПравильно!"
            } else {
                val correctAnswer = currentRiddle?.answer
                resultText.text = "Ваш ответ: $userAnswer\nНе правильно! Правильный ответ: $correctAnswer"
            }
            if (correct) correctAnswers++ else incorrectAnswers++
            buttonAnswer.isEnabled = false
        }
    }

    private fun showStatisticsScreen() {
        val intent = Intent(this, StatisticsActivity::class.java)
        intent.putExtra("correctAnswers", correctAnswers)
        intent.putExtra("incorrectAnswers", incorrectAnswers)
        startActivity(intent)
    }
}
