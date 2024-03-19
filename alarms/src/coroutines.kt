import kotlinx.coroutines.*

suspend fun coroutineImplement(cl: Array<Clock>,tm: MutableList<Int>) = coroutineScope {
    for (i in cl.indices)
    {
        launch{
            cl[i].ClockWork(tm[i])
        }
    }
    launch{
        for (i in 0..tm.max())
        {
            delay(1000L)
            println()}
    }
    Unit
}
