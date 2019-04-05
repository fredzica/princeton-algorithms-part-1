import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource


internal class BinarySearchTest() {

    /** It's already sorted**/
    private val hugeArr = IntArray(10384933) { i -> i }
    private val smallArr = intArrayOf(1, 29, 39, 49, 55, 79)
    private val smallArr2 = intArrayOf(1, 29, 39, 55, 3342, 54252, 6554623)
    private val twoElementsArr = intArrayOf(5, 239)
    private val oneElementArr = intArrayOf(1)
    private val zeroElementsArr = IntArray(0)

    companion object {
        @JvmStatic
        fun implementations() : Array<BinarySearch> {
            return arrayOf(BinarySearchIterative(), BinarySearchRecursive())
        }

        const val implementationsMethodName = "implementations"
    }

    @ParameterizedTest
    @MethodSource(implementationsMethodName)
    fun findFirstInHugeArrayTest(bs: BinarySearch) {
        val ix = 0
        assertEquals(ix, bs.find(ix, hugeArr))
    }

    @ParameterizedTest
    @MethodSource(implementationsMethodName)
    fun findLastInHugeArrayTest(bs: BinarySearch) {
        val ix = hugeArr.size - 1
        assertEquals(ix, bs.find(ix, hugeArr))
    }

    @ParameterizedTest
    @MethodSource(implementationsMethodName)
    fun findLeftInHugeArrayTest(bs: BinarySearch) {
        val ix = 2549405
        assertEquals(ix, bs.find(ix, hugeArr))
    }

    @ParameterizedTest
    @MethodSource(implementationsMethodName)
    fun findRightInHugeArrayTest(bs: BinarySearch) {
        val ix = 7890394
        assertEquals(ix, bs.find(ix, hugeArr))
    }

    @ParameterizedTest
    @MethodSource(implementationsMethodName)
    fun findUnexistentInHugeArrayTest(bs: BinarySearch) {
        val ix = 100000000
        assertNull(bs.find(ix, hugeArr))
    }

    @ParameterizedTest
    @MethodSource(implementationsMethodName)
    fun findInSmallArrayTest(bs: BinarySearch) {
        assertEquals(1, bs.find(29, smallArr))
    }

    @ParameterizedTest
    @MethodSource(implementationsMethodName)
    fun findUnexistentInSmallArrayTest(bs: BinarySearch) {
        assertNull(bs.find(2934, smallArr))
    }

    @ParameterizedTest
    @MethodSource(implementationsMethodName)
    fun findInSmallArray2Test(bs: BinarySearch) {
        assertEquals(5, bs.find(54252, smallArr2))
    }

    @ParameterizedTest
    @MethodSource(implementationsMethodName)
    fun findUnexistentInSmallArray2Test(bs: BinarySearch) {
        assertNull(bs.find(2, smallArr2))
    }

    @ParameterizedTest
    @MethodSource(implementationsMethodName)
    fun findInTwoElementsArrayTest(bs: BinarySearch) {
        assertEquals(1, bs.find(239, twoElementsArr))
    }

    @ParameterizedTest
    @MethodSource(implementationsMethodName)
    fun findInTwoElementsArrayTest2(bs: BinarySearch) {
        assertEquals(0, bs.find(5, twoElementsArr))
    }

    @ParameterizedTest
    @MethodSource(implementationsMethodName)
    fun findUnexistentInTwoElementArrayTest(bs: BinarySearch) {
        assertNull(bs.find(13, twoElementsArr))
    }

    @ParameterizedTest
    @MethodSource(implementationsMethodName)
    fun findInOneElementsArrayTest2(bs: BinarySearch) {
        assertEquals(0, bs.find(1, oneElementArr))
    }

    @ParameterizedTest
    @MethodSource(implementationsMethodName)
    fun findUnexistentInOneElementArrayTest(bs: BinarySearch) {
        assertNull(bs.find(13, oneElementArr))
    }

    @ParameterizedTest
    @MethodSource(implementationsMethodName)
    fun findUnexistentInZeroElementArrayTest(bs: BinarySearch) {
        assertNull(bs.find(13, zeroElementsArr))
    }

}