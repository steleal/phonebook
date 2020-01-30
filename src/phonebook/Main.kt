package phonebook

import java.io.File
import kotlin.system.measureTimeMillis

fun main() {
    val lines = File("directory.txt").readLines()
    val searchLines = File("find.txt").readLines()

    val linearSearchTime = testLinearSearch(searchLines, lines)

    val secondSearchTime = testJumpSearchWithBubbleSort(searchLines, lines, linearSearchTime * 10L)


}

private fun testJumpSearchWithBubbleSort(searchLines: List<String>, lines: List<String>, maxSearchTime: Long): Long {
    println("Start searching (bubble sort + jump search)...")

    var stopped = false
    val bubbleSortTime = measureTimeMillis {
        stopped = bubleSortWithStop(lines, maxSearchTime)
    }

    val result = if (stopped) {
         searchAll(searchLines, lines, ::linearSearch)
    } else {
        searchAll(searchLines, lines, ::jumpSearch)
    }

    val wholeTime = bubbleSortTime + result.millisec
    val time = wholeTime.millisecToString()

    println("${result.found} / ${result.all} entries. Time taken: $time")
    print("Sorting time: ${bubbleSortTime.millisecToString()}")
    if (stopped) print(" - STOPPED, moved to linear search")
    println()
    print("Searching time: ${result.millisec.millisecToString()}")

    return wholeTime
}

private fun testLinearSearch(searchLines: List<String>, lines: List<String>): Long {
    println("Start searching (linear search)...")

    val result = searchAll(searchLines, lines, ::linearSearch)

    val time = result.millisec.millisecToString()
    println("${result.found} / ${result.all} entries. Time taken: $time")

    return result.millisec
}

private fun searchAll(what: List<String>, where: List<String>, search: (String, List<String>) -> Int): SearchResult {
    var cntAll: Int = 0
    var cntSearch: Int = 0

    val searchTimeInMs = measureTimeMillis {
        for (obj in what) {
            cntAll++
            val itemIndex = search(obj, where)
            if (itemIndex > -1) cntSearch++
        }
    }

    return SearchResult(cntSearch, cntAll, searchTimeInMs)
}
