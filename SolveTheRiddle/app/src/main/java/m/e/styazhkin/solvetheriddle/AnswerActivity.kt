package m.e.styazhkin.solvetheriddle

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import data.Riddle

class AnswerActivity : AppCompatActivity() {

    private lateinit var riddleQuestion: TextView
    private lateinit var editTextAnswer: EditText
    private lateinit var buttonCheck: Button
    private var correctAnswer: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answer)

        riddleQuestion = findViewById(R.id.riddleQuestion)
        editTextAnswer = findViewById(R.id.editTextAnswer)
        buttonCheck = findViewById(R.id.buttonCheck)

        riddleQuestion.text = intent.getStringExtra("riddleQuestion")
        correctAnswer = intent.getStringExtra("riddleAnswer")

        editTextAnswer.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                buttonCheck.isEnabled = s?.isNotEmpty() == true
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        buttonCheck.setOnClickListener {
            val userAnswer = editTextAnswer.text.toString()
            val isCorrect = userAnswer.equals(correctAnswer, ignoreCase = true)
            val resultIntent = Intent()
            resultIntent.putExtra("userAnswer", userAnswer)
            resultIntent.putExtra("isCorrect", isCorrect)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}
