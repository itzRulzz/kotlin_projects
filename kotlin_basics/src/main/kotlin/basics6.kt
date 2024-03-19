/*
Создать приложение, в котором пользователь вводит две различных цифры.
На выходе приложение выдает, если это возможно, из введенных цифр, нечетное число.
Результат выводится в консоль.
При невозможности создать нечетное число выводится сообщение –
«Создать нечетное число невозможно».
Каждое число вводится на отдельной строке.
*/
package main.kotlin

fun digits_input(){
    var num1: Int?
    var num2: Int?

    println("Введите первую цифру:")
    while(true){
        print("num1 = ")
        num1 = readln().toIntOrNull()
        if (num1 == null){
            println("Введите цифру!")
            continue
        } else
            if (num1 !in 0..9) {
                println("Цифра должна быть положительной!")
                continue
            } else
                break
    }
    println()

    println("Введите вторую цифру:")
    while(true){
        print("num2 = ")
        num2 = readln().toIntOrNull()
        if (num2 == null){
            println("Введите цифру!")
            continue
        } else
            if (num2 !in 0..9) {
                println("Цифра должна быть положительной!")
                continue
            } else
                break
    }

    println()
    show_res(num1, num2)
}

fun show_res(num1: Int?, num2: Int?){
    val res: String = num1.toString() + num2.toString()

    if (res.toInt() % 2 == 0)
        println("Создать нечетное число невозможно.")
    else
        println("Нечетное число: $res")
}

fun main(){
    digits_input()
}
