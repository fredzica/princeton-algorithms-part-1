class SelectionSort : Sort {
    override fun <T : Comparable<T>> sort(arr: Array<T>) {
        if (arr.size < 1)
            return

        for (i in arr.indices) {

            var smaller = i
            for (j in i + 1..arr.lastIndex) {
                if (arr[smaller].compareTo(arr[j]) > 0) {
                    smaller = j
                }
            }
            val aux = arr[i]
            arr[i] = arr[smaller]
            arr[smaller] = aux
        }
    }
}