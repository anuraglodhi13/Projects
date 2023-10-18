package queue.utilities;

import queue.service.Queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class QueueOperations {
    public static void print(Queue queue) {
        if(queue.isEmpty()) {
            System.out.println("Queue is empty please push element first");
            return;
        }
        System.out.println("Elements present in queue are: ");
        queue.traverse();
        System.out.println();

    }

    public static void reverse(Queue queue) {
        if(queue.isEmpty()) {
            System.out.println("queue is empty please push element first");
            return;
        }
        queue.reverse();
        System.out.println("Queue reversed successfully!!!. ");
    }

    public static void sort(Queue queue) {
        if(queue.isEmpty()) {
            System.out.println("Queue is empty please push element first");
            return;
        }
        queue.sort();
        System.out.println("Queue sorted successfully!!!. ");
    }

    public static void center(Queue queue) {
        if(queue.isEmpty()) {
            System.out.println("Queue is empty please push element first");
            return;
        }
        System.out.println(queue.centreElement()+" is centre element present in queue.");
    }

    public static void size(Queue queue) {
        if(queue.isEmpty()) {
            System.out.println("Queue is empty please push element first");
            return;
        }
        System.out.println("Current size of queue is "+ queue.size()+".");
    }

    public static void contains(Queue queue) {
        if(queue.isEmpty()) {
            System.out.println("Queue is empty please push element first");
            return;
        }
        System.out.println("Enter element to check if element is contained by queue.");
        Scanner sc = new Scanner(System.in);
        Integer element = sc.nextInt();
        if(queue.contains(element)) {
            System.out.println(element+ " is present in queue.");
        }
        else {
            System.out.println(element+" is not present in queue.");
        }
    }

    public static void peek(Queue queue) {
        if(queue.isEmpty()) {
            System.out.println("Queue is empty please push element first");
            return;
        }
        System.out.println(queue.peek()+" is peek element of queue.");
    }

    public static void deQueue(Queue queue) {
        if(queue.isEmpty()) {
            System.out.println("Queue is empty please push element first");
            return;
        }
        System.out.println(queue.deQueue()+" is popped from queue.");
    }

    public static void enQueue(Queue queue) throws IOException {
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
            queue.enQueue(Integer.parseInt(itemString));
        }
        System.out.println("Provided elements are pushed to queue.");

    }
}
