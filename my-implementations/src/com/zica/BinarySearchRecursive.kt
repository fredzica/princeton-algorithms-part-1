package com.zica

class BinarySearchRecursive {

    /**
     * Finds an element in a sorted array.
     *
     * Complexity in worst case: O(log(n))
     *
     * @param elem the element to be found
     * @param elems the sorted array with the elements
     * @return the index of found element, if present
     */
    fun find(elem: Int, elems: IntArray) : Int? {
        return find(elem, elems, elems.size - 1, 0)
    }

    private tailrec fun find(elem: Int, elems: IntArray, hi: Int, low : Int) : Int? {
        val i = low + (hi - low) / 2
        var newLow = low
        var newHi = hi

        if (newLow > newHi)
            return null

        when {
            elem < elems[i] -> {
                newHi = i - 1
            }
            elem > elems[i] -> {
                newLow = i + 1
            }
            else -> return i
        }

        return find(elem, elems, newHi, newLow)
    }

/*
    private tailrec fun findRecursion(elem: Int, elems: IntArray, low : Int) : Int? {
        val i = elems.size / 2
        var realI =
        var from = 0
        var to = elems.lastIndex

        when {
            elem < elems[i] -> {
                to = i - 1
                offset =
            }
            elem > elems[i] -> {
                from = i + 1
                offset =
            }
            else -> return i
        }

        if (from > to)
            return null

        return findRecursion(elem, elems.copyOfRange(from, to))
    }*/
}
