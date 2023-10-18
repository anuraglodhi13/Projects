package input;

import constant.EntityConstant;
import exception.InvalidInputException;
import utilities.PopulateSet;

import java.util.HashSet;

public class InputValidator {
    public void validateInput(String [] carInfo) throws InvalidInputException {
        validateCarPrice(carInfo[2]);
        validateCarType(carInfo[1]);
        validateInsuranceType(carInfo[3]);
    }
    private void validateCarPrice(String carPrice) throws InvalidInputException {
        try
        {
            Integer price = Integer.parseInt(carPrice);
            if(price.equals(0)) {
                throw new InvalidInputException(carPrice,EntityConstant.CAR_PRICE);
            }
        }
        catch (NumberFormatException e)
        {
            throw new InvalidInputException(carPrice,EntityConstant.CAR_PRICE);
        }
    }

    private void validateCarType(String carType) throws InvalidInputException {
        HashSet <String> carSet = PopulateSet.car();
        if(!carSet.contains(carType)) {
            throw new InvalidInputException(carType, EntityConstant.CAR_TYPE);
        }
    }

    private void validateInsuranceType(String insuranceType) throws InvalidInputException {
        HashSet <String> insuranceSet = PopulateSet.insurance();
        if(!insuranceSet.contains(insuranceType)) {
            throw new InvalidInputException(insuranceType,EntityConstant.INSURANCE_TYPE);
        }
    }
}
