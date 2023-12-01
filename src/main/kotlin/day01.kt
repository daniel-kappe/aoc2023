fun solveDay01() {
    val puzzleInput = readFile("input01", String::toString)

    val simpleCase = simpleCase01(puzzleInput)
    val complexCase = complexCase01(puzzleInput)

    println("First part:  $simpleCase")
    println("Second part: $complexCase")
}

fun simpleCase01(puzzleInput: List<String>): Int {
    return puzzleInput
        .map {
            "\\d".toRegex()
                .findAll(it)
        }
        .map {
            it.first().value + it.last().value
        }
        .map(String::toInt)
        .sum()
}

fun complexCase01(puzzleInput: List<String>): Int {
    return puzzleInput
        .map { parseNumbersInLine(it) }
        .sum()
}

fun parseNumbersInLine(line: String): Int {
    val number_matcher = STRING_DIGIT_MAP.keys
        .joinToString(separator = "|", prefix = "(", postfix = ")")
        .toRegex()
    val firstNumber = number_matcher.findAll(line).first().value
    val firstDigit = STRING_DIGIT_MAP.getOrDefault(firstNumber, firstNumber)
    val secondNumberStart = line.lastIndexOfAny(STRING_DIGIT_MAP.keys)
    val secondNumber = number_matcher.findAll(line, secondNumberStart).first().value
    val secondDigit = STRING_DIGIT_MAP.getOrDefault(secondNumber, secondNumber)
    return "$firstDigit$secondDigit".toInt()
}

val STRING_DIGIT_MAP = mapOf(
    "one" to "1",
    "two" to "2",
    "three" to "3",
    "four" to "4",
    "five" to "5",
    "six" to "6",
    "seven" to "7",
    "eight" to "8",
    "nine" to "9",
    "1" to "1",
    "2" to "2",
    "3" to "3",
    "4" to "4",
    "5" to "5",
    "6" to "6",
    "7" to "7",
    "8" to "8",
    "9" to "9"
)