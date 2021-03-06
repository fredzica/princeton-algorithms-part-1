import edu.princeton.cs.algs4.StdRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class RandomizedQueueTest {

    private RandomizedQueue<Integer> rq;

    @BeforeEach
    void setup() {
        rq = new RandomizedQueue<>();
    }

    @Test
    void testIsEmptyTrue() {
        assertTrue(rq.isEmpty());
    }

    @Test
    void testIsEmptyFalse() {
        rq.enqueue(253);
        rq.dequeue();
        rq.enqueue(2053);
        assertFalse(rq.isEmpty());
    }

    @Test
    void testSize3() {
        rq.enqueue(253);
        rq.dequeue();
        rq.enqueue(2053);
        rq.enqueue(2053);
        rq.enqueue(2053);
        assertEquals(3, rq.size());
    }

    @Test
    void testSize0() {
        rq.enqueue(253);
        rq.dequeue();
        assertEquals(0, rq.size());
    }

    @Test
    void testEnqueue() {
        final int added = 5;
        rq.enqueue(added);
        rq.sample();

        assertEquals(5, rq.dequeue());
    }

    @Test
    void testEnqueueManyElements() {
        final int itCount = 10000001;

        for (int i = 0; i < itCount; i++) {
            rq.enqueue(i);
        }
        assertEquals(itCount, rq.size());
    }

    @Test
    void testEnqueueNull() {
        assertThrows(IllegalArgumentException.class, () -> rq.enqueue(null));
    }

    @Test
    void testDequeue() {
        final int added = 5;

        rq.enqueue(added);
        rq.sample();
        assertEquals(added, rq.dequeue());

        rq.enqueue(added);
        assertEquals(added, rq.dequeue());
    }

    @Test
    void testDequeueManyElements() {
        final int itCount = 10000000;

        for (int i = 0; i < itCount; i++) {
            rq.enqueue(i);
        }
        assertEquals(itCount, rq.size());

        for (int i = 0; i < itCount; i++) {
            assertTrue(rq.dequeue() < itCount);
        }

        assertTrue(rq.isEmpty());
    }

    @Test
    void testDequeueRandomness() {
        int matchCount = 0;
        final int added = 5;
        final int iterations = 10000000;

        for (int i = 0; i < iterations; i++) {
            rq = new RandomizedQueue<>();

            rq.enqueue(3432);
            rq.enqueue(added);
            rq.enqueue(45453);

            if (rq.dequeue() == added)
                matchCount++;
        }

        final float toBeRounded = matchCount/(float)iterations;
        assertEquals(33, Math.round(toBeRounded * 100));
    }

    @Test
    void testDequeueEmpty() {
        assertThrows(NoSuchElementException.class, rq::dequeue);
    }

    @Test
    void testSample() {
        final int added1 = 1;
        final int added2 = 2;

        rq.enqueue(1);
        rq.enqueue(2);

        for (int i = 0; i < 9; i++) {
            final int sample = rq.sample();
            assertTrue(sample == added1 || sample == added2);
        }
    }

    @Test
    void testSampleRandomness() {
        int foundCount = 0;
        final int added = 523;
        final int iterations = 10000000;

        rq.enqueue(4546);
        rq.enqueue(3423423);
        rq.enqueue(added);
        rq.enqueue(13);

        for (int i = 0; i < iterations; i++)
            if (rq.sample() == added)
                foundCount++;

        final float toBeRounded = foundCount/(float)iterations;
        assertEquals(25, Math.round(toBeRounded * 100));
    }

    @Test
    void testSampleEmpty() {
        assertThrows(NoSuchElementException.class, rq::sample);
    }

    @Test
    void testIterator() {
        final int added1 = 12;
        final int added2 = 98;
        rq.enqueue(added1);
        rq.enqueue(added2);

        int itCount = 0;
        for (int elem : rq) {
            itCount++;
            assertTrue(elem == added1 || elem == added2);
        }

        assertEquals(2, itCount);
    }

    @Test
    void testIteratorRandomness() {
        int foundCount = 0;
        final int added = 37;
        final int itCount = 5000000;

        for (int i = 0; i < itCount; i++) {
            rq = new RandomizedQueue<>();

            // adding 9 elements
            for (int j = 0; j < 9; j++) {
                rq.enqueue(j);
            }

            // adding the tenth element
            rq.enqueue(added);

            if (rq.iterator().next() == added)
                foundCount++;
        }

        assertEquals(10, rq.size());

        final float toBeRounded = foundCount/(float)itCount;
        assertEquals(10, Math.round(toBeRounded * 100));
    }

    @Test
    void testParallelIterators() {
        final int itemCount = 10;

        for (int i = 0; i < itemCount; i++) {
            rq.enqueue(StdRandom.uniform(Integer.MAX_VALUE));
        }

        final Iterator<Integer> it1 = rq.iterator();
        final Iterator<Integer> it2 = rq.iterator();

        int it1Count = 0;
        while (it1.hasNext()) {
            it1.next();
            it1Count++;
        }
        assertEquals(itemCount, it1Count);
        assertTrue(!it1.hasNext() && it2.hasNext());


        int it2Count = 0;
        while (it2.hasNext()) {
            it2.next();
            it2Count++;
        }
        assertEquals(itemCount, it2Count);
    }

    @Test
    void testIteratorEmpty() {
        final Iterator it = rq.iterator();
        assertFalse(it.hasNext());
        assertThrows(NoSuchElementException.class, it::next);
    }
}