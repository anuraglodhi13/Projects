package org.nagarro.service;

import org.nagarro.model.Tshirt;
import org.nagarro.model.TshirtDTO;

import java.util.List;

public class SearchingCsvData {
    public List<Tshirt> searchTshirt(TshirtDTO tshirtDTO) {
        MappingService singletonMappingService = MappingService.getInstance();
        String tshirtSearchKey = (tshirtDTO.getColor()+tshirtDTO.getSize()+tshirtDTO.getGenderRecommendation()).toUpperCase();
        List<Tshirt> shirts = singletonMappingService.getShirts(tshirtSearchKey);

        return shirts;
    }

}
