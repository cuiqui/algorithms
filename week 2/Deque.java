import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node front, rear;
    private int size;

    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return this.size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return this.size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node newFront = new Node();
        newFront.item = item;
        if (this.front == null) {
            this.rear = this.front = newFront;
        } else {
            newFront.next = this.front;
            this.front.prev = newFront;
            this.front = newFront;
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node newLast = new Node();
        newLast.item = item;
        if (this.rear == null) {
            this.front = this.rear = newLast;
        } else {
            newLast.prev = this.rear;
            this.rear.next = newLast;
            this.rear = newLast;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item item = this.front.item;
        this.front = this.front.next;
        if (this.front == null) {
            this.rear = null;
        } else {
            this.front.prev = null;
        }
        size--;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item item = this.rear.item;
        this.rear = this.rear.prev;
        if (this.rear == null) {
            this.front = null;
        } else {
            this.rear.next = null;
        }
        size--;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = front;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }

    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> dq = new Deque<Integer>();
        dq.addFirst(3);
        dq.addFirst(4);
        dq.addLast(5);
        dq.removeFirst();
        System.out.println(dq.removeLast());
        System.out.println(dq.removeFirst());
        System.out.println(dq.size());
        dq.addLast(10);
        dq.addLast(15);
        for (int i : dq) {
            System.out.println(i);
        }
        System.out.println(dq.isEmpty());
    }
}
