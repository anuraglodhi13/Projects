package stack.utilities;

import stack.service.Stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class StackOperations {
    public static void print(Stack stack) {
        if(stack.isEmpty()) {
            System.out.println("Stack is empty please push element first");
            return;
        }
        System.out.println("Elements present in stack are: ");
        stack.traverse();
        System.out.println();

    }

    public static void reverse(Stack stack) {
        if(stack.isEmpty()) {
            System.out.println("Stack is empty please push element first");
            return;
        }
        stack.reverse();
        System.out.println("Stack reversed successfully!!!. ");
    }

    public static void sort(Stack stack) {
        if(stack.isEmpty()) {
            System.out.println("Stack is empty please push element first");
            return;
        }
        stack.sort();
        System.out.println("Stack sorted successfully!!!. ");
    }

    public static void center(Stack stack) {
        if(stack.isEmpty()) {
            System.out.println("Stack is empty please push element first");
            return;
        }
        System.out.println(stack.centreElement()+" is centre element present in stack.");
    }

    public static void size(Stack stack) {
        if(stack.isEmpty()) {
            System.out.println("Stack is empty please push element first");
            return;
        }
        System.out.println("Current size of stack is "+ stack.size()+".");
    }

    public static void contains(Stack stack) {
        if(stack.isEmpty()) {
            System.out.println("Stack is empty please push element first");
            return;
        }
        System.out.println("Enter element to check if element is contained by stack.");
        Scanner sc = new Scanner(System.in);
        Integer element = sc.nextInt();
        if(stack.contains(element)) {
            System.out.println(element+ " is present in stack.");
        }
        else {
            System.out.println(element+" is not present in stack.");
        }
    }

    public static void peek(Stack stack) {
        if(stack.isEmpty()) {
            System.out.println("Stack is empty please push element first");
            return;
        }
        System.out.println(stack.peek()+" is peek element of stack.");
    }

    public static void pop(Stack stack) {
        if(stack.isEmpty()) {
            System.out.println("Stack is empty please push element first");
            return;
        }
        System.out.println(stack.pop()+" is popped from stack.");
    }

    public static void push(Stack stack) throws IOException {
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
            stack.push(Integer.parseInt(itemString));
        }
        System.out.println("Provided elements are pushed to stack.");
    }
}
