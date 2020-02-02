package phonebook

internal fun bubbleSort(records: MutableList<Record>) {

    for (i in records.indices) {
        for (j in 1..(records.lastIndex - i)) {
            val prev = j - 1
            if (records[prev].isMoreThan(records[j])) {
                swap(records, prev, j)
            }
        }
    }
}

internal fun quickSort(records: MutableList<Record>) {
    if (records.size<2) return
    quickSort(records, 0, records.lastIndex)
}

private fun quickSort(records: MutableList<Record>, low: Int, high: Int) {
    if (low < high) {
        val pivotIndex = partition(records, low, high )
        quickSort(records, low, pivotIndex-1)
        quickSort(records,pivotIndex+1,high)
    }

}

private fun partition(records: MutableList<Record>, low: Int, high: Int): Int {
    val pivot = records[high]
    var i = low
    for (j in low..high-1) {
        if (records[j].name <= pivot.name) {
            swap(records, i, j)
            i++
        }
    }
    swap(records, i, high)
    return i
}

private fun swap(records: MutableList<Record>, i: Int, j: Int) {
    if (i==j) return
    val tmp = records[i]
    records[i] = records[j]
    records[j] = tmp
}

private fun Record.isMoreThan(record: Record): Boolean {
    return this.name.compareTo(record.name) > 0
}
