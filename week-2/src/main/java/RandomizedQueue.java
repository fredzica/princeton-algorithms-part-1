import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private static final String NO_ELEMENT_ERROR_MSG =
            "There aren't any available elements. Did you check before invoking the method?";

    private Item[] items;
    private int lastIx = -1;
    private int size = 0;


    public RandomizedQueue() {
        this.items = (Item[])new Object[1];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return this.size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null)
            throw new IllegalArgumentException("Nulls not accepted as argument");

        if (items.length == this.size)
            resizeArray(items.length * 2);

        items[++lastIx] = item;

        size++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException(RandomizedQueue.NO_ELEMENT_ERROR_MSG);

        final int ix = generateRandomIndex();
        Item found = items[ix];

        // fill the removed position with the last item
        items[ix] = items[lastIx];
        // and make the previous last item empty
        items[lastIx] = null;

        lastIx--;
        size--;

        if (size > 0 && items.length/4 == this.size)
            resizeArray(items.length/2);

        return found;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (this.isEmpty())
            throw new NoSuchElementException(RandomizedQueue.NO_ELEMENT_ERROR_MSG);

        return items[generateRandomIndex()];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private void resizeArray(int newSize) {
        Item[] newArr = (Item[])new Object[newSize];

        System.arraycopy(items, 0, newArr, 0, size);
        items = newArr;
    }

    private int generateRandomIndex() {
        return StdRandom.uniform(size);
    }

    private class RandomizedQueueIterator implements Iterator<Item> {

        private final RandomizedQueue<Item> iteratorQ;

        private RandomizedQueueIterator() {
            iteratorQ = new RandomizedQueue<>();
            iteratorQ.size = size;
            iteratorQ.lastIx = lastIx;
            iteratorQ.items = (Item[]) new Object[items.length];
            System.arraycopy(items, 0, iteratorQ.items, 0, size);
        }

        @Override
        public boolean hasNext() {
            return !iteratorQ.isEmpty();
        }

        @Override
        public Item next() {
            if (!this.hasNext())
                throw new NoSuchElementException(RandomizedQueue.NO_ELEMENT_ERROR_MSG);

            return iteratorQ.dequeue();
        }
    }
}
