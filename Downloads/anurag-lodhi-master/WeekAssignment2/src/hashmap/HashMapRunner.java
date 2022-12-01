package hashmap;

import hashmap.service.HashMap;
import hashmap.utilties.HashMapOperations;
import input.InputValidator;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class HashMapRunner {
    public static void main(String args[]) throws IOException {
        HashMap map = new HashMap();
        System.out.println("Hi!! Welcome to HashMaps");
        System.out.println("We provide following operations on HashMaps: ");
        System.out.println("1. Insert \n2. Delete \n3. Contains \n4. GetValueByKey \n5. Size \n6. Print");
        String str;
        do{
            Integer operationNumber = InputValidator.validateNumber(6);
            switch (operationNumber) {
                case 1 :
                    HashMapOperations.insert(map);
                    break;
                case 2 :
                    HashMapOperations.delete(map);
                    break;
                case 3 :
                    HashMapOperations.contains(map);
                    break;
                case 4 :
                    HashMapOperations.getValueByKey(map);
                    break;
                case 5 :
                    HashMapOperations.size(map);
                    break;
                case 6:
                    HashMapOperations.print(map);
                    break;
            }
            System.out.println("Do you want to perform other operation ? (y/n)");
            Scanner sc = new Scanner(System.in);
            str = sc.nextLine();
        }while(Objects.equals(str, "y"));
    }


}
