package org.nagarro.service;

import org.nagarro.model.Tshirt;

import java.util.*;

public class MappingService {

    private static MappingService singletonMappingServiceInstance;
    private Map<String, Map<String, Set<Tshirt>>> tshirtDataCollection =
            new HashMap<String, Map<String, Set<Tshirt>>>();

    public static synchronized MappingService getInstance() {
        if (null == singletonMappingServiceInstance) {
            singletonMappingServiceInstance = new MappingService();
        }
        return singletonMappingServiceInstance;
    }

    public void insertDataOfCsvFile(String filename, Map<String, Set<Tshirt>> tshirtData){
        if(filename!=null && tshirtData!= null){
            tshirtDataCollection.put(filename, tshirtData);
        }
    }

    public List<Tshirt> getShirts(String tshirtKeyForSearch){
        List<Tshirt> searchedTshirt = new ArrayList<>();

        for (Map.Entry<String, Map<String, Set<Tshirt>>> localMap : tshirtDataCollection.entrySet()) {
            for (Map.Entry<String, Set<Tshirt>> innerMap : localMap.getValue().entrySet()) {

                if (innerMap.getKey().equals(tshirtKeyForSearch)) {
                    searchedTshirt.addAll(innerMap.getValue());
                }
            }
        }
        return searchedTshirt;
    }
}
