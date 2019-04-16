import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] random;
    private int size;

    // construct an empty randomized queue
    public RandomizedQueue() {
        random = (Item[]) new Object[2];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException("item to add cannot be null.");

        if (random.length == size) resize(size * 2);
        random[size++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();

        int index = randomIndex();
        int end   = size - 1;

        Item item     = random[index];
        random[index] = random[end];
        random[end]   = null;
        size--;

        // Resize when the queue is less than 1/4 full
        if (size > 0 && size < random.length / 4) resize(random.length / 2);
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();
        return random[randomIndex()];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    // unit testing (optional)
    public static void main(String[] args) {
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        queue.enqueue("this");
        queue.enqueue("is");
        queue.enqueue("a");
        queue.enqueue("test");
        queue.enqueue("case");

        while(queue.size > 0) System.out.println(queue.dequeue());
    }

    // Resize array
    private void resize(final int capacity) {
        Item[] newRandom = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            newRandom[i] = random[i];
        }
        random = newRandom;
    }

    // Return a random index value from the queue
    private int randomIndex() {
        return StdRandom.uniform(size);
    }

    // Iterator
    private class RandomizedQueueIterator implements Iterator<Item> {

        private int[] randomIndex;
        private int index;

        public RandomizedQueueIterator() {
            randomIndex = new int[size];
            index = 0;
            for (int i = 0; i < randomIndex.length; i++) {
                randomIndex[i] = i;
            }
            StdRandom.shuffle(randomIndex);
        }

        public boolean hasNext() { return index < size; }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return random[randomIndex[index++]];
        }
    }
}
