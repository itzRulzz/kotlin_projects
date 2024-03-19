/*
Создать приложение, которое подсчитывает количество подряд
идущих одинаковых символов во введенной строке.
На вход подается, например, строка AAADSSSRRTTHAAAA.
На выходе получаем A3DS3R2T2HA4. То есть, если количество
подряд идущих символов меньше двух, то мы не пишем единицу
*/
package main.kotlin

fun main() {
    println("Введите строку для подсчета количества симоволов в нем: ")
    var text: String

    while(true){
        text = readln()
        if (text == "")
            println("Строка не должна быть пустой.")
        else
            break
    }

    var text2 = ""
    var count = 0

    for (i in text.indices) {
        val c = text[i]
        if (c != ' ')
            count++

        if ((i == maxOf(text.length) - 1) || (text[i + 1] != c)) {
            text2 += c
            if (count != 1 && count != 0)
                text2 += count
            count = 0
        }
    }

    println(text2)
}
