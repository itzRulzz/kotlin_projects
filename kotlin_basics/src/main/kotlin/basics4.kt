/*
Создать приложение, с помощью которого пользователь, введя два числа и символ операции, узнает результат.
Символами операции могут быть: / — деление, * — умножение, + — сложение, - — вычитание. Числа могут быть вещественными.
Числа и знак операции разделяются между собой одним пробелом. Ввод производится в формате - ЧИСЛО1 ЧИСЛО2 ОПЕРАЦИЯ.
*/

package main.kotlin

fun string_input(): String {
    val equation = readln()

    if (equation == "") {
        println("Введите выражение.")
        return string_input()
    }
    if (equation.count { it == ' ' } != 2) {
        println("Введите выражение с пробелами между числами и операцией.")
        return string_input()
    }
    if (',' in equation){
        println("Вместо , в десятичной дробе должна быть .")
        return string_input()
    }
    val (num1, num2, operation) = string_splitting(equation)
    if (num2 == 0.0 && operation == "/"){
        println("На ноль делить нельзя!")
        return string_input()
    }
    if (operation != "+" && operation != "-" && operation != "/" && operation != "*"){
        println("Введите корректную операцию.")
        return string_input()
    }

    return equation
}

fun equation_calculation(equation: String): Double{
    val (num1, num2, operation) = string_splitting(equation)

    return when (operation) {
        "+" -> num1 + num2
        "-" -> num1 - num2
        "*" -> num1 * num2
        "/" -> num1 / num2
        else -> {
            println("Непредвиденная ошибка. Перезагрузите порграмму.")
            equation_calculation(string_input())
        }
    }
}

fun string_splitting(equation: String): Triple<Double, Double, String> {
    val splitted_equation = equation.split(' ')

    val num1 = splitted_equation[0].toDouble()
    val num2 = splitted_equation[1].toDouble()
    val operation = splitted_equation[2]

    return Triple(num1, num2, operation)
}

fun main(){
    println("Введите арифмитическое выражение из двух чисел вида «ЧИСЛО1 ЧИСЛО2 ОПЕРАЦИЯ» (пробелы обязательны): ")
    val equation = string_input()
    val (num1, num2, operation) = string_splitting(equation)

    println("Ответ: $num1 $operation $num2 = ${equation_calculation(equation)}")
}
