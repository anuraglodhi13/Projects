package com.nagarro.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServlet;

import com.nagarro.constants.Constants;
import com.nagarro.dao.TshirtDao;
import com.nagarro.model.TshirtData;

public class ProcessCsvFileController extends HttpServlet {
	private Map<String, FileTime> csvFilesWithTime;

    // to store the file names which are updated or newly added in folder
    private List<String> newFiles;
	public void init() {
		new Thread(new Runnable() {
            @Override
            public void run() {
            	csvFilesWithTime = new HashMap<>();
            	while (true) {
                    try {
                    	searchCsvDirectory();
                        Thread.sleep(10*1000);
                    } catch (InterruptedException | IOException e) {
                        throw new RuntimeException(e);
                    } catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
                }
            }
        }).start();
	}
	public void searchCsvDirectory() throws IOException, IllegalAccessException {
        String directoryPath = Constants.CURR_WORKING_DIR + Constants.FOLDER_NAME;
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
            
            addCSVFileData();
        }
    }
    private String getExtension(final String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    private void addCSVFileData () throws IOException, IllegalAccessException {
        TshirtDao tshirtDao = null;
        for (String filename : newFiles) {
            Map<String, Set<TshirtData>> tshirtData = readDataFromCsvFile(filename);
            tshirtDao = new TshirtDao();

            for (Map.Entry<String, Set<TshirtData>> tshirt : tshirtData
                    .entrySet()) {
            	
                tshirtDao.insertTshirtInDb(tshirt.getValue());
            
            }
        }
    }
    public Map<String, Set<TshirtData>> readDataFromCsvFile(String csvFile) throws IOException {
        String directoryPath = Constants.CURR_WORKING_DIR + Constants.FOLDER_NAME;
        csvFile = directoryPath+"/"+csvFile;
        Map<String, Set<TshirtData>> tshirtData = new HashMap<>();

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
            TshirtData tshirt = new TshirtData();
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
                tshirtData.put(keyForSearch,new HashSet<TshirtData>());
            }

            tshirtData.get(keyForSearch).add(tshirt);
        }

        return tshirtData;
    }
}
