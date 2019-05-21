import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.lang.IllegalArgumentException
import kotlin.test.assertTrue

class CollinearPointsTest {

    companion object {
        var cp1: CollinearPoints? = null
        var cp2: CollinearPoints? = null


        @JvmStatic
        private fun implementations() : Array<() -> CollinearPoints> {
            return arrayOf(imp1(), imp2())
        }

        // functions that return current CollinearPoints objects
        private fun imp1() : () -> CollinearPoints {
            return  {cp1!!}
        }

        private fun imp2() : () -> CollinearPoints {
            return  {cp2!!}
        }
        const val implementationsMethodName = "implementations"
    }

    @ParameterizedTest
    @MethodSource(implementationsMethodName)
    fun testNullInput(cp: () -> CollinearPoints) {
        assertThrows<IllegalArgumentException> {changeValues(null)}
    }

    @ParameterizedTest
    @MethodSource(implementationsMethodName)
    fun testNullPoint(cp: () -> CollinearPoints) {

        assertThrows<IllegalArgumentException> {
                changeValues(arrayOf(
                Point(16000, 16000),
                null
            ))
        }
    }

    @ParameterizedTest
    @MethodSource(implementationsMethodName)
    fun testRepeatedPoint(cp: () -> CollinearPoints) {

        assertThrows<IllegalArgumentException> {
                changeValues(arrayOf(
                Point(15000, 16000),
                Point(1000, 16000),
                Point(15000, 16000)
            ))
        }
    }

    @ParameterizedTest
    @MethodSource(implementationsMethodName)
    fun testInput1(cp: () -> CollinearPoints) {

        changeValues(arrayOf(
            Point(16000, 16000)
        ))

        assertEquals(0, cp().numberOfSegments())
    }

    @ParameterizedTest
    @MethodSource(implementationsMethodName)
    fun testInput2(cp: () -> CollinearPoints) {

        changeValues(arrayOf(
                Point(16000, 10000),
                Point(10000, 10000)
        ))

        assertEquals(0, cp().numberOfSegments())
    }

    @ParameterizedTest
    @MethodSource(implementationsMethodName)
    fun testInput4(cp: () -> CollinearPoints) {

        changeValues(arrayOf(
            Point(19000, 10000),
            Point(20000, 10000),
            Point(21000, 10000),
            Point(10000, 10000)
        ))

        val segments = cp().segments()
        assertEquals("(10000, 10000) -> (21000, 10000)", segments[0].toString())
        assertEquals(1, cp().numberOfSegments())
    }

    @ParameterizedTest
    @MethodSource(implementationsMethodName)
    fun testInput8(cp: () -> CollinearPoints) {

        changeValues(arrayOf(
                Point(10000, 0),
                Point(0, 10000),
                Point(3000, 7000),
                Point(7000, 3000),
                Point(20000, 21000),
                Point(3000, 4000),
                Point(14000, 15000),
                Point(6000, 7000)
        ))

        val segments = cp().segments()
        assertEquals("(10000, 0) -> (0, 10000)", segments[0].toString())
        assertEquals("(3000, 4000) -> (20000, 21000)", segments[1].toString())
        assertEquals(2, cp().numberOfSegments())
    }

    @ParameterizedTest
    @MethodSource(implementationsMethodName)
    fun testInputMatrix(cp: () -> CollinearPoints) {

        changeValues(arrayOf(
                Point(0, 0),
                Point(1, 1),
                Point(2, 2),
                Point(3, 3),
                Point(0, 1),
                Point(0, 2),
                Point(0, 3),
                Point(1, 0),
                Point(1, 2),
                Point(1, 3),
                Point(2, 0),
                Point(2, 1),
                Point(2, 3),
                Point(3, 0),
                Point(3, 1),
                Point(3, 2)
        ))

        assertEquals(10, cp().numberOfSegments())


        val segments = cp().segments().map(LineSegment::toString)

        assertTrue(segments.contains("(0, 0) -> (0, 3)"))
        assertTrue(segments.contains("(0, 0) -> (3, 3)"))
        assertTrue(segments.contains("(3, 0) -> (0, 3)"))
        assertTrue(segments.contains("(1, 0) -> (1, 3)"))
    }

    @Test
    fun testInputMatrixFiveCollinear() {

        val cp = FastCollinearPoints(arrayOf(
                Point(0, 0),
                Point(1, 1),
                Point(2, 2),
                Point(3, 3),
                Point(4, 4),
                Point(0, 1),
                Point(0, 2),
                Point(0, 3),
                Point(0, 4),
                Point(1, 0),
                Point(1, 2),
                Point(1, 3),
                Point(1, 4),
                Point(2, 0),
                Point(2, 1),
                Point(2, 3),
                Point(2, 4),
                Point(3, 0),
                Point(3, 1),
                Point(3, 2),
                Point(3, 4),
                Point(4, 0),
                Point(4, 1),
                Point(4, 2),
                Point(4, 3)
        ))

        assertEquals(16, cp.numberOfSegments())

        val segments = cp.segments().map(LineSegment::toString)
        assertTrue(segments.contains("(0, 0) -> (0, 4)"))
        assertTrue(segments.contains("(0, 0) -> (4, 4)"))
        assertTrue(segments.contains("(4, 0) -> (0, 4)"))
        assertTrue(segments.contains("(1, 0) -> (1, 4)"))
    }

    // the best way not to replicate the test code for the multiple implementations was to create this method
    private fun changeValues(points: Array<Point?>?) {
        cp1 = BruteCollinearPoints(points)
        cp2 = FastCollinearPoints(points)
    }
}
