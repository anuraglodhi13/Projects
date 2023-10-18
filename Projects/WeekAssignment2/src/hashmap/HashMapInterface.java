package hashmap;

public interface HashMapInterface {
    /**
     * Insert the given key and value in HashMao.
     * @param key the key of the hashmap
     * @param val the value of the hashmap
     */
    public void insert(int key,int val);

    /**
     * Remove the key and value from the hashmap.
     * @param key delete the key and corresponding value from hashmap.
     */
    public void delete(int key);

    /**
     * True if key is contained by hashmap
     * @param key to check if it's present in hashmap or not.
     * @return true if key exist else return false.
     */
    public boolean contains(int key);

    /**
     * Value is returned which present for given key
     * @param key is to check the value
     * @return the value for given key
     */
    public int getValue(int key);

    /**
     * Size of the Hashmap
     * @return The size of Hashmap at any instance
     */
    public int size();

    /**
     * Traverse the whole hashmap
     */
    public void traverse();
}
