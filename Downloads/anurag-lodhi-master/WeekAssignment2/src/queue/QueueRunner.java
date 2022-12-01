package queue;

import input.InputValidator;
import queue.service.Queue;
import queue.utilities.QueueOperations;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class QueueRunner {
    public static void main(String args[]) throws IOException {
        Queue queue = new Queue();
        System.out.println("Hi!! Welcome to Queues");
        System.out.println("We provide following operations on queue:");
        System.out.println("1. enQueue \n2. deQueue \n3. Peek \n4. Contains \n5. Size \n6. Center \n7. Sort \n8. Reverse \n9. Print");
        String str;
        do{
            Integer operationNumber = InputValidator.validateNumber(9);
            switch (operationNumber) {
                case 1 :
                    QueueOperations.enQueue(queue);
                    break;
                case 2 :
                    QueueOperations.deQueue(queue);
                    break;
                case 3 :
                    QueueOperations.peek(queue);
                    break;
                case 4 :
                    QueueOperations.contains(queue);
                    break;
                case 5 :
                    QueueOperations.size(queue);
                    break;
                case 6 :
                    QueueOperations.center(queue);
                    break;
                case 7 :
                    QueueOperations.sort(queue);
                    break;
                case 8 :
                    QueueOperations.reverse(queue);
                    break;
                case 9:
                    QueueOperations.print(queue);
                    break;
            }
            System.out.println("Do you want to perform other operation ? (y/n)");
            Scanner sc = new Scanner(System.in);
            str = sc.nextLine();
        }while(Objects.equals(str, "y"));
    }


}
