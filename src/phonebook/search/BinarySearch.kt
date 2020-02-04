package phonebook.search

import phonebook.Record

internal fun binarySearch(records: List<Record>, searchName: String): Record? {
    if (records.isEmpty()) return null
    if (records.first().name > searchName) return null
    if (records.last().name < searchName) return null

    var first = 0
    var last = records.lastIndex

    while (first < last) {
        val middle = first + (last - first) / 2

        if (searchName <= records[middle].name) {
            last = middle
        } else {
            first = middle + 1
        }
    }

    if (records[last].name == searchName) {
        return records[last]
    } else {
        return null
    }
}
