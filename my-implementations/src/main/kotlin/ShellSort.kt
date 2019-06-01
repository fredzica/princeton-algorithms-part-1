class ShellSort : Sort {
    override fun <T : Comparable<T>> sort(arr: Array<T>) {
        if (arr.size <= 1)
            return

        var h = 1
        while (h < arr.size/3) h = 3*h + 1

        while (h >= 1) {
            for (i in h..arr.lastIndex step h) {
                // h-sort: the same reasoning for insertion sort,
                // but does it in h intervals
                for (j in i downTo h step h) {
                    // if the condition below is true, it is already sorted
                    if (arr[j] >= arr[j - h])
                        break

                    exchange(arr, j, j - h)
                }
            }
            h /= 3
        }
    }
}