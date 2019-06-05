class QuicksortThreeWay: Sort {
    override fun <T: Comparable<T>> sort(arr: Array<T>) {
        KnuthShuffle().shuffle(arr)
        sort(arr, 0, arr.lastIndex)
    }

    private fun <T: Comparable<T>> sort(arr: Array<T>, lo: Int, hi: Int) {
        if (lo >= hi)
            return

        var lt = lo
        var i = lo
        var gt = hi
        val v = arr[lo]

        while (i <= gt) {
            when {
                arr[i] < v -> exchange(arr, lt++, i++)
                arr[i] > v -> exchange(arr, i, gt--)
                else             -> i++
            }
        }

        // recursive calls for remaining two parts
        sort(arr, lo, lt - 1)
        sort(arr, gt + 1, hi)
    }

}