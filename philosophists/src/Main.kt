import kotlin.random.Random

val namesArray = arrayOf("Николай","Эйбрахам","Ярослав","Альфред","Григорий",
    "Эндрю","Аркадий","Зигмунд","Борис","Остин","Роман","Кристиан","Фёдор","Дэниел",
    "Даниэлла","Элис","Анастасия","Агата","Ксения","Ребека","Дарья","Беатрис",
    "Элеонора","Грейс","Елена","Джеси","Виктория","Интегра","Джина"
)

val foodArray = arrayOf("Каре ягненка", "Дыневое парфе", "Говядину Веллингтон", "Макароны", "Карбонару", "Арбузный сорбет")

fun main() {
    println("Введите количество философов за круглым столом:")
    val countPhilosophers = readln().toInt()

    val philosophers = ArrayList<Philosopher>()
    val forks = ArrayList<Fork>()

    for (i in 0..< countPhilosophers){
        val name = namesArray[i % namesArray.size]
        val foodType = foodArray[i % foodArray.size]
        philosophers.add(Philosopher(i, name, foodType))
        forks.add(Fork(i))
    }

    philosophers.shuffle(Random)

    for (i in 0 ..< countPhilosophers){
        philosophers[i].takeFork(forks)
    }
}
