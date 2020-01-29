package phonebook

internal fun jumpSearch(searchLine: String, lines: List<String>): Int {
    if (lines.isEmpty()) return -1

    val blockSize = Math.sqrt(lines.size.toDouble()).toInt()
    var index = 0

    while (index < lines.size) {
        if (lines[index] == searchLine) break
        if (lines[index] < searchLine) {
            index += blockSize
        }
    }

    if (index > lines.lastIndex) index = lines.lastIndex
    if (lines[index] < searchLine) return -1

    while (index >= 0 && lines[index] >= searchLine) {
        index--
    }
    index++
    return if (lines[index]!=searchLine) -1 else index
}