package com.example.fuelstationspringbootapplication.util;

import org.springframework.stereotype.Component;

import java.io.File;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Component
public class FileHandler {

    public String createDownloadLink(String filePath) {
        // Assuming the file can be opened by the operating system
        return "file://" + filePath;
    }
    public String getCreationTime(File file) {
        LocalDateTime creationDateTime = LocalDateTime.ofInstant(
                Instant.ofEpochMilli(file.lastModified()),
                ZoneId.systemDefault()
        );
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return creationDateTime.format(formatter);
    }
}
