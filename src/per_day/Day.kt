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
        First, Second
    }

    fun run(part: Part) {
        if (part == Part.First) runFirst()
        else runSecond()
    }

    private fun runFirst() {

        // Run the explanation example
        val testLines = readTestInput(javaClass.simpleName)
        check(part1TestExpectedResult() == firstPart(testLines))
        println("Explanation example of part 1 passed!")

        // Run on the given input
        println("Result of solution on the input: ${firstPart(readInput(javaClass.simpleName))}")
    }

    private fun runSecond() {

        val testLines = readTestInput(javaClass.simpleName, part1And2SeparateExplanations())
        check(part2TestExpectedResult() == secondPart(testLines))
        println("Explanation example of part 2 passed!")

        // Run on the given input
        println("Result of solution on the input: ${secondPart(readInput(javaClass.simpleName))}")
    }
}