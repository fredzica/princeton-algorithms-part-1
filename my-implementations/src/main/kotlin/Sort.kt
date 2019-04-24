interface Sort {
    fun <T : Comparable<T>> sort(arr: Array<T>)
}