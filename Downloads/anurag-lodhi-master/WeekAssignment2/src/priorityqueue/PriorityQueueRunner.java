package priorityqueue;

import input.InputValidator;
import priorityqueue.service.PriorityQueue;
import priorityqueue.utilities.PriorityQueueOperations;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class PriorityQueueRunner {

    public static void main(String args[]) throws IOException {
        PriorityQueue queue = new PriorityQueue();
        System.out.println("Hi!! Welcome to PriorityQueues");
        System.out.println("We provide following operations on queue:");
        System.out.println("1. enQueue \n2. deQueue \n3. Peek \n4. Contains \n5. Size \n6. Center \n7. Reverse \n8. Print");
        String str;
        do{
            Integer operationNumber = InputValidator.validateNumber(8);
            switch (operationNumber) {
                case 1 :
                    PriorityQueueOperations.enQueue(queue);
                    break;
                case 2 :
                    PriorityQueueOperations.deQueue(queue);
                    break;
                case 3 :
                    PriorityQueueOperations.peek(queue);
                    break;
                case 4 :
                    PriorityQueueOperations.contains(queue);
                    break;
                case 5 :
                    PriorityQueueOperations.size(queue);
                    break;
                case 6 :
                    PriorityQueueOperations.center(queue);
                    break;
                case 7 :
                    PriorityQueueOperations.reverse(queue);
                    break;
                case 8:
                    PriorityQueueOperations.print(queue);
                    break;
            }
            System.out.println("Do you want to perform other operation ? (y/n)");
            Scanner sc = new Scanner(System.in);
            str = sc.nextLine();
        }while(Objects.equals(str, "y"));
    }

}
