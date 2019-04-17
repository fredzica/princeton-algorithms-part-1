import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class DequeTest {

    @Test
    void isEmptyTest() {
        Deque d = new Deque();
        assertTrue(d.isEmpty());
    }

    @Test
    void isNotEmptyTest() {
        Deque<Integer> d = new Deque<>();
        d.addFirst(2);
        d.removeLast();
        d.addLast(3);
        d.addFirst(5);
        d.removeLast();
        assertFalse(d.isEmpty());
    }

    @Test
    void size3Test() {
        Deque<Integer> d = new Deque<>();
        d.addFirst(1);
        d.addLast(Integer.MAX_VALUE);
        d.addLast(Integer.MAX_VALUE);

        assertEquals(3, d.size());
    }

    @Test
    void size0Test() {
        Deque<Integer> d = new Deque<>();

        assertEquals(0, d.size());
    }

    @Test
    void addFirstTest() {
        final int added = 4;

        Deque<Integer> d = new Deque<>();
        d.addFirst(5);
        d.addFirst(53423);
        d.addFirst(53);
        d.addLast(454432423);
        d.addFirst(added);

        assertEquals(added, d.removeFirst());
    }

    @Test
    void addLastTest() {
        final int added = 4;

        Deque<Integer> d = new Deque<>();
        d.addLast(5);
        d.addLast(53423);
        d.addLast(added);
        d.addFirst(454432423);
        d.addFirst(-231);

        assertEquals(added, d.removeLast());
    }

    @Test
    void removeFirstTest() {
        final int added = 4;

        Deque<Integer> d = new Deque<>();
        d.addFirst(6);
        d.addFirst(34);
        d.addLast(added);
        d.removeFirst();
        d.removeFirst();

        assertEquals(added, d.removeFirst());
    }

    @Test
    void removeLastTest() {
        final int added = 4;

        Deque<Integer> d = new Deque<>();
        d.addFirst(3);
        d.removeFirst();
        d.addLast(20);
        d.addFirst(6);
        d.addLast(added);
        d.removeFirst();
        d.addFirst(8);

        assertEquals(added, d.removeLast());
    }

    @Test
    void removeLastManyTest() {
        final int added = 4;

        Deque<Integer> d = new Deque<>();
        d.addFirst(3);
        d.addLast(added);
        d.addLast(20);
        d.addLast(40);
        d.addFirst(6);
        d.addFirst(8);
        d.removeLast();
        d.removeFirst();
        d.removeLast();

        assertEquals(added, d.removeLast());
    }

    @Test
    void removeLastUniqueTest() {
        final int added = 4;

        Deque<Integer> d = new Deque<>();
        d.addFirst(added);
        d.addFirst(3);
        d.removeLast();
        d.removeFirst();
        d.addFirst(added);

        assertEquals(added, d.removeLast());
    }

    @Test
    void removeLastPointerLoiteringTest() {
        final int firstAdded = 7;
        final int added = 4;

        Deque<Integer> d = new Deque<>();
        d.addFirst(firstAdded);
        d.addLast(added);

        assertEquals(added, d.removeLast());

       Integer last = null;
       for (int elem : d) {
           last = elem;
       }

       assertEquals(firstAdded, last);
    }

    @Test
    void removeFirstExceptionTest() {
        final int added = 3;

        Deque<Integer> d = new Deque<>();
        d.addFirst(added);

        assertEquals(added, d.removeLast());
        assertThrows(NoSuchElementException.class, d::removeFirst);
    }

    @Test
    void removeLastExceptionTest() {
        final int added = 3;

        Deque<Integer> d = new Deque<>();
        d.addFirst(added);

        assertEquals(added, d.removeFirst());
        assertThrows(NoSuchElementException.class, d::removeLast);
    }

    @Test
    void iteratorTest() {
        final String expected = "belo horizonte";

        Deque<Character> d = new Deque<>();
        for (int i = expected.length() - 1; i >= 0; i--) {
            d.addFirst(expected.charAt(i));
        }

        String result = "";
        for (char c : d) {
            result = String.format("%s%c", result, c);
        }

        assertEquals(expected, result);
    }
}