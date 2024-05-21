package m.e.styazhkin.solvetheriddle

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class StatisticsActivity : AppCompatActivity() {

    private lateinit var statisticsText: TextView
    private lateinit var buttonMain: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistics)

        statisticsText = findViewById(R.id.statisticsText)
        buttonMain = findViewById(R.id.buttonMain)

        val correctAnswers = intent.getIntExtra("correctAnswers", 0)
        val incorrectAnswers = intent.getIntExtra("incorrectAnswers", 0)

        statisticsText.text = "Получено загадок: ${correctAnswers + incorrectAnswers}\nПравильных ответов: $correctAnswers\nНе правильных ответов: $incorrectAnswers"

        buttonMain.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }
    }
}