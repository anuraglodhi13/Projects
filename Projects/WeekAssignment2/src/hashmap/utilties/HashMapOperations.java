package hashmap.utilties;

import hashmap.service.HashMap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class HashMapOperations {
    public static void print(HashMap map) {
        if(map.isEmpty()) {
            System.out.println("Hash Map is empty please insert element first");
            return;
        }
        System.out.println("Elements present in hash map are: ");
        map.traverse();
        System.out.println();

    }

    public static void size(HashMap map) {
        if(map.isEmpty()) {
            System.out.println("Hash Map is empty please insert element first");
            return;
        }
        System.out.println("Current size of hash map is "+ map.size()+".");
    }

    public static void contains(HashMap map) {
        if(map.isEmpty()) {
            System.out.println("Hash Map is empty please insert element first");
            return;
        }
        System.out.println("Enter key");
        Scanner sc = new Scanner(System.in);
        Integer element = sc.nextInt();
        if(map.contains(element)) {
            System.out.println(element+ " is present in hash map.");
        }
        else {
            System.out.println(element+" is not present in hash map.");
        }
    }
    public static void getValueByKey(HashMap map) {
        if(map.isEmpty()) {
            System.out.println("Hash Map is empty please insert element first");
            return;
        }
        System.out.println("Enter key :");
        Scanner sc = new Scanner(System.in);
        Integer element = sc.nextInt();
        if(map.contains(element)) {
            System.out.println("Value is " + map.getValue(element));
        }
        else {
            System.out.println("Key is not in map please give key which is present in map.");
        }
    }
    public static void delete(HashMap map) {
        if(map.isEmpty()) {
            System.out.println("Hash Map is empty please insert element first");
            return;
        }
        System.out.println("Enter key to be deleted from map");
        Scanner sc = new Scanner(System.in);
        Integer key = sc.nextInt();
        map.delete(key);
        System.out.println("Key deleted successfully");
        if(!map.isEmpty()) {
            System.out.println("Key-Values present in map are : ");
            map.traverse();
            System.out.println();
        }
    }

    public static void insert(HashMap map) throws IOException {
        String lines;
        System.out.println("Enter key-values separated with white space");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        lines = br.readLine();
        while(lines.equals("")){
            System.out.println("You haven't enter anything!!!");
            System.out.println("Enter elements separated with white space");
            lines = br.readLine();
        }
        String[] itemStrings = lines.trim().split("\\s+");
        for (String itemString : itemStrings) {
            String[] result = itemString.split("-");
            //System.out.println(result[0]+result[1]);
            map.insert(Integer.parseInt(result[0]),Integer.parseInt(result[1]));
        }
        System.out.println("Provided elements are inserted to Hash Map.");

    }
}
