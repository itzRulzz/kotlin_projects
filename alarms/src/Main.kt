import kotlinx.coroutines.*

suspend fun main()
{
    val arrClocks = arrayOf(Clock("Первый","Тик1","ФИНИШ1!"),
        Clock("Второй","Тик2","ФИНИШ2!"),
        Clock("Bomb","Bomb has been planted","*взрыв*"))
    val arrTimes : MutableList<Int> = ArrayList()

    for(i in arrClocks)
    {
        print("Введите время работы в секундах для будильника ${i.ClockName}: ")
        arrTimes.add(readln().toInt())
    }

    println()
    for(i in arrClocks){
        println("Будильник ${i.ClockName} тикает: ${i.ClockTickSound} ")
    }

    println()
    coroutineImplement(arrClocks,arrTimes)
}
