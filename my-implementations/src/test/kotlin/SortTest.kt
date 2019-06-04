
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

internal class SortTest {

    companion object {
        @JvmStatic
        fun implementations() : Array<Sort> {
            return arrayOf(SelectionSort(), InsertionSort(), ShellSort(),
                MergesortRecursive(), MergesortBottomUp(), Quicksort(), QuicksortLessCalls())
        }

        const val implementationsMethodName = "implementations"
    }

    @ParameterizedTest
    @MethodSource(implementationsMethodName)
    fun testSortInt(s: Sort) {
        val arr = arrayOf(7, 9, 1, 3, 4, 15)
        s.sort(arr)

        assertArrayEquals(arrayOf(1, 3, 4, 7, 9, 15), arr)
    }

    @ParameterizedTest
    @MethodSource(implementationsMethodName)
    fun testSortIntTwoElements(s: Sort) {
        val arr = arrayOf(7, 2)
        s.sort(arr)

        assertArrayEquals(arrayOf(2, 7), arr)
    }

    @ParameterizedTest
    @MethodSource(implementationsMethodName)
    fun testSortIntThreeElements(s: Sort) {
        val arr = arrayOf(7, 2, 5)
        s.sort(arr)

        assertArrayEquals(arrayOf(2, 5, 7), arr)
    }

    @ParameterizedTest
    @MethodSource(implementationsMethodName)
    fun testSortDuplicateElements(s: Sort) {
        val arr = arrayOf(3, 2, 9, 4, 1, 5, 8, 1, 1, 5, 5)
        s.sort(arr)

        assertArrayEquals(arrayOf(1, 1, 1, 2, 3, 4, 5, 5, 5, 8, 9), arr)
    }

    @ParameterizedTest
    @MethodSource(implementationsMethodName)
    fun testSortBigArrayDuplicateElements(s: Sort) {
        val size = 5000
        val arr = Array(size) {1}
        s.sort(arr)

        assertEquals(arr[0], 1)
        assertEquals(arr[4850], 1)
        assertEquals(arr[4999], 1)
        assertEquals(size, arr.size)
    }

    @ParameterizedTest
    @MethodSource(implementationsMethodName)
    fun testSortBigArray(s: Sort) {
        val ixToBeTested1 = 834
        val ixToBeTested2 = 49857
        val arr = Array(50000) {i -> i}
        arr[ixToBeTested1] = ixToBeTested2
        arr[ixToBeTested2] = ixToBeTested1

        val shuffler = KnuthShuffle()
        shuffler.shuffle(arr)
        s.sort(arr)
        assertEquals(arr[ixToBeTested1], ixToBeTested1)
        assertEquals(arr[ixToBeTested2], ixToBeTested2)
    }

    @ParameterizedTest
    @MethodSource(implementationsMethodName)
    fun testSortChar(s: Sort) {
        val arr = arrayOf('7', 'b', 'y', 'o', '1', 'a', 'e', 'h')
        s.sort(arr)

        assertArrayEquals(arrayOf('1', '7', 'a', 'b', 'e', 'h', 'o', 'y'), arr)
    }

    @ParameterizedTest
    @MethodSource(implementationsMethodName)
    fun testSortAlreadySorted(s: Sort) {
        val size = 50000
        val arr = Array(size) {i -> i}
        s.sort(arr)

        assertArrayEquals(Array(size) {i -> i}, arr)
    }

    @ParameterizedTest
    @MethodSource(implementationsMethodName)
    fun testSortOneElement(s: Sort) {
        val arr = arrayOf(7)
        s.sort(arr)

        assertArrayEquals(arrayOf(7), arr)
    }

    @ParameterizedTest
    @MethodSource(implementationsMethodName)
    fun testSortEmpty(s: Sort) {
        val arr = emptyArray<String>()
        s.sort(arr)

        assertArrayEquals(emptyArray(), arr)
    }
}