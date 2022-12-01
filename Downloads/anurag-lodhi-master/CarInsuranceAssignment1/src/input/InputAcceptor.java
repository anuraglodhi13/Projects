package input;

import constant.CarTypeConstant;
import constant.EntityConstant;
import constant.InsuranceTypeConstant;
import exception.InvalidInputException;
import utilities.PopulateSet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Scanner;

public class InputAcceptor {
    public static String[] enterInput() throws IOException {
        String exceptionMessage;
        String entity;

        System.out.println("Please enter your car model, car type, car cost price and insurance type separated by white space");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String lines = br.readLine();
        String[] itemStrings = lines.trim().split("\\s+");

        exceptionMessage = validateInput(itemStrings);
        if(!exceptionMessage.equals(" ")) {
            System.out.println(exceptionMessage);
        }
        entity = checkWhichEntityIsWrong(exceptionMessage);

        if(entity.equals(EntityConstant.CAR_PRICE)) {
            takeInputUntilCarPriceIsWrong(entity,itemStrings);
        }

        exceptionMessage = validateInput(itemStrings);

        entity =  checkWhichEntityIsWrong(exceptionMessage);
        if(entity.equals(EntityConstant.CAR_TYPE)) {
            takeInputUntilCarTypeIsWrong(entity,itemStrings);
        }

        exceptionMessage = validateInput(itemStrings);

        entity =  checkWhichEntityIsWrong(exceptionMessage);
        if(entity.equals(EntityConstant.INSURANCE_TYPE)) {
            takeInputUntilInsuranceTypeIsWrong(entity,itemStrings);
        }

        return itemStrings;
    }
    private static String validateInput(String[] itemStrings) {
        try {
            InputValidator inputValidator = new InputValidator();
            inputValidator.validateInput(itemStrings);
        }
        catch (InvalidInputException e) {
            return e.getMessage();
        }
        return " ";
    }
    private static String checkWhichEntityIsWrong(String str) {
        if(str.equals(" ")) return " ";
        String[] itemStrings = str.trim().split("\\s+");
        HashSet <String> entitySet = PopulateSet.entity();
        String inputEntity = itemStrings[2];
        inputEntity += " ";
        inputEntity += itemStrings[3];
        if(entitySet.contains(inputEntity)) {
            return inputEntity;
        }
        return " ";
    }

    private static void takeInputUntilCarPriceIsWrong(String entity, String[] itemStrings) {
        while(entity.equals(EntityConstant.CAR_PRICE)) {
            System.out.println("Please enter valid car price it can't be 0");
            Scanner sc= new Scanner(System.in);
            String c = sc.nextLine();
            itemStrings[2] = c;
            entity = checkWhichEntityIsWrong(validateInput(itemStrings));
        }
    }

    private static void takeInputUntilCarTypeIsWrong (String entity, String[] itemStrings) {
        while(entity.equals(EntityConstant.CAR_TYPE)) {
            System.out.println("Please enter valid car type it can be only " + CarTypeConstant.HATCHBACK_CARTYPE_CONSTANT
                    + " or " + CarTypeConstant.SEDAN_CARTYPE_CONSTANT + " or " + CarTypeConstant.SUV_CARTYPE_CONSTANT);
            Scanner sc= new Scanner(System.in);
            String c = sc.nextLine();
            itemStrings[1] = c;
            entity = checkWhichEntityIsWrong(validateInput(itemStrings));
        }
    }
    private static void takeInputUntilInsuranceTypeIsWrong (String entity, String[] itemStrings) {
        while(entity.equals(EntityConstant.INSURANCE_TYPE)) {
            System.out.println("PLease enter valid insurance type it can be only " + InsuranceTypeConstant.BASIC_INSURANCE_CONSTANT
                    + " or " + InsuranceTypeConstant.PREMIUM_INSURANCE_CONSTANT);
            Scanner sc= new Scanner(System.in);
            String c = sc.nextLine();
            itemStrings[3] = c;
            entity = checkWhichEntityIsWrong(validateInput(itemStrings));
        }
    }
}
