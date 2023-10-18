package hashmap.service;

import utility.Node;

public class HashMapIterator {
    private Node current;

    public HashMapIterator(Node first) {
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
