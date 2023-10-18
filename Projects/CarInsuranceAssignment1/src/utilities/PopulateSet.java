package utilities;

import constant.CarTypeConstant;
import constant.EntityConstant;
import constant.InsuranceTypeConstant;

import java.util.HashSet;

public class PopulateSet {
    public static HashSet<String> entity () {
        HashSet <String> entitySet = new HashSet<String>();
        entitySet.add(EntityConstant.CAR_PRICE);
        entitySet.add(EntityConstant.CAR_TYPE);
        entitySet.add(EntityConstant.INSURANCE_TYPE);
        return entitySet;
    }

    public static HashSet<String> car () {
        HashSet <String> carSet = new HashSet<String>();
        carSet.add(CarTypeConstant.SEDAN_CARTYPE_CONSTANT);
        carSet.add(CarTypeConstant.SUV_CARTYPE_CONSTANT);
        carSet.add(CarTypeConstant.HATCHBACK_CARTYPE_CONSTANT);
        return carSet;
    }

    public static HashSet<String> insurance () {
        HashSet <String> insuranceSet = new HashSet<String>();
        insuranceSet.add(InsuranceTypeConstant.BASIC_INSURANCE_CONSTANT);
        insuranceSet.add(InsuranceTypeConstant.PREMIUM_INSURANCE_CONSTANT);
        return insuranceSet;
    }
}
