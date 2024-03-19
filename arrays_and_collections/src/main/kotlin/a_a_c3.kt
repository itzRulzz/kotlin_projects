/*
Имеется массив из символов русского алфавита (все 33 символа, могут быть не по порядку).
Символы алфавита нумеруются от 1 до 33. Каждое число используется только один раз.
Сообщение шифруется с помощью ключевого слова, задаваемого пользователем.
Номер символа ключевого слова показывает, на сколько нужно сдвинуться по массиву из
символов русского алфавита. Составить программу шифровки и дешифровки строкового
выражения (то есть программа спрашивает - зашифровать или расшифровать текст и ключевое слово).
Первый массив считать закольцованным. Регистр букв не имеет значения. Например:

А	Б	В	Г	Д	Е	Ё	Ж	З	И	Й	К	Л	М	Н	О	П	Р	С	Т	У	Ф	Х	Ц	Ч	Ш	Щ	Ь	Ы	Ъ	Э	Ю	Я
21	13	4	20	22	1	25	12	24	14	2	28	9	23	3	29	6	16	15	11	26	5	30	27	8	18	10	33	31	32	19	7	17
Ключевое слово - ПОЛЕ
Исходный текст - СООБЩЕНИЕ
Зашифрованный текст - АЁФИРХЖСЮ
*/

fun select_arr_generate_type(): Array<Pair<Char, Int>> {
    println("Выберите тип ввода алфавита.")
    println("Введите 'вручную', если хотите расшифровать сообщение.")
    println("Введите 'случайно', если хотите зашифровать сообщение.")
    var choice: String
    while(true){
        choice = readln().uppercase()

        if (choice == "") {
            println("Тип ввода не должен быть пустой.")
            continue
        } else if (choice != "ВРУЧНУЮ" && choice != "СЛУЧАЙНО"){
            println("Введите 'случайно' или 'вручную'.")
            continue
        }
        break
    }
    return generate_arr(choice)
}

fun generate_arr(choice: String): Array<Pair<Char, Int>> {
    val arr: Array<Pair<Char, Int>>
    if (choice == "ВРУЧНУЮ") {
        println("Введите алфавит.")
        arr = Array(33) { ' ' to 0 }
        for (i in arr.indices) {
            arr[i] = readln().first().uppercaseChar() to i + 1
        }
    } else {
        val alphabet = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЬЫЪЭЮЯ"
        arr = alphabet.toList().shuffled().mapIndexed { index, c -> c to index + 1 }.toTypedArray()
    }
    return arr
}

fun printArray(arr: Array<Pair<Char, Int>>) {
    for ((char, num) in arr) {
        print("$char($num) ")
    }
    println()
}

fun input(): Pair<String, String> {
    println("Введите текст.")
    val text = readln()
    println("Введите ключевое слово.")
    val key = readln()
    return Pair(text, key)
}

fun encrypt(text: String, key: String, arr: Array<Pair<Char, Int>>): String {
    val alphabet = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЬЫЪЭЮЯ"
    val encryptedText = StringBuilder()
    for ((i, c) in text.withIndex()) {
        val pos = alphabet.indexOf(c.uppercaseChar())
        val shift = alphabet.indexOf(key[i % key.length].uppercaseChar())
        val newPos = (pos + shift + 2) % arr.size // +2 because our array starts from 1
        encryptedText.append(arr.first { it.second == newPos }.first)
    }
    return encryptedText.toString()
}

fun decrypt(text: String, key: String, arr: Array<Pair<Char, Int>>): String {
    val alphabet = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЬЫЪЭЮЯ"
    val decryptedText = StringBuilder()
    for ((i, c) in text.withIndex()) {
        val pos = arr.first { it.first == c.uppercaseChar() }.second
        val shift = alphabet.indexOf(key[i % key.length].uppercaseChar())
        val newPos = (pos - shift - 2 + arr.size) % arr.size
        decryptedText.append(if (newPos <= 0) arr.last().first else arr.first { it.second == newPos }.first)
    }
    return decryptedText.toString()
}

fun main(){
    val arr = select_arr_generate_type()
    println("Массив: ")
    printArray(arr)
    val (text, key) = input()
    println("Введите 'зашифровать' для шифрования текста или 'расшифровать' для его расшифровки.")
    while (true) {
        val choice = readln()
        if (choice == "зашифровать") {
            val encryptedText = encrypt(text, key, arr)
            println("Зашифрованный текст: $encryptedText")
            break
        } else if (choice == "расшифровать") {
            val decryptedText = decrypt(text, key, arr)
            println("Расшифрованный текст: $decryptedText")
            break
        } else {
            println("Неверный выбор, попробуйте снова.")
        }
    }
}