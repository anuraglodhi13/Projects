package input;

import java.util.Scanner;

public class InputValidator {
    //This method is used to validate if given input is in given range
    public static Integer validateNumber(Integer numberOfOperation) {
        Integer operationNumber;
        do{
            System.out.println("Enter number(1-"+numberOfOperation+") to perform the operation");
            Scanner sc = new Scanner(System.in);
            operationNumber = sc.nextInt();

        }while(operationNumber < 1 || operationNumber > numberOfOperation);
        return operationNumber;
    }
}
