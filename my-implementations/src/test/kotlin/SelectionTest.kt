
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class SelectionTest {

    private val selection = QuickSelect()

    @Test
    fun emptyArrayTest() {
        val arr = emptyArray<Int>()
        val elem = selection.select(arr, 3)

        assertEquals(null, elem)
    }

    @Test
    fun outOfBoundsArrayTest() {
        val arr = arrayOf(25)
        val elem = selection.select(arr, 3)

        assertEquals(null, elem)
    }

    @Test
    fun smallArrayTest() {
        val arr = arrayOf(0, 1, 2, 35, 43, 34829349, 5)
        val elem = selection.select(arr, 3)

        assertEquals(5, elem)
    }

    @Test
    fun oneElementArrayTest() {
        val arr = arrayOf(25)
        val elem = selection.select(arr, 0)

        assertEquals(25, elem)
    }

    @Test
    fun hugeArrayTest() {
        val arr = Array(1000000) {i -> i}
        val elem = selection.select(arr, 837495)

        assertEquals(837495, elem)
    }

    @Test
    fun hugeArray2Test() {
        val arr = Array(1000000) {i -> i}

        arr[348392] = -2
        val elem = selection.select(arr, 0)

        assertEquals(-2, elem)
    }
}