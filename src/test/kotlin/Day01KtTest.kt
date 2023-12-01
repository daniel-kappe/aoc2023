import io.kotest.core.spec.style.ShouldSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class Day01KtTest : ShouldSpec({
 context("should work with examples from the puzzle") {

  withData(
   listOf(
    Pair("two1nine", 29),
    Pair("eightwothree", 83),
    Pair("abcone2threexyz", 13),
    Pair("xtwone3four", 24),
    Pair("4nineeightseven2", 42),
    Pair("zoneight234", 14),
    Pair("7pqrstsixteen", 76)
   ),
   {
    parseNumbersInLine(it.first) shouldBe it.second
   })
 }

 context("should correctly parse meaner examples") {
  parseNumbersInLine("twone1twone") shouldBe 21
  parseNumbersInLine("eightwo") shouldBe 82
 }
})
