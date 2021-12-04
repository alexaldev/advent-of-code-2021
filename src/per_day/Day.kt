package per_day

import readInput
import readTestInput

abstract class Day {

    protected abstract fun firstPart(input: List<String>): Int
    protected abstract fun secondPart(input: List<String>): Int

    protected abstract fun part1TestExpectedResult(): Int
    protected abstract fun part2TestExpectedResult(): Int
    protected abstract fun part1And2SeparateExplanations(): Boolean

    enum class Part {
        First, Second, Both
    }

    fun run(part: Part) {
        when(part) {
            Part.First -> runFirst()
            Part.Second -> runSecond()
            Part.Both ->  {
                runFirst()
                runSecond();
            }
        }
    }

    private fun runFirst() {

        // Run the explanation example
        val testLines = readTestInput(javaClass.simpleName)
        val testResult = firstPart(testLines)
        check(part1TestExpectedResult() == testResult) {"Expected ${part1TestExpectedResult()} but was $testResult"}
        println("Explanation example of part 1 passed!")

        // Run on the given input
        println("Result of solution on the input: ${firstPart(readInput(javaClass.simpleName))}")
    }

    private fun runSecond() {

        val testLines = readTestInput(javaClass.simpleName, part1And2SeparateExplanations())
        val testResult = secondPart(testLines)
        check(part2TestExpectedResult() == testResult) { "Expected ${part2TestExpectedResult()} but was $testResult"}
        println("Explanation example of part 2 passed!")

        // Run on the given input
        println("Result of solution on the input: ${secondPart(readInput(javaClass.simpleName))}")
    }
}