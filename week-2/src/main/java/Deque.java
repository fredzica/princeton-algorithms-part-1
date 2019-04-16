import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first = null;
    private Node last = null;
    private int size = 0;

    // is the main.java.deque empty?
    public boolean isEmpty() {
        return first == null;
    }

    // return the number of items on the main.java.deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null)
            throw new IllegalArgumentException("Null argument is not allowed");

        Node n = new Node();
        n.item = item;
        if (isEmpty()) {
            first = n;
            last = n;
        } else {
            n.next = first;
            first = n;
        }
            
        size++;
    }

    // add the item to the end
    public void addLast(Item item) {
        if (item == null)
            throw new IllegalArgumentException("Null argument is not allowed");

        Node n = new Node();
        n.item = item;
        if (isEmpty()) {
            first = n;
            last = n;
        } else {
            last.next = n;
            last = n;
        }

        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException("The main.java.deque is empty");

        if (first == last)
            last = null;

        Item removed = first.item;
        first = first.next;
        size--;

        return removed;
    }

    // remove and return the item from the end
    public Item removeLast() {
        if (isEmpty())
            throw new NoSuchElementException("The main.java.deque is empty");

        if (first == last)
            first = null;

        Item removed = last.item;
        last = null;
        size--;

        return removed;
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class Node {
        private Item item;
        private Node next;
    }

    private class DequeIterator implements Iterator<Item> {

        private Node current;

        public DequeIterator() {
            this.current = first;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException("There are no more elements available");

            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}