package priorityqueue;

public interface PriorityQueueInterface {
    /**
     * Insert the given data in priority queue.
     * @param data The data to be inserted in priority queue.
     */
    public void enQueue(int data);

    /**
     * Remove the data from the priority queue
     * @return data which have higher priority in the priority queue.
     */
    public int deQueue();

    /**
     * peek element of priority queue
     * @return data which have higher priority in the priority queue.
     */
    public int peek();

    /**
     * Centre Element Of Priority Queue
     * @return centre element from Priority Queue
     */
    public int centreElement();

    /**
     * Size of the Priority Queue
     * @return The size of Priority Queue at any instance
     */
    public int size();

    /**
     * Reverse the Priority Queue
     */
    public void reverse();

    /**
     * @param key is to check if it is in Priority queue or not
     * @return true if key is in Priority Queue else false.
     */
    public boolean contains(int key);

    /**
     * Traverse the Priority Queue
     */
    public void traverse();
}
