package queue.service;

import utility.Node;
import queue.QueueInterface;

import java.util.Objects;

public class Queue implements QueueInterface {
    private Node front;
    private Node rear;

    private int length;

    public void enQueue(int data) {
        if (front == null) {
            rear = new Node(data);
            front = rear;
        } else {
            rear.next = new Node(data);
            rear = rear.next;
        }
        length++;
    }

    public int deQueue() {
        int item = front.val;
        front = front.next;
        length--;
        return item;
    }

    public int peek() {
        return front.val;
    }

    public int centreElement() {
        Node slow = front;
        Node fast = front;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow.val;
    }

    public void sort() {
        Node curr = front;
        Node i = null;

        int temp;

        if (front == null) {
            return;
        }
        else {
            while (curr != null) {
                i = curr.next;

                while (i != null) {
                    if (curr.val > i.val) {
                        temp = curr.val;
                        curr.val = i.val;
                        i.val = temp;
                    }

                    i = i.next;
                }
                curr = curr.next;
            }
        }
    }

    public int size() {
        return length;
    }

    public void reverse() {
        Node curr = front;
        Node prev = null, next = null;

        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        front = prev;
    }

    public boolean contains(int key) {
        Node curr = front;
        while (curr != null) {
            if(Objects.equals(curr.val,key)) {
                return true;
            }
            curr = curr.next;
        }
        return false;
    }
    public void traverse() {
        QueueIterator iterator = new QueueIterator(front);
        while (iterator.hasNext())
            System.out.print(iterator.next().val+ " ");
    }

    public boolean isEmpty() {
        return length == 0;
    }
}
