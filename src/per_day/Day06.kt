package per_day

import java.io.File
import java.util.*

fun sol(input: List<String>, days: Int = 80): Long {

    val lst = MutableList(10) { 0L }
    input[0].split(',').map { it.toInt() }.forEach { lst[it] = lst[it].plus(1L) }

    repeat(days) {
        val toGiveBirth: Long = lst[0]
        Collections.rotate(lst, -1)
        lst[9] = 0
        if (toGiveBirth > 0) {
            lst[6] += toGiveBirth
            lst[8] += toGiveBirth
        }
    }
    return lst.sum()
}

fun main() {
    println(sol(File("src", "inputs/Day06.txt").readLines(), 256))
}