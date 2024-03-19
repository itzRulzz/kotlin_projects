/*
Создать приложение, которое преобразует введенное пользователем натуральное число
из 10-ичной системы в двоичную.
*/
package main.kotlin

fun number_input(): Int{
    try{
        val num = readln().toInt()
        if (num <= 0){
            println("Число должно быть больше нуля!")
            return number_input()
        }
        return num
    } catch(e: NumberFormatException) {
        println("Введите ЧИСЛО.")
        return number_input()
    }
}

fun main(){
    println("Введите натуральное число для преобразования в двоичную систему: ")
    var num = number_input()

    println("$num в десятичной = ${Integer.toBinaryString(num)} в двоичной")
}
