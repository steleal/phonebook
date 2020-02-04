package phonebook.sort

fun <T : Comparable<T>> quickSort(list: MutableList<T>) {
    if (list.size < 2) return
    list.quickSort(0, list.lastIndex)
}

private fun <T : Comparable<T>> MutableList<T>.quickSort(low: Int, high: Int) {
    if (low < high) {
        val pivotIndex = this.partition(low, high)
        this.quickSort(low, pivotIndex - 1)
        this.quickSort(pivotIndex + 1, high)
    }
}

private fun <T : Comparable<T>> MutableList<T>.partition(low: Int, high: Int): Int {
    val pivot = this.chosePivot(low, high)

    var i = low
    for (j in low..high - 1) {
        if (this[j] <= pivot) {
            this.swap(i, j)
            i++
        }
    }
    this.swap(i, high)
    return i
}

private fun <T : Comparable<T>> MutableList<T>.chosePivot(low: Int, high: Int): T {
    val mid = low + (high - low) / 2
    if (this[mid] < this[low]) {
        this.swap(low, mid)
    }
    if (this[high] < this[low]) {
        this.swap(low, high)
    }
    if (this[mid] < this[high]) {
        this.swap(mid, high)
    }
    return this[high]
}
