package priorityqueue.service;

import priorityqueue.utilities.MaxHeap;
import priorityqueue.utilities.MinHeap;

public class PriorityQueueIterator {
    private final int [] heap;
    private int size;
    private boolean reverseOrder;
    public PriorityQueueIterator(int[] heap,int size,boolean reverseOrder) {
        this.heap = heap;
        this.size = size;
        this.reverseOrder = reverseOrder;
    }

    public boolean hasNext() {
        return !(size == 0);
    }

    public int next() {
        return deQueue();
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
}
