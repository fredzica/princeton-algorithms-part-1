
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class KnuthShuffleTest {

    private val knuthShuffle = KnuthShuffle()

    @Test
    fun shuffleTest() {
        val arr = Array(1000000) {i ->  i}
        val copy = arr.copyOf()

        knuthShuffle.shuffle(arr)
        assertEquals(copy.sum(), arr.sum())

        var differentCount = 0
        for (i in arr.indices) {
            if (arr[i] != copy[i])
                differentCount++
        }

        // the probability of this failing is so small I won't even consider it
        assertTrue(differentCount > 10000)
    }

    @Test
    fun shuffleOneElementTest() {
        val elem = 3
        val arr = arrayOf(elem)

        knuthShuffle.shuffle(arr)
        assertEquals(elem, arr[0])
    }

    @Test
    fun shuffleEmptyTest() {
        val arr = emptyArray<Int>()

        knuthShuffle.shuffle(arr)
        assertEquals(0, arr.size)
    }
}