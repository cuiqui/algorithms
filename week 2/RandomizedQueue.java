import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] arr;
    private int N = 0;

    public RandomizedQueue() {
        arr = (Item[]) new Object[1];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return N == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return N;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (N == arr.length) {
            resize(N * 2);
        }
        arr[N++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int popIdx = StdRandom.uniform(0, N);
        Item item = arr[popIdx];
        arr[popIdx] = arr[N - 1];
        arr[N - 1] = null;
        N--;
        if (N > 0 && N == arr.length / 4) {
            resize(arr.length / 2);
        }

        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return arr[StdRandom.uniform(this.N)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Item[] rndArr;
        private int idx = 0;

        public ListIterator() {
            rndArr = (Item[]) new Object[N];
            for (int i = 0; i < N; i++) {
                rndArr[i] = arr[i];
            }
            StdRandom.shuffle(rndArr);
        }

        public boolean hasNext() {
            return idx < N;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return rndArr[idx++];
        }
    }

    private void resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++) {
            temp[i] = arr[i];
        }
        arr = temp;
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
        rq.enqueue(2);
        rq.enqueue(4);
        rq.enqueue(6);
        rq.sample();
        rq.dequeue();
        rq.isEmpty();
        rq.size();

        for (int i : rq) {
            StdOut.println(i);
        }
    }
}
