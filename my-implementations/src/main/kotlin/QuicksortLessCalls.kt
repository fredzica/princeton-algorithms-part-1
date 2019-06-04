class QuicksortLessCalls: Sort, Partition {
    override fun <T: Comparable<T>> sort(arr: Array<T>) {
        KnuthShuffle().shuffle(arr)
        sort(arr, 0, arr.lastIndex)
    }

    private fun <T: Comparable<T>> sort(arr: Array<T>, lo: Int, hi: Int) {
        var low = lo
        // while the bounds are respected, keep partitioning and
        while (low < hi) {
            // does partitioning. the partitioned element is put in its final place
            val part = partition(arr, low, hi)

            sort(arr, low, part - 1)

            low = part + 1
        }
    }
}