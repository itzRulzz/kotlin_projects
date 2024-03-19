import kotlinx.coroutines.*

class Clock
{
    var ClockName = "Первый"
    var ClockTickSound = "Тик1"
    var ClockRingSound = "ФИНИШ!"

    constructor(){}

    constructor(name: String,tickS: String,ringS: String)
    {
        ClockName=name; ClockTickSound=tickS; ClockRingSound=ringS
    }

    // Запуск отчета будильника
    suspend fun ClockWork(time: Int)
    {
        for (i in 0..time)
        {
            print("$i-$ClockTickSound ")
            delay(1000L)
        }
        print("$ClockRingSound ")
    }
}
