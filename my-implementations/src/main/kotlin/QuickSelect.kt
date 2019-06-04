class QuickSelect : Partition {

    fun <T: Comparable<T>> select(arr: Array<T>, k: Int): T? {
        KnuthShuffle().shuffle(arr)

        var lo = 0
        var hi = arr.lastIndex
        if (hi < 0 || k > hi)
            return null

        while (true) {
            // part is the sorted position of an element
            val part = partition(arr, lo, hi)

            // goes to the greater than or lesser than the partitioned element portions of the array
            // if it's equal, it has been found
            when {
                part < k -> lo = part + 1
                part > k -> hi = part - 1
                else -> return arr[part]
            }
        }
    }
}