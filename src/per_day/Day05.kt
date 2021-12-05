package per_day

import kotlin.math.max
import kotlin.math.min

data class Point(val x: Int, val y: Int)

class Day05 : Day() {

    override fun firstPart(input: List<String>): Int {

        val grid = hashMapOf<Point, Int>()

        input.forEach { line ->
            val (l, r) = line.split(" -> ")
            val (x1, y1) = l.split(',').map { it.toInt() }
            val (x2, y2) = r.split(',').map { it.toInt() }
            if (x1 == x2 || y1 == y2) {
                for (x in min(x1, x2)..(max(x1, x2))) {
                    for (y in min(y1, y2)..(max(y1, y2))) {
                        grid[Point(x, y)] = (grid[Point(x, y)] ?: 0) + 1
                    }
                }
            }
        }
        printGrid(grid)
        return grid.values.count { it > 1 }
    }

    private fun printGrid(g: Map<Point, Int>) {

        for (x in 0..(g.keys.map { it.x }.maxByOrNull { it }!!)) {
            for (y in 0..(g.keys.map { it.y }.maxByOrNull { it }!!)) {
                if (g[Point(x, y)] != null) {
                    print("${g[Point(x, y)]}")
                } else {
                    print('.')
                }
            }
            println()
        }
    }

    override fun secondPart(input: List<String>): Int {

        val grid = hashMapOf<Point, Int>()

        input.forEach { line ->
            val (l, r) = line.split(" -> ")
            val (x1, y1) = l.split(',').map { it.toInt() }
            val (x2, y2) = r.split(',').map { it.toInt() }
            if (x1 == x2 || y1 == y2) {
                for (x in min(x1, x2)..(max(x1, x2))) {
                    for (y in min(y1, y2)..(max(y1, y2))) {
                        grid[Point(x, y)] = (grid[Point(x, y)] ?: 0) + 1
                    }
                }
            }
        }
        printGrid(grid)
        return grid.values.count { it > 1 }
    }

    override fun part1TestExpectedResult(): Int {
        return 5
    }

    override fun part2TestExpectedResult(): Int {
        return 12
    }

    override fun part1And2SeparateExplanations(): Boolean {
        return false
    }
}
