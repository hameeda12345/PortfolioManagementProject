
package com.hospitalmanagement.service;

import java.util.HashMap;
import java.util.Map;

import com.hospitalmanagement.model.Doctor;

public class DoctorService {

    private Map<Integer, Doctor> doctors = new HashMap<>();
    private JsonFileService jsonService = new JsonFileService();
    private final String FILE_NAME = "data/doctors.json";

    // Add doctor
    public void addDoctor(Doctor doctor) {

        if (doctors.containsKey(doctor.getDoctorId())) {
            System.out.println("Doctor ID already exists.");
            return;
        }

        doctors.put(doctor.getDoctorId(), doctor);
        System.out.println("Doctor added successfully.");
    }

    // Find doctor by ID
    public Doctor findDoctor(int doctorId) {
        return doctors.get(doctorId);
    }

    // Display all doctors
    public void displayDoctors() {

        if (doctors.isEmpty()) {
            System.out.println("No doctors available.");
            return;
        }

        for (Doctor doctor : doctors.values()) {
            System.out.println(doctor);
        }
    }

    // Save doctors to JSON
    public void saveDoctors() {
        jsonService.saveData(doctors, FILE_NAME);
    }

    // Load doctors from JSON
    public void loadDoctors() {

        Map<?, ?> loadedDoctors =
                jsonService.loadData(FILE_NAME, Map.class);

        if (loadedDoctors != null) {
            doctors.clear();

            for (Map.Entry<?, ?> entry : loadedDoctors.entrySet()) {
                Doctor doctor = jsonService.loadData(
                        FILE_NAME, Doctor.class);

                // Loading a map of doctors requires proper
                // generic type handling; see note below.
                break;
            }
        }
    }

    public Map<Integer, Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(Map<Integer, Doctor> doctors) {
        this.doctors = doctors;
    }
}