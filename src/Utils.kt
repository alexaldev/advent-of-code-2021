import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src/inputs", "$name.txt").readLines()

fun readTestInput(className: String, applyPart2Postfix: Boolean = false): List<String> {

    val postfix = if (applyPart2Postfix) "_part2test" else "_test"
    return File("src/explanationTests", "${className}${postfix}.txt").readLines()
}

/**
 * Converts string to md5 hash.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)
