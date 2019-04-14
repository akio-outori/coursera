import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node<Item> first;    // beginning of queue
    private Node<Item> last;     // end of queue
    private int size;

    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
        private Node<Item> previous;
    }

    // construct an empty deque
    public Deque() {
        first = null;
        last  = null;
        size  = 0;
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
        if (item.equals("null")) throw new IllegalArgumentException("item to add cannot be null.");
        Node<Item> oldfirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldfirst;
        if (isEmpty()) last = first;
        size++;
    }

    // add the item to the end
    public void addLast(Item item) {
        if (item.equals("null")) throw new IllegalArgumentException("item to add cannot be null.");
        Node<Item> oldlast = last;
        last = new Node<Item>();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else oldlast.next = last;
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("deque is empty");
        Item item = first.item;
        first = first.next;
        size --;
        return item;
    }

    // remove and return the item from the end
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException("deque is empty");
        Item item = last.item;
        last = last.previous;
        if (last != null) last.next = null;
        if (size == 1) first = null;
        size--;
        return item;
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new ArrayIterator(first);
    }

    // unit testing (optional)
    public static void main(String[] args) {
        Deque<Integer> queue = new Deque<Integer>();

        // Test add
        queue.addFirst(6);
        queue.addLast(1);
        queue.addLast(2);
        queue.addLast(3);
        queue.addLast(4);
        queue.addFirst(5);
        System.out.println(queue.printDeque());
        System.out.println(queue.size() + " left on queue");

        // Test remove
        System.out.println(queue.removeFirst());
        System.out.println(queue.printDeque());
        System.out.println(queue.size() + " left on queue");

        System.out.println(queue.removeLast());
        System.out.println(queue.printDeque());
        System.out.println(queue.size() + " left on queue");
    }

    private String printDeque() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item);
            s.append(' ');
        }
        return s.toString();
    }

    private class ArrayIterator implements Iterator<Item> {
        private Node<Item> current;

        public ArrayIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext() { return current != null; }
        public void remove() { throw new UnsupportedOperationException("We don't implement remove"); }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }

    }

}
