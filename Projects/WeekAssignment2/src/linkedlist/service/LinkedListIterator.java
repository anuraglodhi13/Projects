package linkedlist.service;
import utility.Node;

public class LinkedListIterator {
    private Node current;

    public LinkedListIterator(Node first) {
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
