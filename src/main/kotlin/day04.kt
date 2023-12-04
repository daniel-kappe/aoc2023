import java.lang.Math.pow
import kotlin.math.min

fun solveDay04() {
    val puzzleInput = readFile("input04", String::toString)

    val simpleCase = simpleCase04(puzzleInput)
    val complexCase = complexCase04(puzzleInput)

    println("First part:  $simpleCase")
    println("Second part: $complexCase")
}

fun simpleCase04(puzzleInput: List<String>): Int {
    val cards = puzzleInput
        .map { Card.createFromLine(it) }
        .map { it.getPoints() }
    return cards.sum()
}

fun complexCase04(puzzleInput: List<String>): Int {
    val cards = puzzleInput
        .map { Card.createFromLine(it) }
    for (i in 0..cards.size - 1) {
        val wins = cards[i].getWins()
        for (j in i + 1..min( i + wins, cards.size - 1)) {
            cards[j].count += cards[i].count
        }
    }
    return cards.map { it.count }.sum()
}

data class Card(val id: Int, val winningNumbers: Set<Int>, val ownNumbers: List<Int>, var count: Int) {

    fun getWins(): Int {
        return ownNumbers
            .filter { winningNumbers.contains(it) }
            .size
    }

    fun getPoints(): Int {
        return pow(2.0, getWins().toDouble() - 1).toInt()
    }
    companion object {
        fun createFromLine(line: String): Card {
            val numbersRegex = "\\d+".toRegex()
            val cardId = numbersRegex.findAll(line).first().value.toInt()
            val stripedGameId = line.split(":").last()
            val splitNumberGroups = stripedGameId.split("|")
            val winningNumbers = splitNumberGroups.first()
            val ownNumbers = splitNumberGroups.last()
            return Card(
                cardId,
                numbersRegex.findAll(winningNumbers).map { it.value.toInt() }.toSet(),
                numbersRegex.findAll(ownNumbers).map { it.value.toInt() }.toList(),
                1
            )
        }
    }
}