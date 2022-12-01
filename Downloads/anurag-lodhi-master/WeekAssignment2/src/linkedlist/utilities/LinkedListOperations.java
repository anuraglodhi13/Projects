package linkedlist.utilities;

import linkedlist.service.LinkedList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class LinkedListOperations {
    public static void print(LinkedList list) {
        if(list.isEmpty()) {
            System.out.println("List is empty please insert element first");
            return;
        }
        System.out.println("Elements present in list are: ");
        list.traverse();
        System.out.println();

    }

    public static void reverse(LinkedList list) {
        if(list.isEmpty()) {
            System.out.println("List is empty please insert element first");
            return;
        }
        list.reverse();
        System.out.println("Linked List reversed successfully!!!. ");
    }

    public static void sort(LinkedList list) {
        if(list.isEmpty()) {
            System.out.println("List is empty please insert element first");
            return;
        }
        list.sort();
        System.out.println("Linked List sorted successfully!!!. ");
    }

    public static void centreNode(LinkedList list) {
        if(list.isEmpty()) {
            System.out.println("List is empty please insert element first");
            return;
        }
        System.out.println(list.centreNode()+" is centre node present in list.");
    }

    public static void size(LinkedList list) {
        if(list.isEmpty()) {
            System.out.println("List is empty please insert element first");
            return;
        }
        System.out.println("Current size of linked list is "+ list.size()+".");
    }

    public static void deleteAtPos(LinkedList list) {
        if(list.isEmpty()) {
            System.out.println("List is empty please insert element first");
            return;
        }
        System.out.println("Enter pos(0-based indexing) to delete the element");
        Scanner sc = new Scanner(System.in);
        Integer index = sc.nextInt();
        list.deleteAtPos(index);
        System.out.println("Element deleted successfully");
        if(!list.isEmpty()) {
            System.out.println("Elements present in list are : ");
            list.traverse();
            System.out.println();
        }

    }

    public static void delete(LinkedList list) {
        if(list.isEmpty()) {
            System.out.println("List is empty please insert element first");
            return;
        }
        System.out.println("Enter node value to delete the element");
        Scanner sc = new Scanner(System.in);
        Integer nodeVal = sc.nextInt();
        list.delete(nodeVal);
        System.out.println("Element deleted successfully");
        if(!list.isEmpty()) {
            System.out.println("Elements present in list are : ");
            list.traverse();
            System.out.println();
        }
    }

    public static void insertAtPos(LinkedList list) throws IOException {
        System.out.println("Enter pos(0-based indexing) and value separated by white space to insert the element");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String lines = br.readLine();
        String[] itemStrings = lines.trim().split("\\s+");
        list.insertAtPos(Integer.parseInt(itemStrings[0]),Integer.parseInt(itemStrings[1]));
        System.out.println("Elements present in list are : ");
        list.traverse();
        System.out.println();
    }

    public static void insert(LinkedList list) throws IOException {
        String lines;
        System.out.println("Enter elements separated with white space");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        lines = br.readLine();
        while(lines.equals("")){
            System.out.println("You haven't enter anything!!!");
            System.out.println("Enter elements separated with white space");
            lines = br.readLine();
        }
        String[] itemStrings = lines.trim().split("\\s+");
        for (String itemString : itemStrings) {
            list.insert(Integer.parseInt(itemString));
        }
        System.out.println("Provided elements are inserted to linked list.");
    }
}
