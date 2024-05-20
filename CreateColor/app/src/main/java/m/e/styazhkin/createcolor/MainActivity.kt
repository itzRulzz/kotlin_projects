package m.e.styazhkin.createcolor

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var colorDisplay: View
    private var red: Int = 0
    private var green: Int = 0
    private var blue: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        colorDisplay = findViewById(R.id.colorDisplay)

        val openDialogButton: Button = findViewById(R.id.openDialogButton)
        openDialogButton.setOnClickListener {
            showColorPickerDialog()
        }
    }

    private fun showColorPickerDialog() {
        val builder = AlertDialog.Builder(this)
        val inflater: LayoutInflater = layoutInflater
        val dialogView: View = inflater.inflate(R.layout.dialog_color_picker, null)
        builder.setView(dialogView)

        val seekBarRed: SeekBar = dialogView.findViewById(R.id.seekBarRed)
        val seekBarGreen: SeekBar = dialogView.findViewById(R.id.seekBarGreen)
        val seekBarBlue: SeekBar = dialogView.findViewById(R.id.seekBarBlue)
        val editTextRed: EditText = dialogView.findViewById(R.id.editTextRed)
        val editTextGreen: EditText = dialogView.findViewById(R.id.editTextGreen)
        val editTextBlue: EditText = dialogView.findViewById(R.id.editTextBlue)
        val previewColor: View = dialogView.findViewById(R.id.previewColor)
        val applyButton: Button = dialogView.findViewById(R.id.applyButton)

        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                updateColorFromText(editTextRed, editTextGreen, editTextBlue, seekBarRed, seekBarGreen, seekBarBlue, previewColor)
            }

            override fun afterTextChanged(s: Editable?) {}
        }

        editTextRed.addTextChangedListener(textWatcher)
        editTextGreen.addTextChangedListener(textWatcher)
        editTextBlue.addTextChangedListener(textWatcher)

        val seekBarChangeListener = object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                updateColorFromSeekBar(editTextRed, editTextGreen, editTextBlue, seekBarRed, seekBarGreen, seekBarBlue, previewColor)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        }

        seekBarRed.setOnSeekBarChangeListener(seekBarChangeListener)
        seekBarGreen.setOnSeekBarChangeListener(seekBarChangeListener)
        seekBarBlue.setOnSeekBarChangeListener(seekBarChangeListener)

        applyButton.setOnClickListener {
            red = seekBarRed.progress
            green = seekBarGreen.progress
            blue = seekBarBlue.progress
            colorDisplay.setBackgroundColor(Color.rgb(red, green, blue))
        }

        builder.setNegativeButton("Отмена") { dialog, _ ->
            dialog.dismiss()
        }

        builder.create().show()
    }

    private fun updateColorFromText(
        editTextRed: EditText, editTextGreen: EditText, editTextBlue: EditText,
        seekBarRed: SeekBar, seekBarGreen: SeekBar, seekBarBlue: SeekBar, previewColor: View
    ) {
        try {
            val r = editTextRed.text.toString().toInt()
            val g = editTextGreen.text.toString().toInt()
            val b = editTextBlue.text.toString().toInt()
            if (r in 0..255 && g in 0..255 && b in 0..255) {
                seekBarRed.progress = r
                seekBarGreen.progress = g
                seekBarBlue.progress = b
                previewColor.setBackgroundColor(Color.rgb(r, g, b))
            }
        } catch (e: NumberFormatException) {
            // Handle invalid input
        }
    }

    private fun updateColorFromSeekBar(
        editTextRed: EditText, editTextGreen: EditText, editTextBlue: EditText,
        seekBarRed: SeekBar, seekBarGreen: SeekBar, seekBarBlue: SeekBar, previewColor: View
    ) {
        val r = seekBarRed.progress
        val g = seekBarGreen.progress
        val b = seekBarBlue.progress
        editTextRed.setText(r.toString())
        editTextGreen.setText(g.toString())
        editTextBlue.setText(b.toString())
        previewColor.setBackgroundColor(Color.rgb(r, g, b))
    }
}
