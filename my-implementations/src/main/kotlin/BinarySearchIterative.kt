class BinarySearchIterative : BinarySearch {

    /**
     * Finds an element in a sorted array.
     *
     * Complexity in worst case: O(log(n))
     *
     * @param elem the element to be found
     * @param elems the sorted array with the elements
     * @return the index of found element, if present
     */
    override fun find(elem: Int, elems: IntArray) : Int? {
        var hi = elems.lastIndex
        var low = 0

        while (low <= hi) {
            val i = low + (hi - low) / 2
            when {
                elem < elems[i] -> hi = i - 1
                elem > elems[i] -> low = i + 1
                else -> return i
            }
        }
        return null
    }
}
