fun main() {
    var userInput: String?
    val words: List<String>

    while (true) {
        print("Введите слова для сопоставления анаграмм через пробел: ")
        userInput = readlnOrNull()

        if (userInput.isNullOrEmpty()) {
            println("Строка не должна быть пустой!")
        } else {
            words = userInput.split(" ")
            break
        }
    }

    val groupedWords = words.groupBy { it.toCharArray().sorted().joinToString("") }
    groupedWords.values.forEach { group -> println(group) }
}