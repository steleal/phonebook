package phonebook

//based on Article "Java HashMap Implementation in a Nutshell
//https://dzone.com/articles/custom-hashmap-implementation-in-java

class SimpleHashTable<K, V>(val capacity: Int) {
    private val bucketSize = capacity / 20 + 1
    private val buckets = arrayOfNulls<Entry<K, V>>(bucketSize)
    private var size = 0;

    fun put(key: K, value: V) {
        val entry = Entry<K, V>(key, value, null)

        val bucketIndex = getBucketIndex(key)

        var existing = buckets[bucketIndex]
        if (existing == null) {
            buckets[bucketIndex] = entry
            size++
        } else {
            while (existing!!.next != null) {
                if (existing.key == key) {
                    existing.value = value
                    return
                }
                existing = existing.next
            }
            if (existing.key == key) {
                existing.value = value
            } else {
                existing.next = entry
                size++
            }
        }
    }

    fun get(key: K): V? {
        var bucket = buckets[getBucketIndex(key)]

        while (bucket != null) {
            if (bucket.key == key) {
                return bucket.value
            }
            bucket = bucket.next
        }
        return null
    }

    private fun getBucketIndex(key: K) = (key.hashCode() ushr 1) % bucketSize
}

private data class Entry<K, V>(
        val key: K,
        var value: V,
        var next: Entry<K, V>?
)
