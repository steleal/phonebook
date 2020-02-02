package phonebook

internal fun bubbleSort(records: MutableList<Record>) {

    for (i in records.indices) {
        for (j in 1..(records.lastIndex - i)) {
            val prev = j - 1
            if (records[prev].isMoreThan(records[j])) {
                change(records, prev, j)
            }
        }
    }
}

private fun change(records: MutableList<Record>, i: Int, j: Int) {
    val tmp = records[i]
    records[i] = records[j]
    records[j] = tmp
}

private fun Record.isMoreThan(record: Record): Boolean {
    return this.name.compareTo(record.name) > 0
}
