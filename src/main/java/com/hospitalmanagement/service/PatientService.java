
package com.hospitalmanagement.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Comparator;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hospitalmanagement.model.Patient;
import com.hospitalmanagement.exception.DuplicatePatientException;
import com.hospitalmanagement.exception.PatientNotFoundException;

public class PatientService {

    private static final Logger logger =
            LoggerFactory.getLogger(PatientService.class);

    private Map<Integer, Patient> patients = new HashMap<>();

    // Add Patient
    public void addPatient(Patient patient)
            throws DuplicatePatientException {

        if (patients.containsKey(patient.getPatientId())) {

            logger.warn("Duplicate patient ID: {}",
                    patient.getPatientId());

            throw new DuplicatePatientException(
                    "Patient already exists!");
        }

        patients.put(patient.getPatientId(), patient);

        logger.info("Patient added successfully. Patient ID: {}",
                patient.getPatientId());
    }

    // Display All Patients
    public void displayPatients() {

        if (patients.isEmpty()) {
            logger.warn("No patient records available.");
            System.out.println("No patients found!");
            return;
        }

        patients.values().forEach(System.out::println);

        logger.info("Patient records displayed. Total patients: {}",
                patients.size());
    }

    // Find Patient
    public Patient findPatient(int patientId)
            throws PatientNotFoundException {

        Patient patient = patients.get(patientId);

        if (patient == null) {

            logger.error("Patient not found. Patient ID: {}", patientId);

            throw new PatientNotFoundException(
                    "Patient not found!");
        }

        logger.info("Patient found. Patient ID: {}", patientId);

        return patient;
    }

    // Display Unique Diseases
    public void displayUniqueDiseases() {

        patients.values().stream()
                .map(Patient::getDisease)
                .distinct()
                .forEach(System.out::println);

        logger.info("Unique diseases displayed.");
    }

    // Filter Patients By Disease
    public void filterPatientsByDisease(String disease) {

        patients.values().stream()
                .filter(p -> p.getDisease().equalsIgnoreCase(disease))
                .forEach(System.out::println);

        logger.info("Patients filtered by disease: {}", disease);
    }

    // Sort Patients By Age
    public void sortPatientsByAge() {

        patients.values().stream()
                .sorted(Comparator.comparingInt(Patient::getAge))
                .forEach(System.out::println);

        logger.info("Patients sorted by age.");
    }

    // Display Patient Names
    public void displayPatientNames() {

        patients.values().stream()
                .map(Patient::getName)
                .forEach(System.out::println);

        logger.info("Patient names displayed.");
    }

    // Count Patients Using Stream API
    public long countPatientsUsingStream() {

        long count = patients.values().stream().count();

        System.out.println("Total number of patients: " + count);

        logger.info("Total patient count calculated: {}", count);

        return count;
    }

    // Get Patients
    public Map<Integer, Patient> getPatients() {
        return patients;
    }

    // Set Patients
    public void setPatients(Map<Integer, Patient> patients) {
        this.patients = patients;

        logger.info("Patient records loaded. Total patients: {}",
                patients.size());
    }
}
