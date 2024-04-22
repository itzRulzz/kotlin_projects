package m.e.styazhkin.car_brands

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var spinner: Spinner
    private lateinit var btnAdd: Button
    private lateinit var btnInfo: Button
    private lateinit var tvVehicleInfo: TextView

    private val vehicleList = mutableListOf(
        Vehicle("Mercedes-Benz S-Class", true, 560, 2, R.drawable.mercedes),
        Vehicle("Tesla Model S", true, 580, 2, R.drawable.tesla),
        Vehicle("Land Rover Defender", true, 900, 2, R.drawable.land_rover),
        Vehicle("Porsche 911", true, 390, 2, R.drawable.porsche),
        Vehicle("Ducati Panigale V4", false, 180, 2, R.drawable.ducati)
    )

    private lateinit var adapter: CustomSpinnerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spinner = findViewById(R.id.spinner)
        btnAdd = findViewById(R.id.btn_add)
        btnInfo = findViewById(R.id.btn_info)
        tvVehicleInfo = findViewById(R.id.tv_vehicle_info)
        tvVehicleInfo.movementMethod = ScrollingMovementMethod.getInstance()

        adapter = CustomSpinnerAdapter(this, vehicleList)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                updateInfo(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }


        btnAdd.setOnClickListener {
            showAddDialog()
        }

        btnInfo.setOnClickListener {
            showInfo()
        }
    }

    @SuppressLint("SetTextI18n")
    fun updateInfo(position: Int) {
        val vehicle = vehicleList[position]
        val type = if (vehicle.isCar) "Автомобиль" else "Мотоцикл"
        val positionText = "Позиция ${position + 1} из ${vehicleList.size}\n"  // Позиция +1, потому что индексация начинается с 0
        val vehicleInfo = "Наименование: ${vehicle.name}\n" +
                "Тип: $type\n" +
                "Грузоподъемность: ${vehicle.capacity}\n" +
                "Количество осей: ${vehicle.axles}"
        tvVehicleInfo.text = "$positionText$vehicleInfo"
        tvVehicleInfo.setBackgroundColor(generateRandomColor())  // Если нужно изменить цвет при каждом выборе
    }

    private fun showAddDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_vehicle, null)
        val dialogBuilder = AlertDialog.Builder(this)
            .setView(dialogView)
            .setTitle("Добавить новое транспортное средство")

        val etName = dialogView.findViewById<EditText>(R.id.et_name)
        val radioGroup = dialogView.findViewById<RadioGroup>(R.id.radio_group)
        val etCapacity = dialogView.findViewById<EditText>(R.id.et_capacity)
        val etAxles = dialogView.findViewById<EditText>(R.id.et_axles)

        dialogBuilder.setPositiveButton("Добавить") { _, _ ->
            val name = etName.text.toString()
            val isCar = radioGroup.checkedRadioButtonId == R.id.radio_car
            val capacity = etCapacity.text.toString().takeIf { it.isNotEmpty() }?.toIntOrNull() ?: DEFAULT_CAPACITY
            val axles = etAxles.text.toString()
                .takeIf { it.isNotEmpty() }?.toIntOrNull() ?: DEFAULT_AXLES

            val newVehicle = Vehicle(name, isCar, capacity, axles, R.drawable.default_pic)

            vehicleList.add(newVehicle)
            adapter.notifyDataSetChanged()
        }

        dialogBuilder.setNegativeButton("Отмена") { dialog, _ ->
            dialog.dismiss()
        }

        dialogBuilder.create().show()
    }

    private fun showInfo() {
        val position = spinner.selectedItemPosition + 1  // +1, так как позиции начинаются с 0
        val totalItems = vehicleList.size

        if (position > 0 && position <= totalItems) {
            val vehicle = vehicleList[position - 1]  // Сдвиг на -1, так как список начинается с 0
            val type = if (vehicle.isCar) "Автомобиль" else "Мотоцикл"
            val infoText = "Позиция $position из $totalItems\nНаименование: ${vehicle.name}\n" +
                    "Тип: $type\n" +
                    "Грузоподъемность: ${vehicle.capacity}\n" +
                    "Количество осей: ${vehicle.axles}"
            tvVehicleInfo.text = infoText
            tvVehicleInfo.setBackgroundColor(generateRandomColor())
        } else {
            tvVehicleInfo.text = "Выберите транспортное средство"
        }
    }

    private fun generateRandomColor(): Int {
        val rnd = Random
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
    }

    companion object {
        private const val DEFAULT_CAPACITY = 1488
        private const val DEFAULT_AXLES = 2
    }
}