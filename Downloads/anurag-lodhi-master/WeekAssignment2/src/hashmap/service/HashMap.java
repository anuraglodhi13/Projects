package hashmap.service;

import hashmap.HashMapInterface;
import utility.HashNode;
import utility.Node;
import linkedlist.service.LinkedList;

public class HashMap implements HashMapInterface {
    private int size = 0;
    private final LinkedList keys;
    HashNode[] nodes;
    public HashMap() {
        keys = new LinkedList();
        nodes = new HashNode[10000];
    }
    public void insert(int key, int val) {
        int index = getIndex(key);

        if(contains(key)) {
            delete(key);
        }

        HashNode prev = findElement(index,key);

        if(prev.next==null)
            prev.next = new HashNode(key,val);
        else
            prev.next.value = val;
        size++;
        keys.insert(key);
    }
    public int getIndex(int key){
        return Integer.hashCode(key)%nodes.length;
    }
    public HashNode findElement(int index,int key){
        if(nodes[index]==null)
            return nodes[index] =  new HashNode(-1,-1);

        HashNode prev = nodes[index];

        while(prev.next!=null && prev.next.key!=key){
            prev = prev.next;
        }
        return prev;

    }
    public void delete(int key) {
        int index = getIndex(key);
        HashNode prev = findElement(index, key);

        if(prev.next != null)
            prev.next = prev.next.next;
        size--;
        keys.delete(key);
    }
    public boolean contains(int key) {
        int index = getIndex(key);
        HashNode prev = findElement(index, key);

        while(prev.next!= null){
            if(prev.next.key == key) {
                return true;
            }
            prev = prev.next;
        }
        return false;
    }
    public int getValue(int key) {
        int index = getIndex(key);

        HashNode prev = findElement(index,key);
        int ans = -1;
        while(prev.next!= null){
            if(prev.next.key == key) {
                ans = prev.next.value;
                return ans;
            }
            prev = prev.next;
        }
        return ans;
    }

    public int size() {
        return size;
    }

    public void traverse() {
        HashMapIterator iterator = new HashMapIterator(keys.head);
        while (iterator.hasNext()) {
              Node curr = iterator.next();
            System.out.print("Key : " + curr.val + " Value : " + getValue(curr.val) + "\n");
        }
    }
    public boolean isEmpty() {
        return size() == 0;
    }
}
