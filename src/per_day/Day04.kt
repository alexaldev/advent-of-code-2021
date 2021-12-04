package per_day

class Day04 : Day() {

    companion object {
        val whitespace = "\\s+".toRegex()
    }

    override fun firstPart(input: List<String>): Int {

        val moves = input[0]
        val boards = hashSetOf<Board>()
        var boardId = 0
        val boardsLines = input.subList(2, input.size)
        val a: ArrayDeque<Int> = ArrayDeque(boardsLines.indicesOf(""))

        // First board, can be merged with the bottom creation codebase
        boards.add(Board.create(boardsLines.subList(0, a.first()), boardId++))

        // This can be simplified using a for loop with step
        while (a.isNotEmpty()) {
            val startIndex = a.removeFirst()
            val endIndex = if (a.isEmpty()) boardsLines.size else a.first()
            boards.add(Board.create(boardsLines.subList(startIndex + 1, endIndex), boardId++))
        }

        moves.split(',')
            .map { it.toInt() }
            .forEach { move ->
                boards.forEach { it.mark(move) }
                val winningBoard = boards.firstOrNull { it.wins() }
                if (winningBoard != null) {
                    return move * winningBoard.sumOfRemainingElements()
                }
            }

        return -1
    }

    private fun List<String>.indicesOf(s: String): List<Int> {
        val result = mutableListOf<Int>()
        this.forEachIndexed { index, element ->
            if (element == s) {
                result.add(index)
            }
        }
        return result
    }

    override fun secondPart(input: List<String>): Int {

        val moves = input[0]
        val boards = hashSetOf<Board>()
        var boardId = 0
        val boardsLines = input.subList(2, input.size)
        val a: ArrayDeque<Int> = ArrayDeque(boardsLines.indicesOf(""))

        boards.add(Board.create(boardsLines.subList(0, a.first()), boardId++))

        // This can be simplified with step
        while (a.isNotEmpty()) {
            val startIndex = a.removeFirst()
            val endIndex = if (a.isEmpty()) boardsLines.size else a.first()
            boards.add(Board.create(boardsLines.subList(startIndex + 1, endIndex), boardId++))
        }

        val finishedBoards = mutableSetOf<Board>()

        moves.split(',')
            .map { it.toInt() }
            .forEach { move ->
                boards.forEach { it.mark(move) }

                with(boards.filter { it.wins() }) { finishedBoards.addAll(this) }

                if (finishedBoards.size == boards.size) {
                    return finishedBoards.last().sumOfRemainingElements() * move
                }
            }

        return -1
    }

    override fun part1TestExpectedResult(): Int {
        return 4512
    }

    override fun part2TestExpectedResult(): Int {
        return 1924
    }

    override fun part1And2SeparateExplanations(): Boolean {
        return false
    }
}

data class Board(private val id: Int = 0) {
    private val rows: MutableMap<Int, MutableSet<Int>> = mutableMapOf()
    private val columns: MutableMap<Int, MutableSet<Int>> = mutableMapOf()

    fun mark(i: Int) {
        rows.forEach { (_, elements) ->
            if (elements.contains(i)) {
                elements.remove(i)
            }
        }
        columns.forEach { (_, elements) ->
            if (elements.contains(i)) {
                elements.remove(i)
            }
        }
    }

    fun sumOfRemainingElements(): Int {
        return rows.values.flatten().sum()
    }

    fun wins(): Boolean {
        return rows.any { it.value.isEmpty() } || columns.any { it.value.isEmpty() }
    }

    companion object {

        fun create(lines: List<String>, id: Int = 0): Board {

            val result = Board(id)
            for (i in lines.indices) {
                val lineElements = lines[i].trim().split(Day04.whitespace)
                lineElements.forEachIndexed { index, s ->

                    if (result.rows[i] == null) result.rows[i] = hashSetOf()
                    result.rows[i]?.add(lineElements[index].toInt())

                    if (result.columns[index] == null) result.columns[index] = hashSetOf()
                    result.columns[index]?.add(s.toInt())
                }
            }
            return result
        }
    }
}