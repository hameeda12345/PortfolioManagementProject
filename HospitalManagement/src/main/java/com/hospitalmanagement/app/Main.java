
package com.hospitalmanagement.app;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.hospitalmanagement.model.Patient;
import com.hospitalmanagement.model.Doctor;
import com.hospitalmanagement.model.Appointment;
import com.hospitalmanagement.model.Treatment;

import com.hospitalmanagement.service.PatientService;
import com.hospitalmanagement.service.DoctorService;
import com.hospitalmanagement.service.AppointmentService;
import com.hospitalmanagement.service.TreatmentService;
import com.hospitalmanagement.service.JsonFileService;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        PatientService patientService = new PatientService();
        DoctorService doctorService = new DoctorService();
        AppointmentService appointmentService = new AppointmentService();
        TreatmentService treatmentService = new TreatmentService();
        JsonFileService jsonService = new JsonFileService();

        // Save the JSON file in the current working directory
        String fileName = System.getProperty("user.dir")
                + File.separator + "hospitalmanagement.json";

        System.out.println("JSON file location: " + fileName);

        // Create the JSON file if it does not exist
        File jsonFile = new File(fileName);

        if (!jsonFile.exists()) {
            jsonService.saveData(
                    new HashMap<Integer, Patient>(), fileName);
        }

        int choice;

        do {
            System.out.println("\n==================================");
            System.out.println("     HOSPITAL MANAGEMENT SYSTEM");
            System.out.println("==================================");

            System.out.println("1. Add Patient");
            System.out.println("2. Display Patients");
            System.out.println("3. Search Patient");
            System.out.println("4. Add Doctor");
            System.out.println("5. Display Doctors");
            System.out.println("6. Add Appointment");
            System.out.println("7. Display Appointments");
            System.out.println("8. Add Treatment");
            System.out.println("9. Display Treatments");
            System.out.println("10. Save Patient Data");
            System.out.println("11. Load Patient Data");
            System.out.println("12. Display Hospital Information");
            System.out.println("13. Exit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1: {
                    System.out.print("Enter Patient ID: ");
                    int patientId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Patient Name: ");
                    String patientName = sc.nextLine();

                    System.out.print("Enter Age: ");
                    int age = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Gender: ");
                    String gender = sc.nextLine();

                    System.out.print("Enter Phone Number: ");
                    String phone = sc.nextLine();

                    System.out.print("Enter Disease: ");
                    String disease = sc.nextLine();

                    Patient patient = new Patient(
                            patientId, patientName, age,
                            gender, phone, disease);

                    patientService.addPatient(patient);
                    break;
                }

                case 2:
                    patientService.displayPatients();
                    break;

                case 3: {
                    System.out.print("Enter Patient ID to search: ");
                    int searchId = sc.nextInt();
                    sc.nextLine();

                    Patient patient = patientService.findPatient(searchId);

                    if (patient != null) {
                        System.out.println("Patient found:");
                        System.out.println(patient);
                    } else {
                        System.out.println("Patient not found.");
                    }
                    break;
                }

                case 4: {
                    System.out.print("Enter Doctor ID: ");
                    int doctorId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Doctor Name: ");
                    String doctorName = sc.nextLine();

                    System.out.print("Enter Specialization: ");
                    String specialization = sc.nextLine();

                    System.out.print("Enter Doctor Phone: ");
                    String doctorPhone = sc.nextLine();

                    Doctor doctor = new Doctor(
                            doctorId, doctorName,
                            specialization, doctorPhone);

                    doctorService.addDoctor(doctor);
                    break;
                }

                case 5:
                    doctorService.displayDoctors();
                    break;

                case 6: {
                    System.out.print("Enter Appointment ID: ");
                    int appointmentId = sc.nextInt();

                    System.out.print("Enter Patient ID: ");
                    int appointmentPatientId = sc.nextInt();

                    System.out.print("Enter Doctor ID: ");
                    int appointmentDoctorId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Appointment Date: ");
                    String date = sc.nextLine();

                    System.out.print("Enter Appointment Time: ");
                    String time = sc.nextLine();

                    System.out.print("Enter Status: ");
                    String status = sc.nextLine();

                    Appointment appointment = new Appointment(
                            appointmentId, appointmentPatientId,
                            appointmentDoctorId, date, time, status);

                    appointmentService.addAppointment(appointment);
                    break;
                }

                case 7:
                    appointmentService.displayAppointments();
                    break;

                case 8: {
                    System.out.print("Enter Treatment ID: ");
                    int treatmentId = sc.nextInt();

                    System.out.print("Enter Patient ID: ");
                    int treatmentPatientId = sc.nextInt();

                    System.out.print("Enter Doctor ID: ");
                    int treatmentDoctorId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Treatment Description: ");
                    String description = sc.nextLine();

                    System.out.print("Enter Treatment Cost: ");
                    double cost = sc.nextDouble();
                    sc.nextLine();

                    Treatment treatment = new Treatment(
                            treatmentId, treatmentPatientId,
                            treatmentDoctorId, description, cost);

                    treatmentService.addTreatment(treatment);
                    break;
                }

                case 9:
                    treatmentService.displayTreatments();
                    break;

                case 10:
                    jsonService.saveData(
                            patientService.getPatients(), fileName);
                    break;

                case 11: {
                    Map<Integer, Patient> loadedPatients =
                            jsonService.loadData(
                                    fileName,
                                    new TypeReference<Map<Integer, Patient>>() {});

                    if (loadedPatients != null) {
                        patientService.setPatients(loadedPatients);
                        System.out.println(
                                "Patient data loaded successfully.");
                    } else {
                        System.out.println(
                                "Could not load patient data.");
                    }
                    break;
                }

                case 12:
                    System.out.println("\n===== HOSPITAL INFORMATION =====");
                    System.out.println("Hospital Name: City Care Hospital");
                    System.out.println("Department: General Healthcare");
                    System.out.println("System: Hospital Management System");
                    System.out.println("Data File: " + jsonFile.getAbsolutePath());
                    break;

                case 13:
                    System.out.println("Exiting Hospital Management System.");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 13);

        sc.close();
    }
}

