package com.codecool.seasonalproductdiscounter.service.logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileLogger extends LoggerBase {

    private String filePath;

    public FileLogger(String filePath) {
        this.filePath = filePath;
    }

    @Override
    protected void log(String message, String type) {
        String logEntry = createLogEntry( message, type);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(logEntry);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error writing to log file: " + e.getMessage());
        }
    }
}