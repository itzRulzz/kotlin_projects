fun generateSecretCode(): String {
    var secretCode: String
    do {
        secretCode = (0..9999).random().toString().padStart(4, '0')
    } while (!is4differentDigits(secretCode))
    return secretCode
}
