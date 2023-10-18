package linkedlist;

import input.InputValidator;
import linkedlist.service.LinkedList;
import linkedlist.utilities.LinkedListOperations;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class LinkedListRunner {
    public static void main(String args[]) throws IOException {
        LinkedList list = new LinkedList();

        System.out.println("Hi!! Welcome to Linked List");
        System.out.println("We provide following operations on linked list:");
        System.out.println("1. Insert \n2. Insert at Pos \n3. Delete \n4. Delete at Pos \n5. Centre Node \n6. Sort \n7. Size \n8. Reverse \n9. Print");
        String str;
        do{
            Integer operationNumber = InputValidator.validateNumber(9);
            switch (operationNumber) {
                case 1 :
                    LinkedListOperations.insert(list);
                    break;
                case 2 :
                    LinkedListOperations.insertAtPos(list);
                    break;
                case 3 :
                    LinkedListOperations.delete(list);
                    break;
                case 4 :
                    LinkedListOperations.deleteAtPos(list);
                    break;
                case 5 :
                    LinkedListOperations.centreNode(list);
                    break;
                case 6 :
                    LinkedListOperations.sort(list);
                    break;
                case 7 :
                    LinkedListOperations.size(list);
                    break;
                case 8 :
                    LinkedListOperations.reverse(list);
                    break;
                case 9:
                    LinkedListOperations.print(list);
                    break;
            }
            System.out.println("Do you want to perform other operation ? (y/n)");
            Scanner sc = new Scanner(System.in);
            str = sc.nextLine();
        }while(Objects.equals(str, "y"));
    }


}
