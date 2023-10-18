package queue.service;
import utility.Node;

public class QueueIterator {
    private Node current;

    public QueueIterator(Node first) {
        current = first;
    }

    public boolean hasNext() {
        return current != null;
    }

    public Node next() {
        Node tempo = current;
        current = current.next;
        return tempo;
    }
}
