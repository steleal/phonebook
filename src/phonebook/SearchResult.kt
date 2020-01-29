package phonebook

class SearchResult(var found: Int = 0, var all: Int = 0) {
    override fun toString(): String {
        return "Found $found / $all entries."
    }
}