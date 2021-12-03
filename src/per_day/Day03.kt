package per_day

fun List<Int>.modeOrNull(): Int? {
    return this
        .groupingBy { it }
        .eachCount()
        .maxByOrNull { it.value }?.key
}

fun List<Char>.toDigits() = this.map { it.digitToInt() }

fun List<List<Char>>.swapRowsAndColumns(): List<List<Char>> {

    val result: MutableList<MutableList<Char>> = MutableList(this[0].size) { MutableList(this.size) { ' ' } }

    for (i in result.indices) {
        for (j in result[i].indices) {
            result[i][j] = this[j][i]
        }
    }

    return result
}

class Day03 : Day() {
    override fun firstPart(input: List<String>): Int {

        val columnsAsLines = input
            .map { it.toCharArray().toList() }
            .swapRowsAndColumns()
            .map { it.toDigits() }

        val g = columnsAsLines
            .fold(StringBuilder()) { acc, list ->
                val mode = list.modeOrNull()
                acc.append(if (mode == 0) '0' else '1')
            }

        val e = columnsAsLines
            .fold(StringBuilder()) { acc, list ->
                val mode = list.modeOrNull()
                acc.append(if (mode == 0) '1' else '0')
            }

        return Integer.parseInt(g.toString(), 2) * Integer.parseInt(e.toString(), 2)
    }

    override fun secondPart(input: List<String>): Int {

        val possibleForOx = input.toHashSet()
        val possibleForCo2 = input.toHashSet()

        // Find OX
        for (p in 0 until input[0].length) {

            val columnOxes = possibleForOx.map { it[p] }
            val zeros = columnOxes.count { it.digitToInt() == 0 }
            val ones = columnOxes.count { it.digitToInt() == 1 }

            when {
                zeros > ones -> {
                    val keepOx = possibleForOx.filter { it[p] == '0' }
                    possibleForOx.clear()
                    possibleForOx.addAll(keepOx)
                }
                zeros < ones -> {
                    val keepOx = possibleForOx.filter { it[p] == '1' }
                    possibleForOx.clear()
                    possibleForOx.addAll(keepOx)
                }
            }

            if (possibleForOx.size == 1) break
        }

        // FInd CO2
        for (p in 0 until input[0].length) {

            val column = possibleForCo2.map { it[p] }
            val zeros = column.count { it.digitToInt() == 0 }
            val ones = column.count { it.digitToInt() == 1 }

            when {
                zeros > ones -> {
                    val keepOx = possibleForCo2.filter { it[p] == '1' }
                    possibleForCo2.clear()
                    possibleForCo2.addAll(keepOx)
                }
                zeros < ones -> {
                    val keepOx = possibleForCo2.filter { it[p] == '0' }
                    possibleForCo2.clear()
                    possibleForCo2.addAll(keepOx)
                }
            }

            if (possibleForCo2.size == 1) break
        }

        return Integer.parseInt(possibleForOx.first(), 2) * Integer.parseInt(possibleForCo2.first(), 2)
    }

    override fun part1TestExpectedResult() = 198

    override fun part2TestExpectedResult() = 230

    override fun part1And2SeparateExplanations(): Boolean {
        return false
    }
}