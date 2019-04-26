class MergesortRecursive : Sort {
    override fun <T : Comparable<T>> sort(arr: Array<T>) {
        val aux = arr.copyOf()
        sort(arr, aux, 0, arr.lastIndex)
    }

    private fun <T: Comparable<T>> sort(arr: Array<T>, aux: Array<T>, lo: Int, hi: Int) {
        if (lo >= hi)
            return

        val mid = lo + (hi - lo)/2

        sort(arr, aux, lo, mid)
        sort(arr, aux, mid + 1, hi)
        merge(arr, aux, lo, mid, hi)
    }

    private fun <T: Comparable<T>> merge(arr: Array<T>, aux: Array<T>, lo: Int, mid: Int, hi: Int) {
        var firstPartIx = lo
        var secondPartIx = mid + 1

        // check if its already sorted
        if (arr[mid] < arr[secondPartIx])
            return

        for (i in lo..hi) {
            when {
                firstPartIx > mid -> arr[i] = aux[secondPartIx++]
                secondPartIx > hi -> arr[i] = aux[firstPartIx++]
                aux[firstPartIx] > aux[secondPartIx] -> arr[i] = aux[secondPartIx++]
                else -> arr[i] = aux[firstPartIx++]
            }
        }

        for (i in lo..hi) aux[i] = arr[i]
    }
}