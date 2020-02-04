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

private inline fun lineToRecord(line: String): Record? {
    if (line.isBlank() || !line.contains(" ")) return null

    // With substringFirst and without inline
    // I was missing about two hundred milliseconds for passing the test
    val numberEnd = line.indexOfFirst { it == ' ' }
    val nameStart = numberEnd + 1

    val number = line.substring(0, numberEnd)
    val name = line.substring(nameStart)

    return Record(number, name)
}

fun importLinesFromFile(name: String) = File(name).readLines()
