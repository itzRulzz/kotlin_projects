class PizzaPeter(
    neapolitanPizzaPrice: Double,romanPizzaPrice: Double,
    sicilianPizzaPrice: Double, tyroleanPizzaPrice: Double
) : PizzaCity(
    neapolitanPizzaPrice,  romanPizzaPrice,
    sicilianPizzaPrice,  tyroleanPizzaPrice), Drink
{
    val coffeePrice = 200.0
    var coffeeCount = 0

    var neapPlusCoffee = 0
    var romanPlusCoffee = 0
    var sicilianPlusCoffee = 0
    var tyroleanPlusCoffee = 0

    private var currSelectedPizza = "undefined" // Отдельное значение до обновления статистики
    override fun showStats()
    {
        println("-------------------------------------------")
        println("СТАТИСТИКА ПО СПБ")
        println()

        super.showStats()
        println("Продано чашек кофе: $coffeeCount \n" +
                "Всего продано кофе на сумму ${coffeePrice*coffeeCount} рублей")

        println("Кофе берут ${calcPercent(clientCount,coffeeCount)}% клиентов")
        println("${calcPercent(coffeeCount,neapPlusCoffee)}% кофе куплено вместе с неаполитанской пиццей, а именно ${neapPlusCoffee} штук")
        println("${calcPercent(coffeeCount,romanPlusCoffee)}% кофе куплено вместе с римской пиццей, а именно ${romanPlusCoffee} штук")
        println("${calcPercent(coffeeCount,sicilianPlusCoffee)}% кофе куплено вместе с сицилийской пиццей, а именно ${sicilianPlusCoffee} штук")
        println("${calcPercent(coffeeCount,tyroleanPlusCoffee)}% кофе куплено вместе с тирольской пиццей, а именно ${tyroleanPlusCoffee} штук")
        println()
        println("Итого на счету пиццерии ${moneyPizz+coffeePrice*coffeeCount} рублей")
        println("-------------------------------------------")
    }

    override fun drinkSale() {
        println("Не хотите ли выпить кофе?")
        println("1. Да\n2. Нет")
        if (readln()=="1")
        {
            coffeeCount++
            println("С вас ещё $coffeePrice рублей")
            when (currSelectedPizza)
            {
                "neapolitanPizza" -> neapPlusCoffee++
                "romanPizza" -> romanPlusCoffee++
                "sicilianPizza" -> sicilianPlusCoffee++
                "tyroleanPizza" -> tyroleanPlusCoffee++
            }
        }
    }

    override fun neapolitanPizzaSell() //pizza sellers
    {
        neapolitanPizzaCount++
        currSelectedPizza="neapolitanPizza"
        println("Спасибо за покупку неаполитанской пиццы в СПб")
    }
    override fun romanPizzaSell()
    {
        romanPizzaCount++
        currSelectedPizza="romanPizza"
        println("Спасибо за покупку римской пиццы в СПб")
    }
    override fun sicilianPizzaSell()
    {
        sicilianPizzaCount++
        currSelectedPizza="sicilianPizza"
        println("Спасибо за покупку сицилийской пиццы в СПб")
    }
    override fun tyroleanPizzaSell()
    {
        tyroleanPizzaCount++
        currSelectedPizza="tyroleanPizza"
        println("Спасибо за покупку тирольской пиццы в СПб")
    }
}
