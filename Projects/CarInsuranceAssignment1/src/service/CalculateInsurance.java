package service;

import constant.CarTypeConstant;
import constant.InsuranceTypeConstant;
import constant.PremiumConstant;
import model.Car;
import model.Insurance;

public class CalculateInsurance {

        public Double insuranceCalculator (Car car, Insurance insurance) {
                String carType = car.getType();
                if (carType.equals(CarTypeConstant.HATCHBACK_CARTYPE_CONSTANT)) {
                        return getHatchbackPremium(car.getCostPrice(),insurance.getType());
                }
                else if(carType.equals(CarTypeConstant.SEDAN_CARTYPE_CONSTANT)) {
                        return getSedanPremium(car.getCostPrice(),insurance.getType());
                }
                else {
                        return getSuvPremium(car.getCostPrice(),insurance.getType());
                }
        }

        private Double getHatchbackPremium (Integer carPrice, String insuranceType) {
                Double premium = 0.00;
                if(insuranceType.equals(InsuranceTypeConstant.BASIC_INSURANCE_CONSTANT)) {
                      premium = premiumCalculate(PremiumConstant.HATCHBACK_PREMIUM_CONSTANT,carPrice);
                }
                else if (insuranceType.equals(InsuranceTypeConstant.PREMIUM_INSURANCE_CONSTANT)) {
                        Double basicPremium = premiumCalculate(PremiumConstant.HATCHBACK_PREMIUM_CONSTANT,carPrice);
                        premium = increasedPremiumCalculate(basicPremium);

                }
                return premium;
        }

        private Double getSedanPremium (Integer carPrice, String insuranceType) {
                Double premium = 0.00;
                if(insuranceType.equals(InsuranceTypeConstant.BASIC_INSURANCE_CONSTANT)) {
                        premium = premiumCalculate(PremiumConstant.SEDAN_PREMIUM_CONSTANT,carPrice);
                }
                else if (insuranceType.equals(InsuranceTypeConstant.PREMIUM_INSURANCE_CONSTANT)) {
                        Double basicPremium = premiumCalculate(PremiumConstant.SEDAN_PREMIUM_CONSTANT,carPrice);
                        premium = increasedPremiumCalculate(basicPremium);

                }
                return premium;
        }

        private Double getSuvPremium (Integer carPrice, String insuranceType) {
                Double premium = 0.00;
                if(insuranceType.equals(InsuranceTypeConstant.BASIC_INSURANCE_CONSTANT)) {
                        premium = premiumCalculate(PremiumConstant.SUV_PREMIUM_CONSTANT,carPrice);
                }
                else if (insuranceType.equals(InsuranceTypeConstant.PREMIUM_INSURANCE_CONSTANT)) {
                        Double basicPremium = premiumCalculate(PremiumConstant.SUV_PREMIUM_CONSTANT,carPrice);
                        premium = increasedPremiumCalculate(basicPremium);

                }
                return premium;
        }

        private Double premiumCalculate(Integer value, Integer carPrice) {
                return (carPrice*value) / 100.00;
        }
        private Double increasedPremiumCalculate (Double basicPremium) {
                return basicPremium + ( basicPremium * PremiumConstant.INCREASED_PREMIUM_CONSTANT ) / 100.00;
        }
}