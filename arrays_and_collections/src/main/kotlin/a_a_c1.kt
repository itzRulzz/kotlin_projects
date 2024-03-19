/*
Создать программу, выполняющую следующий функционал:
- запрашивается количество строк и столбцов для двумерного массива
- вводится необходимое количество трехзначных чисел (числа могут повторяться)
- подсчитывается количество различных цифр в полученном массиве
- на консоль выводится двумерный массив из введенных чисел и количество
различных цифр используемых в данном массиве.

Например, для массива
100   951   101   950
519   999   155   501
510   911   144   554
выведется результат: В массиве использовано 5 различных цифр.
*/

fun arr_vars_input() {
    var n: Int?
    var m: Int?

    println("Введите количество строк массива (целое число): ")
    while (true) {
        print("n = ")
        n = readln().toIntOrNull()
        if (n == null) {
            println("Введите целое число!")
            continue
        } else {
            if (n < 1) {
                println("Число должно быть положительным!")
                continue
            } else
                break
        }
    }

    println("Введите количество столбцов массива (целое число): ")
    while (true) {
        print("m = ")
        m = readln().toIntOrNull()
        if (m == null) {
            println("Введите целое число!")
            continue
        } else {
            if (m < 1) {
                println("Число должно быть положительным!")
                continue
            } else
                break
        }
    }

    n = n!!.toInt() ?: 0
    m = m!!.toInt() ?: 0

    arr_input(n, m)
}

fun arr_input(n: Int, m: Int){
    println()
    println("Заполните массив, введя целые трехзначные числа.")
    val arr = Array(n) {Array(m) {0} }
    for (i in 0..<n) {
        for (j in 0..<m) {
            var input: Int? = null
            while (input == null || input !in -999..-100 && input !in 100..999) {
                print("[$i $j] = ")
                input = readln().toIntOrNull()
                if (input == null)
                    println("Введите целое число!")
                else
                    if (input !in -999..-100 && input !in 100..999)
                        println("Введите трехзначное число!")
            }
            arr[i][j] = input
        }
    }

    show_arr(n, m, arr)
    count_digits(arr)
}

fun show_arr(n: Int, m: Int, arr: Array<Array<Int>>){
    println()

    for (i in 0..<n){
        for (j in 0..<m){
            print("${arr[i][j]} ")
        }
        println()
    }
}

fun count_digits(arr: Array<Array<Int>>){
    val all_arr_elems = arr.flatten()
    val arr_str = all_arr_elems.joinToString("")
    val arr_str_pos = arr_str.replace("-", "")
    val arr_digits = arr_str_pos.toSet()

    println()
    println("В массиве использовано ${arr_digits.size} различных(-е) цифр(-ы).")
}

fun main(){
    arr_vars_input()
}
