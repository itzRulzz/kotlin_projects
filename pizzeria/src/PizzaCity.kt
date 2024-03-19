//basic pizzeria class
abstract class PizzaCity(
    val neapolitanPizzaPrice: Double,val romanPizzaPrice: Double,
    val sicilianPizzaPrice: Double,val tyroleanPizzaPrice: Double)
{
    var clientCount = 0
    var moneyPizz = 0.0
    var neapolitanPizzaCount = 0
    var romanPizzaCount = 0
    var sicilianPizzaCount = 0
    var tyroleanPizzaCount = 0

    abstract fun neapolitanPizzaSell()
    abstract fun romanPizzaSell()
    abstract fun sicilianPizzaSell()
    abstract fun tyroleanPizzaSell()

    open fun showStats() //show stats
    {
        println("Всего покупателей: $clientCount")
        println()
        println("Продано неаполитанской пиццы: $neapolitanPizzaCount")
        println("Продано римской пиццы: $romanPizzaCount")
        println("Продано сицилийской пиццы: $sicilianPizzaCount")
        println("Продано тирольской пиццы: $tyroleanPizzaCount")

        moneyPizz = neapolitanPizzaCount*neapolitanPizzaPrice + sicilianPizzaCount*sicilianPizzaPrice +
                romanPizzaCount*romanPizzaPrice + tyroleanPizzaCount*tyroleanPizzaPrice

        println("Всего продано пицц на сумму $moneyPizz рублей")
        println()
    }
}
