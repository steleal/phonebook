package phonebook.search

import phonebook.Record

internal fun linearSearch(records: List<Record>, searchName: String): Record? {
    return records.firstOrNull { record ->
        record.name == searchName
    }
}