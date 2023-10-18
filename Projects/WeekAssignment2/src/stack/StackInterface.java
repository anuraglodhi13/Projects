package stack;

public interface StackInterface {
    /**
     * Push the given data at top of stack.
     * @param data The data to be inserted at top of stack.
     */
    public void push(int data);

    /**
     * Popped the data present at the top of stack
     * @return data which is popped.
     */
    public int pop();

    /**
     * Top element at any instance
     * @return top element from stack every time if empty return -1.
     */
    public int peek();

    /**
     * Centre Element Of Stack
     * @return centre element from Stack
     */
    public int centreElement();

    /**
     * Sort the Stack
     */
    public void sort();

    /**
     * Size of the Stack
     * @return The size of stack at any instance
     */
    public int size();

    /**
     * Reverse the stack
     */
    public void reverse();

    /**
     * @param key is to check if it is in stack or not
     * @return true if key is in stack else false.
     */
    public boolean contains(int key);

    /**
     * Traverse the stack
     */
    public void traverse();
}
