class Quicksort: Sort {
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

    private fun <T: Comparable<T>> partition(arr: Array<T>, lo: Int, hi: Int): Int {
        var i = lo + 1
        var j = hi

        // iterates until there are no elements to be exchanged (in wrong locations)
        // exiting when the pointers crossed
        while (true) {
            while (i < hi && arr[i] <= arr[lo]) i++
            while (j > lo && arr[lo] < arr[j]) j--

            if (j <= i)
                break

            exchange(arr, i, j)
        }

        // puts the element used for the comparison in its correct position
        exchange(arr, j, lo)

        return j
    }
}