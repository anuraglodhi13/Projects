package stack.service;
import utility.Node;

public class StackIterator {
    private Node current;

    public StackIterator(Node first) {
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
