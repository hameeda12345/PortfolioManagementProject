
package com.hospitalmanagement.service;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonFileService {

    private static final Logger logger =
            LoggerFactory.getLogger(JsonFileService.class);

    private ObjectMapper mapper;

    public JsonFileService() {
        mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        logger.info("JSON File Service initialized.");
    }

    // Save Data
    public void saveData(Object data, String fileName) {

        try {
            mapper.writeValue(new File(fileName), data);

            logger.info("Data saved successfully to file: {}",
                    fileName);

            System.out.println("Data saved successfully!");

        } catch (IOException e) {

            logger.error("Error saving data to file: {}",
                    fileName, e);

            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    // Load Data using Class
    public <T> T loadData(String fileName, Class<T> type) {

        try {
            T data = mapper.readValue(new File(fileName), type);

            logger.info("Data loaded successfully from file: {}",
                    fileName);

            return data;

        } catch (IOException e) {

            logger.error("Error loading data from file: {}",
                    fileName, e);

            System.out.println("Error loading data: " + e.getMessage());

            return null;
        }
    }

    // Load Data using TypeReference
    public <T> T loadData(String fileName, TypeReference<T> type) {

        try {
            T data = mapper.readValue(new File(fileName), type);

            logger.info("Data loaded successfully from file: {}",
                    fileName);

            return data;

        } catch (IOException e) {

            logger.error("Error loading data from file: {}",
                    fileName, e);

            System.out.println("Error loading data: " + e.getMessage());

            return null;
        }
    }
}
