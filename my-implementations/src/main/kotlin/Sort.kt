interface Sort {
    fun <T : Comparable<T>> sort(arr: Array<T>)

    fun <T: Comparable<T>> exchange(arr: Array<T>, i: Int, j: Int) {
        val aux = arr[i]
        arr[i] = arr[j]
        arr[j] = aux
    }
}