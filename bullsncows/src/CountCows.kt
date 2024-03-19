fun countCows(input: String, reference: String): Int {
    val digitListFromInput = toDigitList(input)
    val digitListFromReference = toDigitList(reference)

    val commonDigits = digitListFromInput.intersect(digitListFromReference.toSet()).size
    val bullCount = countBulls(input, reference)

    return commonDigits - bullCount
}
