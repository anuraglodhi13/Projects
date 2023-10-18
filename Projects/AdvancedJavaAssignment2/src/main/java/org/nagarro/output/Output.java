package org.nagarro.output;

import org.nagarro.constant.Constants;
import org.nagarro.model.Tshirt;
import org.nagarro.model.TshirtDTO;

import java.util.Collections;
import java.util.List;

public class Output {
    public static void displayOutput (List<Tshirt> searchedShirts, TshirtDTO tshirtDTO) {
        if(searchedShirts == null || searchedShirts.size() == 0) {
            System.out.println(Constants.GENERAL_ERROR_MESSAGE);
        }
        else {
            String outputPreference = tshirtDTO.getOutputPreference();
            if (outputPreference.equals("1")) {
                Collections.sort(searchedShirts, Tshirt.ratingSorter);
            } else if (outputPreference.equals("2")) {
                Collections.sort(searchedShirts, Tshirt.priceSorter);
            } else if (outputPreference.equals("3")) {
                Collections.sort(searchedShirts, Tshirt.priceAndRatingSorter);
            }

            System.out.println(Constants.DASHED_LINES);
            System.out.printf(Constants.HEADER_STRING,
                    Constants.ENTITY_ID, Constants.ENTITY_NAME, Constants.ENTITY_COLOUR,
                    Constants.ENTITY_GENDER_RECOMMENDATION, Constants.ENTITY_SIZE,
                    Constants.ENTITY_PRICE, Constants.ENTITY_RATING, Constants.ENTITY_AVAILABILITY);
            System.out.println();
            System.out.println(Constants.DASHED_LINES);

            for (Tshirt tshirt : searchedShirts) {
                System.out.format(Constants.HEADER_STRING,
                        tshirt.getId(), tshirt.getName(), tshirt.getColor(),
                        tshirt.getGenderRecommendation(), tshirt.getSize(), tshirt.getPrice(), tshirt.getRating(),
                        tshirt.getAvailability());
                System.out.println();
            }
            System.out.println();
        }
    }
}
