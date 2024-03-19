import kotlin.system.exitProcess

fun main()
{
    val pizzaSPb = PizzaPeter(
        175.3, 241.5,
        167.5, 215.0
    )
    val pizzaMSK = PizzaMoscow(
        215.0, 250.5,
        180.5, 240.0
    )
    val pizzaLeskovo = PizzaLeskovo(
        100.0, 200.0,
        120.5, 99.99
    )

    var currentPizzaCity: PizzaCity
    while (true)
    {
        println("Добро пожаловать в пиццерию! Выберите город")
        println("1. Москва \n2. Санкт-Петербург\n3. Лесково\n\n0. Выход")
        val city = readln()
        println()
        currentPizzaCity = when (city) { //place an object in the holder
            "1" -> pizzaMSK
            "2" -> pizzaSPb
            "3" -> pizzaLeskovo
            "0" -> break
            else -> {
                println("ERROR")
                continue
            }
        }
        // Увел. кол-во. посетителей после взаимодействия
        currentPizzaCity.clientCount++

        println("Выберите пиццу:")
        println(
            "1. Неаполитанская\n2. Римская\n" +
                    "3. Сицилийская\n4. Тирольская\n0. Показать статистику "
        )
        selectPizzaType(currentPizzaCity)
        println("\n\n\n")
    }
}

private fun selectPizzaType(currentPizzaCity: PizzaCity) {
    when (readln()) {
        "1" -> {
            currentPizzaCity.neapolitanPizzaSell(); selectAddService(currentPizzaCity)
        }

        "2" -> {
            currentPizzaCity.romanPizzaSell(); selectAddService(currentPizzaCity)
        }

        "3" -> {
            currentPizzaCity.sicilianPizzaSell(); selectAddService(currentPizzaCity)
        }

        "4" -> {
            currentPizzaCity.tyroleanPizzaSell(); selectAddService(currentPizzaCity)
        }

        "0" ->{ currentPizzaCity.clientCount--; currentPizzaCity.showStats() }
        else -> {
            println("ERROR")
            exitProcess(1)
        }
    }
}

// Доп. функции в зависимости от города
fun selectAddService(currentPizzaCity:PizzaCity)
{
    if (currentPizzaCity is Drink)
    {currentPizzaCity.drinkSale()}
    if (currentPizzaCity is Sauce)
    {currentPizzaCity.SauceSale()}
    if (currentPizzaCity is CheckPhoto)
    {currentPizzaCity.showReceiptPhoto()}
}

// Калькулятор процентов
fun calcPercent(Amain: Int, Apart: Int):String
{
    if (Amain==0) return "0.0"
    return     String.format("%.2f",     ((Apart.toDouble()/Amain.toDouble())*100)    )
}
