package linkedlist;

public interface LinkedListInterface {

    /**
     * Insert the given value at end of linked list.
     * @param data The data to be inserted in linked list.
     */
    public void insert(int data);

    /**
     * Insert the given value at given index of linked list.
     * @param data The value to be inserted in linked list.
     * @param index The index at which data is need to be inserted
     */
    public void insertAtPos(int index,int data);

    /**
     * Delete the given key from linked list.
     * @param key The key need to be deleted from linked list.
     */
    public void delete(int key);

    /**
     * Delete the given index from linked list.
     * @param index The index need to be deleted from linked list.
     */
    public void deleteAtPos(int index);

    /**
     * Centre Node Of Linked List
     * @return centre node of linked list
     */
    public int centreNode();

    /**
     * Sort the Linked List
     */
    public void sort();

    /**
     * Size of the Linked List
     * @return The size of linked list at any instance
     */
    public int size();

    /**
     * Reverse the linked list
     */
    public void reverse();

    /**
     * Traverse the linked list
     */
    public void traverse();
}
