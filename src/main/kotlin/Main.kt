fun main() {
    logDayTitle(1)
    solveDay01()
    logDayTitle(2)
    solveDay02()
    logDayTitle(3)
    solveDay03()
    logDayTitle(4)
    solveDay04()
}

fun logDayTitle(day: Int) {
    println(String.format("========== %02d ==========", day))
}