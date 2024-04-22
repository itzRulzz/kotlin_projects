package m.e.styazhkin.car_brands

data class Vehicle(
    val name: String,
    val isCar: Boolean,
    val capacity: Int,
    val axles: Int,
    val imageResId: Int? = null
)