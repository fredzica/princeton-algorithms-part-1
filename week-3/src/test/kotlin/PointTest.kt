import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class PointTest {

    @Test
    fun testCompareToSmallerX() {
        val p1 = Point(1, 2)
        val p2 = Point(2, 2)

        assertEquals(-1, p1.compareTo(p2))
    }

    @Test
    fun testCompareToSmallerY() {
        val p1 = Point(1, 1)
        val p2 = Point(20, 2)

        assertEquals(-1, p1.compareTo(p2))
    }

    @Test
    fun testCompareToBiggerY() {
        val p1 = Point(1, 5)
        val p2 = Point(20, 4)

        assertEquals(1, p1.compareTo(p2))
    }

    @Test
    fun testCompareToBiggerX() {
        val p1 = Point(3, 4)
        val p2 = Point(2, 4)

        assertEquals(1, p1.compareTo(p2))
    }

    @Test
    fun testCompareToEqual() {
        val p1 = Point(2, 4)
        val p2 = Point(2, 4)

        assertEquals(0, p1.compareTo(p2))
    }

    @Test
    fun testSlopeToDegenerate() {
        val p1 = Point(3, 3)
        val p2 = Point(3, 3)

        assertEquals(Double.NEGATIVE_INFINITY, p1.slopeTo(p2))
    }

    @Test
    fun testSlopeToVertical() {
        val p1 = Point(3, 3)
        val p2 = Point(3, 300)

        assertEquals(Double.POSITIVE_INFINITY, p1.slopeTo(p2))
    }

    @Test
    fun testSlopeToHorizontal() {
        val p1 = Point(5, 300)
        val p2 = Point(34, 300)

        assertEquals(0.0, p1.slopeTo(p2))
    }

    @Test
    fun testSlopeOrderSmallerHorizontal() {
        val p1 = Point(0, -1)
        val p2 = Point(2, 2)
        val p3 = Point(1, -1)

        assertEquals(-1, p3.slopeOrder().compare(p1, p2))
    }

    @Test
    fun testSlopeOrderSmallerVertical() {
        val p1 = Point(2, -200)
        val p2 = Point(1, 2)
        val p3 = Point(1, -1)

        assertEquals(-1, p3.slopeOrder().compare(p1, p2))
    }

    @Test
    fun testSlopeOrderSmallerDegenerate() {
        val p1 = Point(2, -200)
        val p2 = Point(1, 2)
        val p3 = Point(2, -200)

        assertEquals(-1, p3.slopeOrder().compare(p1, p2))
    }

    @Test
    fun testSlopeOrderSmaller() {
        val p1 = Point(0, -1)
        val p2 = Point(2, 2)
        val p3 = Point(1, -1)

        assertEquals(-1, p3.slopeOrder().compare(p1, p2))
    }

    @Test
    fun testSlopeOrderBiggerHorizontal() {
        val p1 = Point(0, -1)
        val p2 = Point(2, -2)
        val p3 = Point(1, 3)

        assertEquals(1, p3.slopeOrder().compare(p1, p2))
    }

    @Test
    fun testSlopeOrderBiggerVertical() {
        val p1 = Point(1, -1)
        val p2 = Point(2675, 23867)
        val p3 = Point(1, 45)

        assertEquals(1, p3.slopeOrder().compare(p1, p2))
    }

    @Test
    fun testSlopeOrderBiggerDegenerate() {
        val p1 = Point(0, -1000)
        val p2 = Point(1, -1)
        val p3 = Point(1, -1)

        assertEquals(1, p3.slopeOrder().compare(p1, p2))
    }

    @Test
    fun testSlopeOrderBigger() {
        val p1 = Point(-30, 11)
        val p2 = Point(1, 2)
        val p3 = Point(7, -1)

        assertEquals(1, p3.slopeOrder().compare(p1, p2))
    }

    @Test
    fun testSlopeOrderEquals() {
        val p1 = Point(2, 40)
        val p2 = Point(2, 40)
        val p3 = Point(10, -3)

        assertEquals(0, p3.slopeOrder().compare(p1, p2))
    }
}