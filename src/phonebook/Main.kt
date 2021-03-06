package phonebook

import phonebook.search.binarySearch
import phonebook.search.jumpSearch
import phonebook.search.linearSearch
import phonebook.sort.bubbleSortWithStopTime
import phonebook.sort.quickSort
import kotlin.system.measureTimeMillis

fun main() {
    val book = importRecordsFromFile("C:\\Users\\user\\Downloads\\directory.txt")
    val searchLines = importLinesFromFile("C:\\Users\\user\\Downloads\\find.txt")

    val linearSearchTime = testSearchWithSort(
            searchLines,
            book,
            ::linearSearch,
            null,
            "Start searching (linear search)..."
    )

    specialTestCaseWithBubbleSortAndJumpSearch(
            searchLines,
            book,
            "Start searching (bubble sort + jump search)...",
            linearSearchTime * 10
    )

    testSearchWithSort(
            searchLines,
            book,
            ::binarySearch,
            ::quickSort,
            "Start searching (quick sort + binary search)..."
    )

    testSearchWithHashTable(searchLines, book)
}

private fun testSearchWithHashTable(names: List<String>, book: PhoneBook) {
    println("Start searching (hash table)...")
    var table: SimpleHashTable<String, String>? = null

    val createTableTime = measureTimeMillis {
        table = SimpleHashTable(book.records.size)
        book.records.forEach {
            table?.put(it.name, it.phoneNumber)
        }
    }

    val (found, all, searchTime) = searchAllWithHashTable(names, table!!)
    val fullTime = createTableTime + searchTime

    println("Found $found / $all entries. Time taken: ${fullTime.millisecToString()}")
    println("Creating time: ${createTableTime.millisecToString()}")
    println("Searching time: ${searchTime.millisecToString()}")
    println()
}

private fun testSearchWithSort(
        names: List<String>,
        phoneBook: PhoneBook,
        search: (List<Record>, String) -> Record?,
        sort: ((MutableList<Record>) -> Unit)?,
        startMessage: String
): Long {
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

    return fullTime
}

private fun searchAll(names: List<String>, phoneBook: PhoneBook): Triple<Int, Int, Long> {
    var cntAll = 0
    var cntSearch = 0

    val searchTimeInMs = measureTimeMillis {
        for (obj in names) {
            cntAll++
            val record = phoneBook.search(obj)
            record?.let { cntSearch++ }
        }
    }

    return Triple(cntSearch, cntAll, searchTimeInMs)
}

private fun searchAllWithHashTable(names: List<String>, table: SimpleHashTable<String, String>): Triple<Int, Int, Long> {
    var cntAll = 0
    var cntSearch = 0

    val searchTimeInMs = measureTimeMillis {
        for (obj in names) {
            cntAll++
            val record = table.get(obj)
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

private fun specialTestCaseWithBubbleSortAndJumpSearch(
        names: List<String>,
        phoneBook: PhoneBook,
        startMessage: String,
        maxTimeInMs: Long
): Long {
    println(startMessage)

    var stopped = false
    val bubbleSortTime = measureTimeMillis {
        stopped = bubbleSortWithStopTime(phoneBook.records, maxTimeInMs)
    }

    phoneBook.searchMethod = if (stopped) {
        ::linearSearch
    } else {
        ::jumpSearch
    }

    val (found, all, searchTime) = searchAll(names, phoneBook)
    val fullTime = bubbleSortTime + searchTime

    println("Found $found / $all entries. Time taken: ${fullTime.millisecToString()}")
    print("Sorting time: ${bubbleSortTime.millisecToString()}")
    if (stopped) print(" - STOPPED, moved to linear search")
    println()
    println("Searching time: ${searchTime.millisecToString()}")

    println()

    return fullTime
}
