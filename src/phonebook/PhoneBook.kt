package phonebook

class PhoneBook(val records: MutableList<Record>) {
    var searchMethod: ((List<Record>, String) -> Record?)? = null

    fun search(name: String): Record? = searchMethod?.invoke(records, name)
}
