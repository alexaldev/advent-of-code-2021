fun main() {

    fun part1(input: List<String>): Int {

        val position = SubmarinePosition(0, 0)

        for (s in input) {

            val commandAndValue = CommandWithValue.parse(s)
            when (commandAndValue.command) {
                Command.FORWARD -> position.horizontal += commandAndValue.value
                Command.DOWN -> position.depth += commandAndValue.value
                Command.UP -> position.depth -= commandAndValue.value
            }
        }

        return position.depth * position.horizontal
    }

    fun part2(input: List<String>): Int {

        val position = SubmarinePosition(0, 0)

        for (s in input) {
            val commandAndValue = CommandWithValue.parse(s)
            when (commandAndValue.command) {
                Command.FORWARD -> {
                    position.horizontal += commandAndValue.value
                    position.depth += position.aim * commandAndValue.value
                }
                Command.DOWN -> position.aim += commandAndValue.value
                Command.UP -> position.aim -= commandAndValue.value
            }
        }

        return position.depth * position.horizontal
    }

    val testInputPart1 = readInput("Day02_test")
    check(part1(testInputPart1) == 150)

    val testInputPart2 = readInput("Day02_test")
    check(part2(testInputPart2) == 900)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}

data class SubmarinePosition(
    var horizontal: Int = 0,
    var depth: Int = 0,
    var aim: Int = 0
)

data class CommandWithValue(
    val command: Command,
    val value: Int
) {

    companion object {
        fun parse(line: String): CommandWithValue {

            val (c, v) = line.split(' ')
            val command = Command.parse(c)
            val value = v.toInt()
            return CommandWithValue(command, value)
        }
    }
}

enum class Command {
    FORWARD, UP, DOWN;

    companion object {
        fun parse(s: String): Command {
            return when (s) {
                "forward" -> FORWARD
                "up" -> UP
                "down" -> DOWN
                else -> throw IllegalArgumentException("Invalid argument.")
            }
        }
    }
}
