class InsertionSort : Sort {
    override fun <T : Comparable<T>> sort(arr: Array<T>) {
        if (arr.size < 1)
            return

        for (i in 1..arr.lastIndex) {
            for (j in i downTo 1) {
                if (arr[j].compareTo(arr[j - 1]) >= 0)
                    break

                val aux = arr[j]
                arr[j] = arr[j - 1]
                arr[j - 1] = aux
            }

        }
    }
}