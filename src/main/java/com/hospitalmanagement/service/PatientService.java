
package com.hospitalmanagement.service;

import java.util.HashMap;
import java.util.Map;

import com.hospitalmanagement.model.Patient;

public class PatientService {

    private Map<Integer, Patient> patients = new HashMap<>();

    public void addPatient(Patient patient) {
        patients.put(patient.getPatientId(), patient);
        System.out.println("Patient added successfully.");
    }

    public void displayPatients() {
        if (patients.isEmpty()) {
            System.out.println("No patients found.");
            return;
        }

        System.out.println("\n===== PATIENT DETAILS =====");

        for (Patient patient : patients.values()) {
            System.out.println(patient);
        }
    }

    public Patient findPatient(int patientId) {
        return patients.get(patientId);
    }

    public Map<Integer, Patient> getPatients() {
        return patients;
    }

    public void setPatients(Map<Integer, Patient> patients) {
        this.patients = patients;
    }
}

