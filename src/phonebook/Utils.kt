package phonebook

internal fun Long.millisecToString(): String {
    val ms = this % 1000
    val allsec = this / 1000
    val sec = allsec % 60
    val min = allsec / 60
    return "$min min. $sec sec. $ms ms."
}