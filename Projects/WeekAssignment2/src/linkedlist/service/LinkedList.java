package linkedlist.service;

import linkedlist.LinkedListInterface;
import utility.Node;

import java.util.Objects;

public class LinkedList implements LinkedListInterface {
    public Node head;
    public void insert(int data) {
        Node node = new Node(data);
        if(head==null) {
            head = node;
        }
        else {
            Node curr = head;
            while(curr.next!=null) {
                curr = curr.next;
            }
            curr.next =  node;
        }
    }

    public void insertAtPos(int index,int data)
    {
        Node node = new Node(data);

        if(index==0) {
            node.next = head;
            head = node;
        }
        else{
            Node curr = head;
            for(int i=0;i<index-1;i++)
            {
                curr = curr.next;
            }
            node.next = curr.next;
            curr.next = node;
        }
    }

    public void delete(int key) {
        Node prev = null;
        Node curr = head;

        while (curr != null) {
            if (Objects.equals(curr.val,key)) {
                if(curr == head){
                    head = head.next;
                    curr = head;
                }
                else{
                    prev.next = curr.next;
                    curr = prev.next;
                }
            }
            else{
                prev = curr;
                curr = curr.next;
            }
        }
    }

    public void deleteAtPos(int index)
    {
        if(index==0) {
            head = head.next;
        }
        else {
            Node curr = head;
            Node prev = null;
            for(int i=0;i<index-1;i++) {
                curr = curr.next;
            }
            prev = curr.next;
            curr.next = prev.next;
        }
    }

    public int centreNode() {
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow.val;
    }

    public void sort() {
        Node curr = head;
        Node i = null;

        int temp;

        if (head == null) {
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
        Node curr = head;
        Node prev = null, next = null;

        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        head = prev;
    }
    public int size() {
        Node curr = head;
        int size = 0;
        while(curr!=null) {
            size++;
            curr = curr.next;
        }
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }
    public void traverse() {
        LinkedListIterator iterator = new LinkedListIterator(head);
        while (iterator.hasNext())
            System.out.print(iterator.next().val+ " ");
    }
}
