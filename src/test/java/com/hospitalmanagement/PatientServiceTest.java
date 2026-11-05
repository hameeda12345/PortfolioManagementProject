
package com.hospitalmanagement;

import static org.junit.jupiter.api.Assertions.assertEquals; //junit test
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.hospitalmanagement.model.Patient;
import com.hospitalmanagement.service.PatientService;
import com.hospitalmanagement.exception.DuplicatePatientException;
import com.hospitalmanagement.exception.PatientNotFoundException;

public class PatientServiceTest {

    private PatientService patientService;

    // Create a fresh service before every test
    @BeforeEach
    public void setUp() {
        patientService = new PatientService();
    }

    // Test 1: Add a patient
    @Test
    public void testAddPatient() throws DuplicatePatientException {

        Patient patient = new Patient(
            101, "Hami", 19, "Female", "9876543210", "Headache"
        );

        patientService.addPatient(patient);

        assertEquals(1, patientService.getPatients().size());
    }

    // Test 2: Find an existing patient
    @Test
    public void testFindPatient() throws Exception {

        Patient patient = new Patient(
            102, "Manoj", 22, "Male", "9876543211", "Fever"
        );

        patientService.addPatient(patient);

        Patient foundPatient = patientService.findPatient(102);

        assertNotNull(foundPatient);
        assertEquals("Manoj", foundPatient.getName());
    }

    // Test 3: Check patient not found exception
    @Test
    public void testFindPatientNotFound() {

        assertThrows(
            PatientNotFoundException.class,
            () -> patientService.findPatient(999)
        );
    }

    // Test 4: Count patients using Stream API
    @Test
    public void testCountPatientsUsingStream() throws Exception {

        patientService.addPatient(
            new Patient(103, "Asha", 20, "Female", "9876543212", "Cold")
        );

        patientService.addPatient(
            new Patient(104, "Rahul", 21, "Male", "9876543213", "Fever")
        );

        assertEquals(2, patientService.countPatientsUsingStream());
    }
}
