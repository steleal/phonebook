package phonebook

internal fun jumpSearch(records: List<Record>, searchName: String): Record? {
    if (records.isEmpty()) return null

    val blockSize = Math.sqrt(records.size.toDouble()).toInt()
    var index = 0

    while (index < records.size) {
        if (records[index].name == searchName) break
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
    return if (records[index].name!=searchName) null else records[index]
}