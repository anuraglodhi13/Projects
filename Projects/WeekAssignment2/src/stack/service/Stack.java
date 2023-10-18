package stack.service;

import utility.Node;
import stack.StackInterface;

import java.util.Objects;

public class Stack implements StackInterface {
    private Node top;
    private int size;

    public void push(int data) {
        Node tempNode = new Node(data);
        tempNode.next = top;
        top  = tempNode;
        size++;
    }
    public int pop() {
        int result = top.val;
        top = top.next;
        size--;
        return result;
    }

    public int peek() {
        return top.val;
    }



    public int centreElement() {
        Node slow = top;
        Node fast = top;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow.val;
    }

    public int size(){
        return size;
    }

    public void sort() {
        Node curr = top;
        Node i = null;

        int temp;

        if (top == null) {
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

    public void reverse () {
        Node curr = top;
        Node prev = null, next = null;

        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        top = prev;
    }

    public boolean contains (int key) {
        Node curr = top;
        while (curr != null) {
            if(Objects.equals(curr.val,key)) {
                return true;
            }
            curr = curr.next;
        }
        return false;
    }
    public boolean isEmpty() {
        return size == 0;
    }

    public void traverse() {
        StackIterator iterator = new StackIterator(top);
        while (iterator.hasNext())
            System.out.print(iterator.next().val+ " ");
    }
}
