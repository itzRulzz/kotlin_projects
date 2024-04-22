package m.e.styazhkin.form

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.view.View
import android.widget.LinearLayout
import android.widget.RadioGroup
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val radioGroupWorkType = findViewById<RadioGroup>(R.id.radioGroupWorkType)
        val editTextDesiredSalary = findViewById<EditText>(R.id.editTextDesiredSalary)
        val editTextDesiredSalaryLandscape = findViewById<EditText>(R.id.editTextDesiredSalaryLandscape)

        radioGroupWorkType.setOnCheckedChangeListener { _, checkedId ->
            val isVisible = if (checkedId == R.id.radioButtonSalary) View.VISIBLE else View.GONE

            editTextDesiredSalary?.visibility = isVisible
            editTextDesiredSalaryLandscape?.visibility = isVisible
        }
    }
}

