package phonebook

internal fun linearSearch(searchLine: String, lines: List<String>): Int {

    for (i in lines.indices) {
        if (lines[i].contains(searchLine)) return i
    }
    return -1
}
