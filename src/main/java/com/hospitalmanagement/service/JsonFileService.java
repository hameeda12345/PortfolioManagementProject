
package com.hospitalmanagement.service;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JsonFileService {

    private final ObjectMapper mapper;

    public JsonFileService() {
        mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public void saveData(Object data, String fileName) {
        try {
            File file = new File(fileName);

            File parent = file.getParentFile();
            if (parent != null) {
                parent.mkdirs();
            }

            mapper.writeValue(file, data);

            System.out.println(
                    "Data saved successfully to "
                    + file.getAbsolutePath());

        } catch (IOException e) {
            System.out.println(
                    "Error while saving data: " + e.getMessage());
        }
    }

    public <T> T loadData(String fileName, Class<T> type) {
        try {
            return mapper.readValue(new File(fileName), type);
        } catch (IOException e) {
            System.out.println(
                    "Error while loading data: " + e.getMessage());
            return null;
        }
    }

    public <T> T loadData(
            String fileName, TypeReference<T> type) {
        try {
            return mapper.readValue(new File(fileName), type);
        } catch (IOException e) {
            System.out.println(
                    "Error while loading data: " + e.getMessage());
            return null;
        }
    }
}

