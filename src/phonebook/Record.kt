package phonebook

class Record(val phoneNumber: String, val name: String) : Comparable<Record>{

    override operator fun compareTo(record: Record): Int {
        return this.name.compareTo(record.name)
    }
}
