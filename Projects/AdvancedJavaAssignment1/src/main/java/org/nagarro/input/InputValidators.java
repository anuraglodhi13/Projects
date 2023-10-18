package org.nagarro.input;


import org.nagarro.constant.Constants;

import java.util.HashSet;

public class InputValidators {
    public static boolean isStringNull(String input) {
        if (input == null || input.length() == 0 ) {
            return true;
        }
        return false;
    }

    public static boolean validateColour(String input) {

        if(isStringNull(input)) {
            System.out.println(Constants.COLOUR_VALIDATION_NULL_MESSAGE);
            return true;
        }

        if(isNumber(input)) {
            System.out.println(Constants.COLOUR_VALIDATION_NUMBER_MESSAGE);
            return true;
        }
        return false;
    }

    public static boolean validateSize(String input) {
        if(isStringNull(input)) {
            System.out.println(Constants.SIZE_VALIDATION_NULL_MESSAGE);
            return true;
        }

        if(isNumber(input)) {
            System.out.println(Constants.SIZE_VALIDATION_NUMBER_MESSAGE);
            return true;
        }
        return false;
    }

    public static boolean validateGender(String input) {
        if(isStringNull(input)) {
            System.out.println(Constants.GENDER_VALIDATION_NULL_MESSAGE);
            return true;
        }

        if(isNumber(input)) {
            System.out.println(Constants.GENDER_VALIDATION_NUMBER_MESSAGE);
            return true;
        }
        HashSet<String> gender = new HashSet<>();
        gender.add(Constants.MALE_GENDER);
        gender.add(Constants.FEMALE_GENDER);
        gender.add(Constants.UNISEX_GENDER);
        gender.add("m");
        gender.add("f");
        gender.add("u");
        if(!gender.contains(input)) {
            System.out.println(Constants.GENDER_VALIDATION);
            return true;
        }
        return false;
    }

    public static boolean validateOutputPreference(String input) {
        if(isStringNull(input)) {
            System.out.println(Constants.PREFERENCE_VALIDATION_NULL_MESSAGE);
            return true;
        }

        if(!isNumber(input)) {
            System.out.println(Constants.PREFERENCE_VALIDATION_NUMBER_MESSAGE);
            return true;
        }
        else {
            Integer numberInput = Integer.valueOf(input);
            HashSet<Integer> numberSet = new HashSet<>();
            numberSet.add(Constants.RATING_SORTED_VALUE);
            numberSet.add(Constants.PRICE_SORTED_VALUE);
            numberSet.add(3);
            if(!numberSet.contains(numberInput)) {
                System.out.println(Constants.PREFERENCE_VALIDATION_NUMBER_MESSAGE);
                return true;
            }
        }
        return false;
    }

    private static boolean isNumber(String input) {
        return input.chars().allMatch( Character::isDigit );
    }
}
