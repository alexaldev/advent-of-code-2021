fun main() {
    fun part1(input: List<String>): Int {
        return input.map { it.toInt() }
            .zipWithNext { a, b -> a < b }
            .count { it }
    }

    fun part2(input: List<String>): Int {
        return input
            .map { it.toInt() }
            .windowed(size = 3, step = 1, partialWindows = false) { it.sum() }
            .zipWithNext { a, b -> (a < b) }
            .count { it }
    }

    // test if implementation meets criteria from the description, like:
    val testInputPart1 = readInput("Day01_test")
    check(part1(testInputPart1) == 7)

    val testInputPart2 = readInput("Day01_part2test")
    check(part2(testInputPart2) == 5)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
