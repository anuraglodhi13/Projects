package org.nagarro.service;

import org.nagarro.dao.TshirtDao;
import org.nagarro.model.Tshirt;
import org.nagarro.model.TshirtDTO;

import java.util.ArrayList;
import java.util.List;

public class TshirtSearchInDb {
    public List<Tshirt> searchTshirt(TshirtDTO tshirtDTO) {
        TshirtDao tshirtDao = new TshirtDao();
        List <Tshirt> searchedShirts = new ArrayList<>();
        List<Tshirt> shirts = tshirtDao.getTshirts();

        for(Tshirt shirt : shirts) {
            if(shirt.getColor().equals(tshirtDTO.getColor())) {
                if(shirt.getGenderRecommendation().equals(tshirtDTO.getGenderRecommendation())) {
                    if(shirt.getSize().equals(tshirtDTO.getSize())) {
                        searchedShirts.add(shirt);
                    }
                }
            }
        }

        return searchedShirts;
    }

}
