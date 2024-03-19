const val MIN_SECRET_CODE = 123
const val MAX_SECRET_CODE = 9876
const val SECRET_CODE_LENGTH = 4

fun is4differentDigits(input: String): Boolean {
    if (input.length != SECRET_CODE_LENGTH) return false

    val digitListFromInput = input.toList()

    return digitListFromInput.distinct().size == SECRET_CODE_LENGTH
}