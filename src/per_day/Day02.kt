package per_day

class Day02 : Day() {
    override fun firstPart(input: List<String>): Int {
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

    override fun secondPart(input: List<String>): Int {
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

    override fun part1TestExpectedResult() = 150
    override fun part2TestExpectedResult() = 900
    override fun part1And2SeparateExplanations() = false
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
            with(line.split(' ').take(2)) {
                return CommandWithValue(Command.parse(this[0]), this[1].toInt())
            }
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
