fun isNumber(input: String): Boolean {
    return input.all { it in '0'..'9' }
}
