package queue;

public interface QueueInterface {
    /**
     * Insert the given data at rear of queue.
     * @param data The data to be inserted at top of queue.
     */
    public void enQueue(int data);

    /**
     * Remove the data from the front top of queue
     * @return data which is removed.
     */
    public int deQueue();

    /**
     * Top element at any instance
     * @return top element from queue every time.
     */
    public int peek();

    /**
     * Centre Element Of Queue
     * @return centre element from Queue
     */
    public int centreElement();

    /**
     * Sort the Queue
     */
    public void sort();

    /**
     * Size of the Queue
     * @return The size of Queue at any instance
     */
    public int size();

    /**
     * Reverse the Queue
     */
    public void reverse();

    /**
     * @param key is to check if it is in queue or not
     * @return true if key is in queue else false.
     */
    public boolean contains(int key);

    /**
     * Traverse the Queue
     */
    public void traverse();
}
