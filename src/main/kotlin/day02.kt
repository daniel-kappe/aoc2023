import kotlin.math.max

fun solveDay02() {
    val puzzleInput = readFile("input02", String::toString)

    val simpleCase = simpleCase02(puzzleInput)
    val complexCase = complexCase02(puzzleInput)

    println("First part:  $simpleCase")
    println("Second part: $complexCase")
}

fun simpleCase02(puzzleInput: List<String>): Int {
    val games = puzzleInput
        .map { Game.createGameFromLine(it) }
    val validGames = games
        .filter { it.isValid() }
    val validGameIds = validGames
        .map { it.id }
    return validGameIds.sum()
}

fun complexCase02(puzzleInput: List<String>): Int {
    val games = puzzleInput
        .map { Game.createGameFromLine(it) }
    val gameWithPower = games
        .map { it.getPower() }
    return gameWithPower.sum()
}

data class Game(val id: Int, val rounds: List<Round>) {
    fun isValid(): Boolean {
        rounds.forEach {
            if (!it.isValid()) {
                return false
            }
        }
        return true
    }

    fun getPower(): Int {
        val minimumCubes = getMinimumCubes()
        return minimumCubes.red * minimumCubes.green * minimumCubes.blue
    }

    fun getMinimumCubes(): Round {
        return rounds
            .reduce { acc, round -> Round(max(acc.red, round.red), max(acc.green, round.green), max(acc.blue, round.blue)) }
    }

    companion object {
        fun createGameFromLine(line: String): Game {
            val gameId = "Game (\\d+):".toRegex().find(line)?.groupValues?.last()?.toInt() ?: -1
            val rounds = line
                .split(":")
                .last()
                .split(";")
                .map { Round.createRoundFromLine(it) }
            return Game(gameId, rounds)
        }
    }
}

data class Round(val red: Int, val green: Int, val blue: Int) {
    fun isValid(): Boolean {
        return red < 13 && green < 14 && blue < 15
    }

    companion object {
        fun createRoundFromLine(line: String): Round {
            val red = "(\\d+) red".toRegex().find(line)?.groupValues?.last()?.toInt() ?: 0
            val green = "(\\d+) green".toRegex().find(line)?.groupValues?.last()?.toInt() ?: 0
            val blue = "(\\d+) blue".toRegex().find(line)?.groupValues?.last()?.toInt() ?: 0
            return Round(red, green, blue)
        }
    }
}