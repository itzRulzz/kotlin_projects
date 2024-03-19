fun main()
{
    val secretCode = generateSecretCode()

    println("Компьютер загадал 4 разные цифры, комбинацию которых вам нужно разгадать.\n" +
            "Если верная цифра находится в верной позиции, то она зовется быком - \uD83D\uDC02\n" +
            "Если цифра верная, но находится не там, то это корова - \uD83D\uDC04\n" +
            "Вам нужно определить нужную комбинацию по подсказкам в виде быков и коров.")

    var turnCount = 1
    var currentInput = ""
    while(true)
    {
        while (!is4differentDigits(currentInput)) {
            print("Попытка №$turnCount. Введите комбинацию из 4-х различных цифр:")
            currentInput = readln()
            if (!is4differentDigits(currentInput) || !isNumber(currentInput)) {
                println("Введенное число не состоит из 4-х различных цифр или содержит недопустимые символы. Попробуйте еще раз.")
            }
        }

        if (countBulls(currentInput,secretCode)>0 || countCows(currentInput,secretCode)>0)
        {
            print("Результат: ")
            for (i in 1..countBulls(currentInput,secretCode)) print("\uD83D\uDC02")
            print(" ")
            for (i in 1..countCows(currentInput,secretCode)) print("\uD83D\uDC04")
            println()
        }
        else
        {
            println("Вообще мимо.")
        }

        if (currentInput == secretCode)
        {
            println("Поздравляю, ответ был $secretCode!")
            return
        }

        turnCount++
        currentInput = ""
    }

}
