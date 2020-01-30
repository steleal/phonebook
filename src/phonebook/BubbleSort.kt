package phonebook

internal fun bubbleSortWithStop(lines: MutableList<String>, maxTimeInMs: Long): Boolean {

    val startTime = System.currentTimeMillis()
    for (i in lines.indices) {
        for (j in 1..(lines.lastIndex - i)) {
            val prev = j - 1
            if (lines[prev].isMoreThan(lines[j])) {
                change(lines, prev, j)
            }
        }
        val time = System.currentTimeMillis() - startTime
        if (time > maxTimeInMs) return true
    }

    return false
}

private fun change(lines: MutableList<String>, i: Int, j: Int) {
    val tmp = lines[i]
    lines[i] = lines[j]
    lines[j] = tmp
}

private fun String.isMoreThan(line: String): Boolean {
    val thisNameStart = this.indexOfFirst { it == ' ' } + 1
    val secondNameStart = line.indexOfFirst { it == ' ' } + 1

    val thisName = this.substring(thisNameStart)
    val secondName = line.substring(secondNameStart)

    return thisName.compareTo(secondName) > 0
}

