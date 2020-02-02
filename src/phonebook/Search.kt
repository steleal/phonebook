package phonebook

internal fun linearSearch(records: List<Record>, searchName: String): Record? {
    return records.firstOrNull { record ->
        record.name == searchName
    }
}

internal fun jumpSearch(records: List<Record>, searchName: String): Record? {
    if (records.isEmpty()) return null

    val blockSize = Math.sqrt(records.size.toDouble()).toInt()
    var index = 0

    while (index < records.size) {
        if (records[index].name >= searchName) break
        if (records[index].name < searchName) {
            index += blockSize
        }
    }

    if (index > records.lastIndex) index = records.lastIndex
    if (records[index].name < searchName) return null

    while (index >= 0 && records[index].name >= searchName) {
        index--
    }
    index++
    return if (records[index].name != searchName) null else records[index]
}

internal fun binarySearch(records: List<Record>, searchName: String): Record? {
    if (records.isEmpty()) return null
    if (records.first().name>searchName) return null
    if (records.last().name<searchName) return null

    var first = 0
    var last = records.lastIndex

    while ( first<last) {
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
