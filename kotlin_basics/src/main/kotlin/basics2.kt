/*
Создать приложение, которое подсчитывает количество различных символов во введенной строке.
Символы в ответе расположить в алфавитном порядке. Например, дана строка AASADDSS. На выходе получаем:
A - 3
D - 2
S - 3
 */
package main.kotlin

fun main(){
    println("Введите строку для подсчета количества разных символов: ")
    var text: String

    while(true){
        text = readln()
        if (text == "")
            println("Строка не должна быть пустой.")
        else
            break
    }

    val symbols_map = mutableMapOf<Char, Int>()
    var count = 0

    for (i in text.indices){
        if (text[i] == ' ')
            continue

        val c = text[i]
        for (j in i until text.length) {
            if (c == text[j])
                count += 1
        }

        if (c !in symbols_map)
            symbols_map[c] = count

        count = 0
    }

    for (i in symbols_map.toSortedMap().keys){
        println("$i - ${symbols_map[i]}")
    }
}
