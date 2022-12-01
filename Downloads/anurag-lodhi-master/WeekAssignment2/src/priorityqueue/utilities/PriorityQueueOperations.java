package priorityqueue.utilities;

import priorityqueue.service.PriorityQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class PriorityQueueOperations {
    public static void print(PriorityQueue priorityQueue) {
        if(priorityQueue.isEmpty()) {
            System.out.println("Queue is empty please push element first");
            return;
        }
        System.out.println("Elements present in queue are: ");
        priorityQueue.traverse();
        System.out.println();

    }

    public static void reverse(PriorityQueue priorityQueue) {
        if(priorityQueue.isEmpty()) {
            System.out.println("Queue is empty please push element first");
            return;
        }
        priorityQueue.reverse();
        System.out.println("Queue reversed successfully!!! ");
    }

    public static void center(PriorityQueue priorityQueue) {
        if(priorityQueue.isEmpty()) {
            System.out.println("Queue is empty please push element first");
            return;
        }
        System.out.println(priorityQueue.centreElement()+" is centre element present in queue.");
    }

    public static void size(PriorityQueue priorityQueue) {
        if(priorityQueue.isEmpty()) {
            System.out.println("Queue is empty please push element first");
            return;
        }
        System.out.println("Current size of queue is "+ priorityQueue.size()+".");
    }

    public static void contains(PriorityQueue priorityQueue) {
        if(priorityQueue.isEmpty()) {
            System.out.println("Queue is empty please push element first");
            return;
        }
        System.out.println("Enter element to check if element is contained by queue.");
        Scanner sc = new Scanner(System.in);
        Integer element = sc.nextInt();
        if(priorityQueue.contains(element)) {
            System.out.println(element+ " is present in queue.");
        }
        else {
            System.out.println(element+" is not present in queue.");
        }
    }

    public static void peek(PriorityQueue priorityQueue) {
        if(priorityQueue.isEmpty()) {
            System.out.println("Queue is empty please push element first");
            return;
        }
        System.out.println(priorityQueue.peek()+" is peek element of queue.");
    }

    public static void deQueue(PriorityQueue priorityQueue) {
        if(priorityQueue.isEmpty()) {
            System.out.println("Queue is empty please push element first");
            return;
        }
        System.out.println(priorityQueue.deQueue()+" is dequeued from queue.");
    }

    public static void enQueue(PriorityQueue priorityQueue) throws IOException {
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
            priorityQueue.enQueue(Integer.parseInt(itemString));
        }
        System.out.println("Provided elements are pushed to queue.");

    }
}
