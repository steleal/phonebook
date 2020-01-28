package phonebook

import java.io.File
import kotlin.system.measureTimeMillis

fun main() {
    val lines = File("directory.txt").readLines()
    val searchLines = File("find.txt").readLines()
    val res = Result()
    println("Start searching...")

    val time = measureTimeMillis {
        linearSearch(lines, searchLines, res)
    }
    printResult(res, time)
}

class Result(var found: Int = 0, var all: Int = 0)

private fun printResult(result: Result, millis: Long) {
    val ms = millis % 1000
    val allsec = millis / 1000
    val sec = allsec % 60
    val min = allsec / 60
    println("Found ${result.found} / ${result.all} entries. Time taken: $min min. $sec sec. $ms ms.")
}

private fun linearSearch(lines: List<String>, searchLines: List<String>, result: Result) {
    var cntAll: Int = 0
    var cntSearch: Int = 0

    for (line in searchLines) {
        cntAll++
        for (testLine in lines) {
            if (testLine.contains(line)) {
                cntSearch++
                break
            }
        }
    }
    result.all = cntAll
    result.found = cntSearch
}
