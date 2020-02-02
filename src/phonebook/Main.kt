package phonebook

import kotlin.system.measureTimeMillis

fun main() {
    val unsortRecords = importRecordsFromFile("C:\\Users\\user\\Downloads\\directory.txt")
    val searchLines = importLinesFromFile("C:\\Users\\user\\Downloads\\find.txt")

    testSearchWithSort(
            searchLines,
            PhoneBook(unsortRecords.toMutableList()),
            ::linearSearch,
            null,
            "Start searching (linear search)..."
    )

    testSearchWithSort(
            searchLines,
            PhoneBook(unsortRecords.toMutableList()),
            ::jumpSearch,
            ::bubbleSort,
            "Start searching (bubble sort + jump search)..."
    )

    testSearchWithSort(
            searchLines,
            PhoneBook(unsortRecords.toMutableList()),
            ::binarySearch,
            ::quickSort,
            "Start searching (quick sort + binary search)..."
    )
}

private fun testSearchWithSort(
        names: List<String>,
        phoneBook: PhoneBook,
        search: (List<Record>, String) -> Record?,
        sort: ((MutableList<Record>) -> Unit)?,
        startMessage: String
) {
    println(startMessage)

    val sortTime = if (sort != null) {
        measureTimeMillis { sort(phoneBook.records) }
    } else {
        0L
    }

    phoneBook.searchMethod = search

    val (found, all, searchTime) = searchAll(names, phoneBook)
    val fullTime = sortTime + searchTime

    println("Found $found / $all entries. Time taken: ${fullTime.millisecToString()}")
    if (sort != null) {
        println("Sorting time: ${sortTime.millisecToString()}")
        println("Searching time: ${searchTime.millisecToString()}")
    }
    println()
}

private fun searchAll(names: List<String>, phoneBook: PhoneBook): Triple<Int, Int, Long> {
    var cntAll: Int = 0
    var cntSearch: Int = 0

    val searchTimeInMs = measureTimeMillis {
        for (obj in names) {
            cntAll++
            val record = phoneBook.search(obj)
            record?.let { cntSearch++ }
        }
    }

    return Triple(cntSearch, cntAll, searchTimeInMs)
}

private fun Long.millisecToString(): String {
    val ms = this % 1000
    val allsec = this / 1000
    val sec = allsec % 60
    val min = allsec / 60
    return "$min min. $sec sec. $ms ms."
}
