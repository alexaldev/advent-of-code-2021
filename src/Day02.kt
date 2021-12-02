import java.lang.IllegalArgumentException

fun main() {
    fun part1(input: List<String>): Int {

        var forward = 0
        var depth = 0

        for (s in input) {
            val (command, value) = s.split(' ')
            when(command){
                "forward" -> forward += value.toInt()
                "up" -> depth -= value.toInt()
                "down" -> depth += value.toInt()
            }
        }

        println(forward * depth)
        return forward * depth
    }

    fun part2(input: List<String>): Int {

        var forward = 0
        var depth = 0
        var aim = 0

        for (s in input) {
            val (command, value) = s.split(' ')
            when(command){
                "forward" ->  {
                    forward += value.toInt()
                    depth += aim * value.toInt()
                }
                "up" -> aim -= value.toInt()
                "down" -> aim += value.toInt()
            }
        }

        println(forward * depth)
        return forward * depth
    }

    // test if implementation meets criteria from the description, like:
    val testInputPart1 = readInput("Day02_test")
    check(part1(testInputPart1) == 150)

    val testInputPart2 = readInput("Day02_test")
    check(part2(testInputPart2) == 900)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}

enum class Command {
    FORWARD, UP, DOWN;

    fun parse(s: String): Command {
        return when (s) {
            "forward" -> FORWARD
            "up" -> UP
            "down" -> DOWN
            else -> throw IllegalArgumentException("Invald input")
        }
    }
}
