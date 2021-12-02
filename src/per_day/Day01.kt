package per_day

class Day01 : Day() {
    override fun firstPart(input: List<String>): Int {
        return input.map { it.toInt() }
            .zipWithNext { a, b -> a < b }
            .count { it }
    }

    override fun secondPart(input: List<String>): Int {
        return input
            .map { it.toInt() }
            .windowed(size = 3, step = 1, partialWindows = false) { it.sum() }
            .zipWithNext { a, b -> (a < b) }
            .count { it }
    }

    override fun part1TestExpectedResult(): Int {
        return 7
    }

    override fun part2TestExpectedResult(): Int {
        return 5
    }
    override fun part1And2SeparateExplanations() = true
}
