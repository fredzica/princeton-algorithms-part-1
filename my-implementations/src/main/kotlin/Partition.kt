interface Partition {
    fun <T: Comparable<T>> partition(arr: Array<T>, lo: Int, hi: Int): Int {
        var i = lo + 1
        var j = hi

        // iterates until there are no elements to be exchanged (in wrong locations)
        // exiting when the pointers crossed
        while (true) {
            while (i < hi && arr[i] < arr[lo]) i++
            while (j > lo && arr[lo] <= arr[j]) j--

            if (j <= i)
                break

            exchange(arr, i, j)
        }

        // puts the element used for the comparison in its correct position
        exchange(arr, j, lo)

        return j
    }

    private fun <T: Comparable<T>> exchange(arr: Array<T>, i: Int, j: Int) {
        val aux = arr[i]
        arr[i] = arr[j]
        arr[j] = aux
    }
}