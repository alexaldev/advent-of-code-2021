package per_day

import java.io.File
import kotlin.math.abs
import kotlin.math.pow

fun p1(input: List<Int>){

    val inputSorted = input.sorted()
    val median = when(inputSorted.size % 2) {
        0 -> (inputSorted[inputSorted.size / 2] + inputSorted[inputSorted.size / 2 - 1]) / 2
        1 -> inputSorted[inputSorted.size / 2]
        else -> -1 // Never happens
    }

    println(inputSorted.fold(0) { acc, i -> acc + (abs(i - median)) })
}

fun s(n: Int): Int {
    return (n.toDouble().pow(2.toDouble()).toInt() + n) / 2
}

fun fuel(s: Int, e: Int): Int {
    return s(if (e > s) e-s else s-e)
}

fun p2(input: List<Int>) {

    val mean = input.sum() / input.size
    println("Trying mean+1 (${mean+1}), exists in input: ${input.contains(mean+1)} -> ${input.sumOf { fuel(it, mean + 1) }}")
    println("Trying mean-1 (${mean-1}), exists in input: ${input.contains(mean-1)} -> ${input.sumOf { fuel(it, mean - 1) }}")
    println("Trying mean (${mean}), exists in input: ${input.contains(mean)} -> ${input.sumOf { fuel(it, mean) }}")
}

fun main() {
    val d = File("src/inputs/", "Day07.txt")
        .readLines()[0]
        .split(',')
        .map { it.toInt() }

    p2(d)
}