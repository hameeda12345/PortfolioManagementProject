
package com.hospitalmanagement.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hospitalmanagement.model.Doctor;
import com.hospitalmanagement.exception.DuplicateDoctorException;
import com.hospitalmanagement.exception.DoctorNotFoundException;

public class DoctorService {

    private static final Logger logger =
            LoggerFactory.getLogger(DoctorService.class);

    private Map<Integer, Doctor> doctors = new HashMap<>();

    // Add Doctor
    public void addDoctor(Doctor doctor)
            throws DuplicateDoctorException {

        if (doctors.containsKey(doctor.getDoctorId())) {

            logger.warn("Duplicate doctor ID: {}",
                    doctor.getDoctorId());

            throw new DuplicateDoctorException(
                    "Doctor already exists!");
        }

        doctors.put(doctor.getDoctorId(), doctor);

        logger.info("Doctor added successfully. Doctor ID: {}",
                doctor.getDoctorId());
    }

    // Display Doctors
    public void displayDoctors() {

        if (doctors.isEmpty()) {
            logger.warn("No doctor records available.");
            System.out.println("No doctors found!");
            return;
        }

        doctors.values().forEach(System.out::println);

        logger.info("Doctor records displayed. Total doctors: {}",
                doctors.size());
    }

    // Find Doctor
    public Doctor findDoctor(int doctorId)
            throws DoctorNotFoundException {

        Doctor doctor = doctors.get(doctorId);

        if (doctor == null) {

            logger.error("Doctor not found. Doctor ID: {}", doctorId);

            throw new DoctorNotFoundException(
                    "Doctor not found!");
        }

        logger.info("Doctor found. Doctor ID: {}", doctorId);

        return doctor;
    }

    // Get Doctors
    public Map<Integer, Doctor> getDoctors() {
        return doctors;
    }

    // Set Doctors
    public void setDoctors(Map<Integer, Doctor> doctors) {
        this.doctors = doctors;

        logger.info("Doctor records loaded. Total doctors: {}",
                doctors.size());
    }
}
