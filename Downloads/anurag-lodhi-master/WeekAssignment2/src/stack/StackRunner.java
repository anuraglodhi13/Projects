package stack;

import input.InputValidator;
import stack.service.Stack;
import stack.utilities.StackOperations;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class StackRunner {
    public static void main(String args[]) throws IOException {
        Stack stack = new Stack();
        System.out.println("Hi!! Welcome to stacks");
        System.out.println("We provide following operations on stack:");
        System.out.println("1. Push \n2. Pop \n3. Peek \n4. Contains \n5. Size \n6. Center \n7. Sort \n8. Reverse \n9. Print");
        String str;
        do{
            Integer operationNumber = InputValidator.validateNumber(9);
            switch (operationNumber) {
                case 1 :
                    StackOperations.push(stack);
                    break;
                case 2 :
                    StackOperations.pop(stack);
                    break;
                case 3 :
                    StackOperations.peek(stack);
                    break;
                case 4 :
                    StackOperations.contains(stack);
                    break;
                case 5 :
                    StackOperations.size(stack);
                    break;
                case 6 :
                    StackOperations.center(stack);
                    break;
                case 7 :
                    StackOperations.sort(stack);
                    break;
                case 8 :
                    StackOperations.reverse(stack);
                    break;
                case 9:
                    StackOperations.print(stack);
                    break;
            }
            System.out.println("Do you want to perform other operation ? (y/n)");
            Scanner sc = new Scanner(System.in);
            str = sc.nextLine();
        }while(Objects.equals(str, "y"));
}


}
