/*
Создать приложение, с помощью которого пользователь,
введя целое число n и основание степени x узнает,
существует ли целочисленный показатель степени y
для которого выполняется равенство x^y = n.
В случае, если показатель существует – вычислить его и вывести.
В противном случае вывести текст – «Целочисленный показатель не существует».
*/
package main.kotlin
import kotlin.math.log
import kotlin.math.round
import kotlin.math.roundToInt

fun nums_input(){
    var n: Int?
    var x: Double?

    println("Введите основание степени: ")
    while(true){
        print("x = ")
        x = readln().toDoubleOrNull()
        if (x == null){
            println("Введите корректное число (десятичная дробь через .)!")
            continue
        }
        else
            break
    }

    println("Введите результат возведения в степень (целое число): ")
    while(true){
        print("n = ")
        n = readln().toIntOrNull()
        if (n == null){
            println("Введите целое число!")
            continue
        }
        else
            break
    }

    println()
    log_check(n, x)
}

fun log_check(n: Int?, x: Double?){
    if (n != null && x != null){
        val y: Double = log(n.toDouble(), x)

        if (y != Double.POSITIVE_INFINITY && y != Double.NEGATIVE_INFINITY && round(y) == y){
            println("y = $y")
        } else {
            println("Целочисленный показатель не существует.")
        }
    }
}

fun main(){
    nums_input()
}
