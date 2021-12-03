package per_day

class Day03: Day() {
    override fun firstPart(input: List<String>): Int {
        
        var g = ""
        var e = ""

        for (p in 0 until input[0].length) {

            val column = input.map { it[p] }
            val zeros = column.count { it.digitToInt() == 0 }
            val ones = column.count { it.digitToInt() == 1 }
            if (zeros > ones) {
                g += '0'
                e += '1'
            } else {
                g += '1'
                e += '0'
            }
        }

        return Integer.parseInt(g, 2) * Integer.parseInt(e, 2)
    }


    override fun secondPart(input: List<String>): Int {



        val possibleForOx = input.toHashSet()
        val possibleForCo2 = input.toHashSet()

        var foundOx = false
        var foundCo2 = false

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
                else -> {
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
                else -> {
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