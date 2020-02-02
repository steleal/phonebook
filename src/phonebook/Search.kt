package phonebook

internal fun linearSearch(records: List<Record>, searchName: String): Record? {
    return records.firstOrNull { record ->
        record.name == searchName
    }
}
