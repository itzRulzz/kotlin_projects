package m.e.styazhkin.traffic_light

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var redLight: View
    private lateinit var yellowLight: View
    private lateinit var greenLight: View
    private var currentLight: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        redLight = findViewById(R.id.red_light)
        yellowLight = findViewById(R.id.yellow_light)
        greenLight = findViewById(R.id.green_light)
        val buttonChange = findViewById<Button>(R.id.button_change)

        if (savedInstanceState != null) {
            currentLight = savedInstanceState.getInt("CURRENT_LIGHT")
        }
        updateLights()

        buttonChange.setOnClickListener {
            currentLight = (currentLight + 1) % 4
            updateLights()
        }
    }

    private fun updateLights() {
        redLight.setBackgroundResource(R.drawable.circle_shape_gray)
        yellowLight.setBackgroundResource(R.drawable.circle_shape_gray)
        greenLight.setBackgroundResource(R.drawable.circle_shape_gray)
        when (currentLight) {
            0 -> redLight.setBackgroundResource(R.drawable.circle_shape_red)
            1 -> yellowLight.setBackgroundResource(R.drawable.circle_shape_yellow)
            2 -> greenLight.setBackgroundResource(R.drawable.circle_shape_green)
            3 -> yellowLight.setBackgroundResource(R.drawable.circle_shape_yellow)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("CURRENT_LIGHT", currentLight)
    }
}