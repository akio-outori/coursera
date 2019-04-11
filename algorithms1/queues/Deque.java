import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Item[] deque;
    private int size = 0;

    // construct an empty deque
    public Deque() {
        deque = (Item[]) new Object[2];
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item.equals("null")) {
            throw new IllegalArgumentException("item to add cannot be null.");
        }
    }

    // add the item to the end
    public void addLast(Item item) {
        if (item.equals("null")) {
            throw new IllegalArgumentException("item to add cannot be null.");
        }
    }

    // remove and return the item from the front
    public int removeFirst() {
        return 0;
    }

    // remove and return the item from the end
    public int removeLast() {
        return 0;
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    // unit testing (optional)
    public static void main(String[] args) {
        Deque<Integer> test = new Deque<Integer>();
        Iterator<Integer> test2 = test.iterator();
        System.out.println(test2);
    }

    private class ArrayIterator implements Iterator<Item> {

        private int i;

        public ArrayIterator() {
            i = size + 1;
        }

        public boolean hasNext() {
            return i <= size;
        }

        public void remove() {
            throw new UnsupportedOperationException("We don't implement remove");
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return deque[i++];
        }

    }

}
