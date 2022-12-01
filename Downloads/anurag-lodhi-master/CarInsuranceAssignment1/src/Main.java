import exception.InvalidInputException;
import input.InputAcceptor;
import model.Car;
import model.Insurance;
import service.CalculateInsurance;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InvalidInputException {
        System.out.println("Hi there, Welcome To Car Insurance App");
        ArrayList <String[]> carList = new ArrayList<String[]>();
        String str;
        do {
            String strings[] = InputAcceptor.enterInput();
            carList.add(strings);
            System.out.println("Do you want to enter details of any other car (y/n)");
            Scanner sc= new Scanner(System.in);
            str= sc.nextLine();
        }while(Objects.equals(str, "y"));



        for(int i = 0; i < carList.size(); i++) {
                String [] carInfo = carList.get(i);
                String carModel, carType, insuranceType;
                Integer carPrice;
                carModel = carInfo[0];
                carType = carInfo[1];
                carPrice = Integer.parseInt(carInfo[2]);
                insuranceType = carInfo[3];
                Car car = new Car(carModel, carType, carPrice);
                Insurance insurance = new Insurance(insuranceType);
                CalculateInsurance calculateInsurance = new CalculateInsurance();
                insurance.setValue(calculateInsurance.insuranceCalculator(car,insurance));
                System.out.println("Total " + insuranceType + " Insurance for " + carModel + " " + carType + " with price " + carPrice + " will be " + insurance.getValue());
        }
    }
}