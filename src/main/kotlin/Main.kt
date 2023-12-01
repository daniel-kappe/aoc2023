fun main(args: Array<String>) {
    val puzzleInput = readFile("input01", String::toString)

    val total = puzzleInput
        .map {
            it
                .replace("one", "one1one")
                .replace("two", "two2two")
                .replace("three", "three3three")
                .replace("four", "four4four")
                .replace("five", "five5five")
                .replace("six", "six6six")
                .replace("seven", "seven7seven")
                .replace("eight", "eight8eight")
                .replace("nine", "nine9nine")
        }
        .map {
            "\\d"
                .toRegex()
                .findAll(it)
        }
        .map {
            it.first().value + it.last().value
        }
        .map { it.toInt() }
        .sum()
    println(total)
    solveDay01()
}

private fun s(line: String): String {
    val one = "one".toRegex()
    val two = "two".toRegex()
    val three = "three".toRegex()
    val four = "four".toRegex()
    val five = "five".toRegex()
    val six = "six".toRegex()
    val seven = "seven".toRegex()
    val eight = "eight".toRegex()
    val nine = "nine".toRegex()

    val first = one.replace(line, "1")
    val second = two.replace(first, "2")
    val third = three.replace(second, "3")
    val fourth = four.replace(third, "4")
    val fifth = five.replace(fourth, "5")
    val sixth = six.replace(fifth, "6")
    val seventh = seven.replace(sixth, "7")
    val eighth = eight.replace(seventh, "8")
    return nine.replace(eighth, "9")
}