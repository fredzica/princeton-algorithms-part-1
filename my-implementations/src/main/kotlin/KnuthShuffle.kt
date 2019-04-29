import kotlin.random.Random
import kotlin.random.nextInt

class KnuthShuffle {
    fun <T> shuffle(arr: Array<T>) {
        for (i in 1..arr.lastIndex) {
            val toShuffle = Random.nextInt(0..i)
            val aux = arr[i]
            arr[i] = arr[toShuffle]
            arr[toShuffle] = aux
        }
    }
}