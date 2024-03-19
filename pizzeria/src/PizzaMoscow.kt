class PizzaMoscow(
    neapolitanPizzaPrice: Double, romanPizzaPrice: Double,
    sicilianPizzaPrice: Double, tyroleanPizzaPrice: Double
) : PizzaCity(
    neapolitanPizzaPrice,romanPizzaPrice,
    sicilianPizzaPrice, tyroleanPizzaPrice), CheckPhoto
{
    val receiptDiscount = 50.0
    var receiptCount = 0

    override fun showStats()
    {
        println("-------------------------------------------")
        println("СТАТИСТИКА ПО МОСКВЕ")
        println()

        super.showStats()
        println("Показано чеков: $receiptCount \nВсего выдано скидок" +
                " на сумму ${receiptCount*receiptDiscount} рублей")

        println("Чек показывают ${calcPercent(clientCount,receiptCount)}% клиентов")
        println()
        println("Итого на счету пиццерии ${moneyPizz-receiptCount*receiptDiscount} рублей")
        println("-------------------------------------------")
    }

    override fun showReceiptPhoto() {
        println("У Вас есть фотография чека?")
        println("1. Да\n2. Нет")
        if (readln()=="1")
        {
            println("Вы получили скидку $receiptDiscount рублей")
            receiptCount++
        }
    }

    override fun neapolitanPizzaSell()
    {
        neapolitanPizzaCount++
        println("Спасибо за покупку неаполитанской пиццы в Москве")
    }
    override fun romanPizzaSell()
    {
        romanPizzaCount++
        println("Спасибо за покупку римской пиццы в Москве")
    }
    override fun sicilianPizzaSell()
    {
        sicilianPizzaCount++
        println("Спасибо за покупку сицилийской пиццы в Москве")
    }
    override fun tyroleanPizzaSell()
    {
        tyroleanPizzaCount++
        println("Спасибо за покупку тирольской пиццы в Москве")
    }
}
