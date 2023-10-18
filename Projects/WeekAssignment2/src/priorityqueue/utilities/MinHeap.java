package priorityqueue.utilities;

public class MinHeap {
    public static int[] minHeapify(int i,int size, int heap[]){
        int left = 2*i + 1;

        int right = 2*i + 2;

        int largest = i;

        if (left <= size && heap[left] < heap[largest]) {
            largest = left;
        }

        if (right <= size && heap[right] < heap[largest]) {
            largest = right;
        }

        if (largest != i) {
            int temp = heap[i];
            heap[i] = heap[largest];
            heap[largest] = temp;
            minHeapify(largest,size,heap);
        }
        return heap;
    }
}
