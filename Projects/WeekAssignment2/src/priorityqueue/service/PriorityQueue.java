package priorityqueue.service;


import priorityqueue.PriorityQueueInterface;
import priorityqueue.utilities.MaxHeap;
import priorityqueue.utilities.MinHeap;

import java.util.ArrayList;

public class PriorityQueue implements PriorityQueueInterface {

    private static final int MAX_SIZE = 1000000;
    private int [] heap;
    private int size;
    private boolean reverseOrder = false;
    public PriorityQueue() {
        heap = new int[MAX_SIZE];
        size = 0;
    }

    public static int parent(int i) {
        return (i - 1) / 2;
    }
    public void enQueue(int data) {
        if (size >= MAX_SIZE) {
            System.out.println("The queue is full. Cannot insert");
            return;
        }

        heap[size] = data;
        size = size + 1;


        int i = size - 1;
        if(!reverseOrder) { // max order priority
            while (i != 0 && heap[parent(i)] < heap[i]) {
                int temp = heap[i];
                heap[i] = heap[parent(i)];
                heap[parent(i)] = temp;
                i = parent(i);
            }
        }
        else { // min order priority
            while (i != 0 && heap[parent(i)] > heap[i]) {
                int temp = heap[i];
                heap[i] = heap[parent(i)];
                heap[parent(i)] = temp;
                i = parent(i);
            }
        }
    }
    public int deQueue() {
        int maxItem = heap[0];

        heap[0] = heap[size - 1];
        size = size - 1;

        if(!reverseOrder) {
            MaxHeap.maxHeapify(0, size, heap);
        }
        else {
            MinHeap.minHeapify(0, size, heap);
        }
        return maxItem;
    }

    public int peek() {
        return heap[0];
    }

    public int centreElement() {
        ArrayList <Integer> elementList = maintainElements();
        for (Integer integer : elementList) {
            enQueue(integer);
        }
        return elementList.get(size / 2);
    }

    public int size() {
        return size;
    }

    public void reverse() {
        ArrayList <Integer> elementList = maintainElements();
        reverseOrder = !reverseOrder;
        for (Integer integer : elementList) {
            enQueue(integer);
        }
    }

    public boolean contains(int key) {
        ArrayList <Integer> elementList = maintainElements();
        for (Integer integer : elementList) {
            enQueue(integer);
        }

        for (Integer integer : elementList) {
            if (integer.equals(key)) {
                return true;
            }
        }
        return false;
    }

    public void traverse() {
        ArrayList <Integer> elementList = maintainElements();
        for (Integer integer : elementList) {
            enQueue(integer);
        }
        PriorityQueueIterator it = new PriorityQueueIterator(heap, size,reverseOrder);
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
        maintainElements();
        for (Integer integer : elementList) {
            enQueue(integer);
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private ArrayList <Integer> maintainElements () {
        ArrayList <Integer> elementList = new ArrayList<Integer>();
        int temp = size;
        while (!isEmpty()) {
            int ele = deQueue();
            elementList.add(ele);
        }
        for(int i=0;i<temp;i++) heap[i] = 0;
        return  elementList;
    }
}