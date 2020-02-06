public class LinkedQueueOfStrings {
    private Node first, last;

    public boolean isEmpty() {
        return first == null;
    }

    private class Node {
        String item;
        Node next;
    }

    public void enqueue(String item) {
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) {
            first = last;
        } else {
            oldlast.next = last;
        }
    }

    public String dequeue() {
        String item = first.item;
        first = first.next;
        if (isEmpty()) {
            last = null;
        }
        return item;
    }

    public static void main(String[] args) {
        LinkedQueueOfStrings lq = new LinkedQueueOfStrings();
        lq.enqueue("hola");
        lq.enqueue("que");
        lq.enqueue("tal");
        String first = lq.dequeue();
        System.out.println(first);
    }
}
