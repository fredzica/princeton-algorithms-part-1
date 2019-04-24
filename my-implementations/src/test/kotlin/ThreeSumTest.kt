import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

internal class ThreeSumTest {

    companion object {
        @JvmStatic
        fun implementations() : Array<ThreeSum> {
            return arrayOf(ThreeSumBruteForce(), ThreeSumBinarySearch())
        }

        const val implementationsMethodName = "implementations"
    }

    @ParameterizedTest
    @MethodSource(implementationsMethodName)
    fun threeSumTest1(ts: ThreeSum) {
        val triples = ts.threeSum(intArrayOf(1, 2, 3 , -1, -5))

        assertEquals(listOf(Triple(-5, 2, 3)), triples)
    }

    @ParameterizedTest
    @MethodSource(implementationsMethodName)
    fun threeSumTest2(ts: ThreeSum) {
        val triples = ts.threeSum(intArrayOf(0, 2, 3, 5, -2, -1, -5)).toTypedArray()

        val expected = arrayOf(
            Triple(-5, 0, 5),
            Triple(-5, 2, 3),
            Triple(-2, -1, 3),
            Triple(-2, 0, 2))

        assertArrayEquals(expected, triples)
    }

    @ParameterizedTest
    @MethodSource(implementationsMethodName)
    fun threeSumTestNoResult(ts: ThreeSum) {
        val triples = ts.threeSum(intArrayOf(1, 2, 2 , -1, -5))

        assertEquals(emptyList<Triple<Int, Int, Int>>(), triples)
    }

    @ParameterizedTest
    @MethodSource(implementationsMethodName)
    fun threeSumTestEmpty(ts: ThreeSum) {
        val triples = ts.threeSum(intArrayOf())

        assertEquals(emptyList<Triple<Int, Int, Int>>(), triples)
    }
}