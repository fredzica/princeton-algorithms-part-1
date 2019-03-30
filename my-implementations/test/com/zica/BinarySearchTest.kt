package com.zica

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class BinarySearchTest {

    /** It's already sorted**/
    private val hugeArr = IntArray(10384933) { i -> i }
    private val smallArr = intArrayOf(1, 29, 39, 49, 55, 79)
    private val smallArr2 = intArrayOf(1, 29, 39, 55, 3342, 54252, 6554623)
    private val twoElementsArr = intArrayOf(5, 239)
    private val oneElementArr = intArrayOf(1)
    private val zeroElementsArr = IntArray(0)

    private val bs = BinarySearch()

    @Test
    fun findFirstInHugeArrayTest() {
        val ix = 0
        assertEquals(ix, bs.find(ix, hugeArr))
    }

    @Test
    fun findLastInHugeArrayTest() {
        val ix = hugeArr.size - 1
        assertEquals(ix, bs.find(ix, hugeArr))
    }

    @Test
    fun findLeftInHugeArrayTest() {
        val ix = 2549405
        assertEquals(ix, bs.find(ix, hugeArr))
    }

    @Test
    fun findRightInHugeArrayTest() {
        val ix = 7890394
        assertEquals(ix, bs.find(ix, hugeArr))
    }

    @Test
    fun findUnexistentInHugeArrayTest() {
        val ix = 100000000
        assertNull(bs.find(ix, hugeArr))
    }

    @Test
    fun findInSmallArrayTest() {
        assertEquals(1, bs.find(29, smallArr))
    }

    @Test
    fun findUnexistentInSmallArrayTest() {
        assertNull(bs.find(2934, smallArr))
    }

    @Test
    fun findInSmallArray2Test() {
        assertEquals(5, bs.find(54252, smallArr2))
    }

    @Test
    fun findUnexistentInSmallArray2Test() {
        assertNull(bs.find(2, smallArr2))
    }

    @Test
    fun findInTwoElementsArrayTest() {
        assertEquals(1, bs.find(239, twoElementsArr))
    }

    @Test
    fun findInTwoElementsArrayTest2() {
        assertEquals(0, bs.find(5, twoElementsArr))
    }

    @Test
    fun findUnexistentInTwoElementArrayTest() {
        assertNull(bs.find(13, twoElementsArr))
    }

    @Test
    fun findInOneElementsArrayTest2() {
        assertEquals(0, bs.find(1, oneElementArr))
    }

    @Test
    fun findUnexistentInOneElementArrayTest() {
        assertNull(bs.find(13, oneElementArr))
    }

    @Test
    fun findUnexistentInZeroElementArrayTest() {
        assertNull(bs.find(13, zeroElementsArr))
    }

}