package org.nagarro.service;

public class CsvFiles {

    public void initiateThreadClass() {
        ProcessCsvFile csvFileHandler = new ProcessCsvFile();
        Thread t1 = new Thread(csvFileHandler);
        t1.start();
    }
}
