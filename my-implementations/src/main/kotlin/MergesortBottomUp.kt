class MergesortBottomUp: Sort {
    override fun <T: Comparable<T>> sort(arr: Array<T>) {
        val aux = arr.copyOf()
        var sz = 1
        while (sz < arr.size) {
            for (i in 0..arr.lastIndex - sz step sz * 2)
                merge(arr, aux, i, i + sz - 1, Math.min(i + sz * 2 - 1, arr.size - 1))

            sz *= 2
        }
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