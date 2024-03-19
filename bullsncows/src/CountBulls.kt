fun countBulls(input: String, reference: String):Int
{
    var output = 0

    val digitListFromInput = toDigitList(input)
    val digitListFromReference = toDigitList(reference)

    for (i in digitListFromInput.indices)
    {
        if (digitListFromInput[i]==digitListFromReference[i])
        {
            output++
        }
    }
    return output
}
