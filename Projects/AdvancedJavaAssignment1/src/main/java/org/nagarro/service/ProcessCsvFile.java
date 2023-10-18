package org.nagarro.service;

import org.nagarro.constant.Constants;
import org.nagarro.model.Tshirt;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.*;

public class ProcessCsvFile implements Runnable{
    private Map<String, FileTime> csvFilesWithTime;

    // to store the file names which are updated or newly added in folder
    private List<String> newFiles;
    @Override
    public void run() {

        csvFilesWithTime = new HashMap<>();
        while (true) {
            try {
                searchCsvDirectory();
                Thread.sleep(5*1000);
            } catch (InterruptedException | IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void searchCsvDirectory() throws IOException {
        String currentWorkingDir = System.getProperty(Constants.USER_DIRECTORY);
        String directoryPath = currentWorkingDir + Constants.FOLDER_NAME;
        File file = new File(directoryPath);
        String[] nameOfFiles = file.list();
        List<String> newFiles = new ArrayList<>();

        for (String nameOfFile : nameOfFiles) {

            if (getExtension(nameOfFile).equals(Constants.CSV_EXTENSION)) {
                if (!csvFilesWithTime.containsKey(nameOfFile)) {
                    csvFilesWithTime.put(nameOfFile, null);
                }

                Path path = Paths.get(directoryPath, nameOfFile);
                BasicFileAttributes csvBasicFileAttribute = Files.readAttributes(path,
                        BasicFileAttributes.class);

                if(csvFilesWithTime.get(nameOfFile) == null || !csvFilesWithTime.get(nameOfFile).equals(
                        csvBasicFileAttribute.lastModifiedTime())) {
                    newFiles.add(nameOfFile);
                    csvFilesWithTime.put(nameOfFile, csvBasicFileAttribute.lastModifiedTime());
                }
            }
        }
        this.newFiles = newFiles;
        if(newFiles.size() > 0 ) {
            // adding the data in map
            addCSVFileData();
        }
    }
    private String getExtension(final String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    private void addCSVFileData () throws IOException {
        for (String filename : newFiles) {
            Map<String, Set<Tshirt>> tshirtData = readDataFromCsvFile(filename);
            MappingService.getInstance().insertDataOfCsvFile(filename, tshirtData);
        }
    }
    public Map<String, Set<Tshirt>> readDataFromCsvFile(String csvFile) throws IOException {
        String currentWorkingDir = System.getProperty(Constants.USER_DIRECTORY);
        String directoryPath = currentWorkingDir + Constants.FOLDER_NAME;
        csvFile = directoryPath+"/"+csvFile;
        Map<String, Set<Tshirt>> tshirtData = new HashMap<>();

        BufferedReader reader;
        reader = new BufferedReader(new FileReader(csvFile));
        String inputLine = "";

        while ((inputLine = reader.readLine()) != null) {
            String data[] = inputLine.split("\\|");
            if(data[0].equals("ID")) continue;

            String id = data[0];
            String name = data[1];
            String color = data[2];
            String genderRecommendation = data[3];
            String size = data[4];
            String price = data[5];
            String rating = data[6];
            String availability = data[7];
            Tshirt tshirt = new Tshirt();
            tshirt.setId(id);
            tshirt.setName(name);
            tshirt.setColor(color);
            tshirt.setGenderRecommendation(genderRecommendation);
            tshirt.setSize(size);
            tshirt.setPrice(price);
            tshirt.setRating(rating);
            tshirt.setAvailability(availability);

            String keyForSearch = (color+size+genderRecommendation).toUpperCase();
            if(!tshirtData.containsKey(keyForSearch) ) {
                tshirtData.put(keyForSearch,new HashSet<Tshirt>());
            }

            tshirtData.get(keyForSearch).add(tshirt);
        }

        return tshirtData;
    }
}
