fun solveDay03() {
    val puzzleInput = readFile("input03", String::toString)

    val simpleCase = simpleCase03(puzzleInput)
    val complexCase = complexCase03(puzzleInput)

    println("First part:  $simpleCase")
    println("Second part: $complexCase")
}

fun simpleCase03(puzzleInput: List<String>): Int {
    val numbers = getAllNumbers(puzzleInput)
    val parts = getAllParts(puzzleInput)
        .map { attachAdjacentPartNumbers(it, numbers) }

    return parts.sumOf { it.numbers.sum() }
}

fun complexCase03(puzzleInput: List<String>): Int {
    val numbers = getAllNumbers(puzzleInput)
    val gearRatios = getAllParts(puzzleInput)
        .map { attachAdjacentPartNumbers(it, numbers) }
        .filter { it.symbol == "*" }
        .filter { it.numbers.size == 2 }
        .map { it.numbers.first() * it.numbers.last() }

    return gearRatios.sum()
}

private fun getAllNumbers(puzzleInput: List<String>) = puzzleInput
    .flatMapIndexed { rowIndex, rowContent ->
        "\\d+".toRegex().findAll(rowContent).map { PartNumber(Pair(rowIndex, it.range), it.value.toInt()) }.toList()
    }

private fun getAllParts(puzzleInput: List<String>) = puzzleInput
    .flatMapIndexed { rowIndex, rowContent ->
        "[^.^\\d]".toRegex().findAll(rowContent).map { Part(Pair(rowIndex, it.range.first), it.value) }.toList()
    }

fun attachAdjacentPartNumbers(part: Part, partNumbers: List<PartNumber>): Part {
    val adjacentPartNumbers = partNumbers
        .filter { it.isAdjacentTo(part) }
    part.numbers = adjacentPartNumbers.map { it.value }
    return part
}

data class Part(val position: Pair<Int, Int>, val symbol: String, var numbers: List<Int> = mutableListOf())

data class PartNumber(val position: Pair<Int, IntRange>, val value: Int) {
    fun isAdjacentTo(part: Part): Boolean {
        if (position.first == part.position.first) {
            if (position.second.first - 1 == part.position.second) return true
            if (position.second.last + 1 == part.position.second) return true
            return false
        }
        if (position.first + 1 == part.position.first || position.first - 1 == part.position.first) {
            if (position.second.first - 1 <= part.position.second && part.position.second <= position.second.last + 1) return true
        }
        return false
    }
}