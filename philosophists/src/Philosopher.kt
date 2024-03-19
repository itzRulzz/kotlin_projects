import kotlin.random.Random

class Philosopher (val id : Int, val name: String, val foodType: String) {

    public fun takeFork(forks : ArrayList<Fork>){
        val isRight = Random.nextBoolean()
        val right = id % forks.size
        val left = (id + 1) % forks.size
        if (!forks[right].isChosen && isRight){
            forks[right].isChosen = true
            println("Философ ${id + 1} ($name) взял вилку справа и ест $foodType")
            return
        }
        else if (!forks[left].isChosen){
            forks[left].isChosen = true
            println("Философ ${id + 1} ($name) взял вилку слева и ест $foodType")
            return
        }
        if (!forks[left].isChosen && !isRight){
            forks[left].isChosen = true
            println("Философ ${id + 1} ($name) взял вилку слева и ест $foodType")
            return
        }
        else if (!forks[right].isChosen){
            forks[right].isChosen = true
            println("Философ ${id + 1} ($name) взял вилку справа и ест $foodType")
            return
        }
        println("Философ ${id + 1} ($name) думает (голодает)")
    }
}
