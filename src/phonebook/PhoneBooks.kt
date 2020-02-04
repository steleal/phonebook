package phonebook

import java.io.File

fun importRecordsFromFile(name: String): PhoneBook {
    val file = File(name)
    val records = mutableListOf<Record>()

    file.forEachLine { line ->
        val record = lineToRecord(line)
        record?.let { records.add(record) }
    }

    return PhoneBook(records)
}

private fun lineToRecord(line: String): Record? {
    if (line.isBlank() || !line.contains(" ")) return null

    val number = line.substringBefore(' ')
    val name = line.substringAfter(' ')

    return Record(number, name)
}

fun importLinesFromFile(name: String) = File(name).readLines()
