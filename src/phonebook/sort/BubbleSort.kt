package phonebook.sort

internal fun <T : Comparable<T>> bubbleSortWithStopTime(list: MutableList<T>, maxExecuteTimeInMs: Long): Boolean {
    if (list.size < 2) return false

    val startTime = System.currentTimeMillis()

    for (i in list.indices) {
        val high = list.lastIndex - i
        var maxI = 0
        for (j in 0..high) {
            if (list[j] > list[maxI]) {
                maxI = j
            }
        }
        list.swap(maxI, high)

        val executeTime = System.currentTimeMillis() - startTime
        if (executeTime > maxExecuteTimeInMs) return true
    }
    return false
}
