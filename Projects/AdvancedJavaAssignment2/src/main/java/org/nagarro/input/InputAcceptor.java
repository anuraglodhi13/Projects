package org.nagarro.input;

import org.nagarro.constant.Constants;
import org.nagarro.model.TshirtDTO;
import org.nagarro.output.Output;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class InputAcceptor {

    public static String[] enterInput() throws IOException {

            Boolean validate = true;
            System.out.println(Constants.INPUT_MESSAGE);
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String lines = br.readLine();
            String[] tempStrings = lines.trim().split("\\s+");
            String[] itemStrings = Arrays.copyOf(tempStrings, 4);
            do{
                validate = InputValidators.validateColour(itemStrings[0]);
                if(validate) {
                    itemStrings[0] = br.readLine();
                }
            }while(validate);

            do{
                validate = InputValidators.validateSize(itemStrings[1]);
                if(validate) {
                    itemStrings[1] = br.readLine();
                }
            } while(validate);

            do{
                validate = InputValidators.validateGender(itemStrings[2]);
                if(validate) {
                    itemStrings[2] = br.readLine();
                }
            } while(validate);

            do{
                validate = InputValidators.validateOutputPreference(itemStrings[3]);
                if(validate) {
                    itemStrings[3] = br.readLine();
                }
            } while(validate);

            return itemStrings;
    }

}
