class Quicksort: Sort, Partition {
    override fun <T: Comparable<T>> sort(arr: Array<T>) {
        KnuthShuffle().shuffle(arr)
        sort(arr, 0, arr.lastIndex)
    }

    private fun <T: Comparable<T>> sort(arr: Array<T>, lo: Int, hi: Int) {
        if (lo > hi)
            return

        // does partitioning. the partitioned element is put in its final place
        val part = partition(arr, lo, hi)

        // recursive calls for remaining two parts
        sort(arr, lo, part - 1)
        sort(arr, part + 1, hi)
    }

}