package phonebook

import java.io.File
import kotlin.system.measureTimeMillis

fun main() {
    val lines = File("directory.txt").readLines()
    val searchLines = File("find.txt").readLines()
    var searchResult: SearchResult? = null

    println("Start searching (linear search)...")
    val linearSearchTime = measureTimeMillis {
        searchResult = searchAll(searchLines, lines, ::linearSearch)
    }
    val lsTime = linearSearchTime.millisecToString()
    println("$searchResult Time taken: $lsTime")

}

fun searchAll(
        what: List<String>,
        where: List<String>,
        search: (String, List<String>) -> Int
): SearchResult {
    var cntAll: Int = 0
    var cntSearch: Int = 0

    for (obj in what) {
        cntAll++
        val itemIndex = search(obj, where)
        if (itemIndex > -1) cntSearch++
    }

    return SearchResult(cntSearch, cntAll)
}
