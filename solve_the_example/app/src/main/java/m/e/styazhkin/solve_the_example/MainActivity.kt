package m.e.styazhkin.solve_the_example

import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import kotlin.random.Random
import m.e.styazhkin.solve_the_example.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var right = 0
    private var lose = 0
    private var all = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState != null) {
            right = savedInstanceState.getInt("right", 0)
            lose = savedInstanceState.getInt("lose", 0)
            all = savedInstanceState.getInt("all", 0)
            updateUI()
        }

        binding.check.isEnabled = false
        binding.vvod.isEnabled = false

        binding.start.setOnClickListener {
            binding.check.isEnabled = true
            binding.start.isEnabled = false
            binding.vvod.isEnabled = true

            generatePrimer()

            binding.vvod.text = null
        }

        binding.check.setOnClickListener {
            binding.check.isEnabled = false
            binding.start.isEnabled = true
            binding.vvod.isEnabled = false

            checkPrimer()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("right", right)
        outState.putInt("lose", lose)
        outState.putInt("all", all)
        outState.putString("prosenttext", binding.prosenttext.text.toString())
        outState.putString("null_null_1", binding.nullNull1.text.toString())
        outState.putString("null_null_2", binding.nullNull2.text.toString())
        outState.putBoolean("start_enabled", binding.start.isEnabled)
        outState.putBoolean("check_enabled", binding.check.isEnabled)
        outState.putBoolean("vvod_enabled", binding.vvod.isEnabled)

        outState.putInt("primerlayout_background", (binding.primerlayout.background as ColorDrawable).color)
        outState.putInt("vvod_background", (binding.vvod.background as ColorDrawable).color)
    }


    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        right = savedInstanceState.getInt("right", 0)
        lose = savedInstanceState.getInt("lose", 0)
        all = savedInstanceState.getInt("all", 0)
        binding.prosenttext.text = savedInstanceState.getString("prosenttext")
        binding.nullNull1.text = savedInstanceState.getString("null_null_1")
        binding.nullNull2.text = savedInstanceState.getString("null_null_2")
        binding.start.isEnabled = savedInstanceState.getBoolean("start_enabled")
        binding.check.isEnabled = savedInstanceState.getBoolean("check_enabled")
        binding.vvod.isEnabled = savedInstanceState.getBoolean("vvod_enabled")

        val backgroundColor = savedInstanceState.getInt("primerlayout_background")
        binding.primerlayout.setBackgroundColor(backgroundColor)

        val vvodBackgroundColor = savedInstanceState.getInt("vvod_background")
        binding.vvod.setBackgroundColor(vvodBackgroundColor)

        updateUI()
    }

    private fun generateRandomOperand(): Int {
        return Random.nextInt(10, 100)
    }

    private fun generateRandomOperator(): Char {
        val operators = listOf('*', '/', '-', '+')
        return operators.random()
    }

    private fun generatePrimer() {
        binding.primerlayout.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
        binding.nullNull1.text = generateRandomOperand().toString()
        binding.nullNull2.text = generateRandomOperand().toString()
        binding.znak.text = generateRandomOperator().toString()

        // Сброс цвета фона EditText 'vvod'
        binding.vvod.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent)) // Вы можете выбрать любой другой цвет, если это необходимо

        binding.vvod.text = null
    }

    private fun checkPrimer(){
        val numberOne = binding.nullNull1.text.toString().toDouble()
        val numberYwo = binding.nullNull2.text.toString().toDouble()
        var pollResult = binding.vvod.text.toString()

        val result = when (binding.znak.text) {
            "+" -> numberOne + numberYwo
            "-" -> numberOne - numberYwo
            "*" -> numberOne * numberYwo
            "/" -> {
                val stringResult =
                    String.format("%.2f", numberOne / numberYwo)
                stringResult.replace(',', '.').toDouble()
            }
            else -> throw IllegalArgumentException("Unknown operator")
        }

        if (pollResult == ""){
            pollResult = "0"
        }
        if (result == pollResult.toDouble()){
            right++
            binding.vvod.setBackgroundColor(ContextCompat.getColor(this, R.color.green))
        }
        else {
            lose++
            binding.vvod.setBackgroundColor(ContextCompat.getColor(this, R.color.red))
        }
        all++


        val present = String.format("%.2f%%", (right.toDouble() / all.toDouble()) * 100)

        binding.null1.text = all.toString()
        binding.null2.text = right.toString()
        binding.null3.text = lose.toString()
        binding.prosenttext.text = present
    }

    private fun updateUI() {
        val present = String.format("%.2f%%", (right.toDouble() / all.toDouble()) * 100)

        binding.null1.text = all.toString()
        binding.null2.text = right.toString()
        binding.null3.text = lose.toString()
        binding.prosenttext.text = present
    }
}