
package com.hospitalmanagement.app;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;

import com.hospitalmanagement.model.Patient;
import com.hospitalmanagement.model.Doctor;
import com.hospitalmanagement.model.Appointment;
import com.hospitalmanagement.model.Treatment;
import com.hospitalmanagement.model.Bill;
import com.hospitalmanagement.model.Payment;

import com.hospitalmanagement.service.PatientService;
import com.hospitalmanagement.service.DoctorService;
import com.hospitalmanagement.service.AppointmentService;
import com.hospitalmanagement.service.TreatmentService;
import com.hospitalmanagement.service.JsonFileService;
import com.hospitalmanagement.service.BillingService;
import com.hospitalmanagement.service.BillingReport;

import com.hospitalmanagement.exception.DuplicatePatientException;
import com.hospitalmanagement.exception.PatientNotFoundException;
import com.hospitalmanagement.exception.DuplicateDoctorException;
import com.hospitalmanagement.exception.DoctorNotFoundException;
import com.hospitalmanagement.exception.DuplicateAppointmentException;
import com.hospitalmanagement.exception.AppointmentNotFoundException;
import com.hospitalmanagement.exception.DuplicateTreatmentException;
import com.hospitalmanagement.exception.TreatmentNotFoundException;

public class Main {

    private static final Logger logger =
            LoggerFactory.getLogger(Main.class);

    static Scanner sc = new Scanner(System.in);

    static PatientService patientService = new PatientService();
    static DoctorService doctorService = new DoctorService();
    static AppointmentService appointmentService = new AppointmentService();
    static TreatmentService treatmentService = new TreatmentService();

    static JsonFileService jsonService = new JsonFileService();

    static BillingService billingService = new BillingService();
    static BillingReport billingReport = new BillingReport(billingService);

    public static void main(String[] args) {

        logger.info("Hospital Management System started.");

        int choice;

        do {
            System.out.println("\n======================================");
            System.out.println("       HOSPITAL MANAGEMENT SYSTEM");
            System.out.println("======================================");

            System.out.println("1. Add Patient");
            System.out.println("2. Display Patients");
            System.out.println("3. Search Patient");

            System.out.println("4. Add Doctor");
            System.out.println("5. Display Doctors");

            System.out.println("6. Add Appointment");
            System.out.println("7. Display Appointments");

            System.out.println("8. Add Treatment");
            System.out.println("9. Display Treatments");
            System.out.println("10. Search Treatment");

            System.out.println("11. Save Data");
            System.out.println("12. Load Data");

            System.out.println("13. Display Unique Diseases");
            System.out.println("14. Stream API - Patient Reports");

            System.out.println("15. Generate Report Using Thread");
            System.out.println("16. Generate Report Using Runnable");

            System.out.println("\n---------- BILLING MANAGEMENT ----------");

            System.out.println("17. Generate Bill");
            System.out.println("18. Display All Bills");
            System.out.println("19. Search Bill");
            System.out.println("20. Record Payment");
            System.out.println("21. Check Pending Amount");
            System.out.println("22. Display Payment History");
            System.out.println("23. Generate Billing Report");

            System.out.println("24. Exit");

            System.out.print("\nEnter your choice: ");
            choice = readInt();

            logger.info("User selected menu option: {}", choice);

            try {
                switch (choice) {

                    case 1:
                        addPatient();
                        break;

                    case 2:
                        patientService.displayPatients();
                        break;

                    case 3:
                        searchPatient();
                        break;

                    case 4:
                        addDoctor();
                        break;

                    case 5:
                        doctorService.displayDoctors();
                        break;

                    case 6:
                        addAppointment();
                        break;

                    case 7:
                        appointmentService.displayAppointments();
                        break;

                    case 8:
                        addTreatment();
                        break;

                    case 9:
                        treatmentService.displayTreatments();
                        break;

                    case 10:
                        searchTreatment();
                        break;

                    case 11:
                        saveData();
                        break;

                    case 12:
                        loadData();
                        break;

                    case 13:
                        patientService.displayUniqueDiseases();
                        break;

                    case 14:
                        patientStreamMenu();
                        break;

                    case 15:
                        generateThreadReport();
                        break;

                    case 16:
                        generateRunnableReport();
                        break;

                    case 17:
                        generateBill();
                        break;

                    case 18:
                        billingService.displayBills();
                        break;

                    case 19:
                        searchBill();
                        break;

                    case 20:
                        recordPayment();
                        break;

                    case 21:
                        checkPendingAmount();
                        break;

                    case 22:
                        displayPaymentHistory();
                        break;

                    case 23:
                        billingReport.generateBillingReport();
                        break;

                    case 24:
                        System.out.println(
                                "Thank you for using Hospital Management System!");
                        logger.info("Hospital Management System closed.");
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                        logger.warn("Invalid menu option: {}", choice);
                }

            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                logger.error("Error while executing menu option: {}", choice, e);
            }

        } while (choice != 24);

        sc.close();
    }

    // Read integer safely
    public static int readInt() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                logger.warn("Invalid numeric input entered.");
                System.out.print("Please enter a valid number: ");
            }
        }
    }

    // Read decimal safely
    public static double readDouble() {
        while (true) {
            try {
                double value = Double.parseDouble(sc.nextLine().trim());

                if (!Double.isFinite(value)) {
                    System.out.print("Enter a valid amount: ");
                    continue;
                }

                return value;

            } catch (NumberFormatException e) {
                logger.warn("Invalid decimal input entered.");
                System.out.print("Please enter a valid amount: ");
            }
        }
    }

    // 1. Add Patient
    public static void addPatient() {

        try {
            System.out.print("Enter Patient ID: ");
            int id = readInt();

            System.out.print("Enter Patient Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Age: ");
            int age = readInt();

            System.out.print("Enter Gender: ");
            String gender = sc.nextLine();

            System.out.print("Enter Phone Number: ");
            String phone = sc.nextLine();

            System.out.print("Enter Disease: ");
            String disease = sc.nextLine();

            Patient patient = new Patient(
                    id, name, age, gender, phone, disease);

            patientService.addPatient(patient);

            logger.info("Patient added successfully. Patient ID: {}", id);

        } catch (DuplicatePatientException e) {
            System.out.println(e.getMessage());
            logger.warn("Duplicate patient: {}", e.getMessage());
        }
    }

    // 3. Search Patient
    public static void searchPatient() {

        System.out.print("Enter Patient ID: ");
        int id = readInt();

        try {
            Patient patient = patientService.findPatient(id);
            System.out.println(patient);

            logger.info("Patient searched successfully. ID: {}", id);

        } catch (PatientNotFoundException e) {
            System.out.println(e.getMessage());
            logger.warn("Patient not found. ID: {}", id);
        }
    }

    // 4. Add Doctor
    public static void addDoctor() {

        try {
            System.out.print("Enter Doctor ID: ");
            int id = readInt();

            System.out.print("Enter Doctor Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Specialization: ");
            String specialization = sc.nextLine();

            System.out.print("Enter Phone Number: ");
            String phone = sc.nextLine();

            Doctor doctor = new Doctor(
                    id, name, specialization, phone);

            doctorService.addDoctor(doctor);

            logger.info("Doctor added successfully. Doctor ID: {}", id);

        } catch (DuplicateDoctorException e) {
            System.out.println(e.getMessage());
            logger.warn("Duplicate doctor: {}", e.getMessage());
        }
    }

    // 6. Add Appointment
    public static void addAppointment() {

        try {
            System.out.print("Enter Appointment ID: ");
            int appointmentId = readInt();

            System.out.print("Enter Patient ID: ");
            int patientId = readInt();

            System.out.print("Enter Doctor ID: ");
            int doctorId = readInt();

            patientService.findPatient(patientId);
            doctorService.findDoctor(doctorId);

            System.out.print("Enter Appointment Date: ");
            String date = sc.nextLine();

            System.out.print("Enter Appointment Time: ");
            String time = sc.nextLine();

            System.out.print("Enter Appointment Status: ");
            String status = sc.nextLine();

            Appointment appointment = new Appointment(
                    appointmentId, patientId, doctorId,
                    date, time, status);

            appointmentService.addAppointment(appointment);

            logger.info("Appointment added. ID: {}", appointmentId);

        } catch (PatientNotFoundException |
                 DoctorNotFoundException |
                 DuplicateAppointmentException e) {

            System.out.println(e.getMessage());
            logger.warn("Appointment could not be added: {}", e.getMessage());
        }
    }

    // 8. Add Treatment
    public static void addTreatment() {

        try {
            System.out.print("Enter Treatment ID: ");
            int treatmentId = readInt();

            System.out.print("Enter Patient ID: ");
            int patientId = readInt();

            System.out.print("Enter Doctor ID: ");
            int doctorId = readInt();

            patientService.findPatient(patientId);
            doctorService.findDoctor(doctorId);

            System.out.print("Enter Treatment Description: ");
            String description = sc.nextLine();

            System.out.print("Enter Treatment Cost: ");
            double cost = readDouble();

            Treatment treatment = new Treatment(
                    treatmentId, patientId, doctorId,
                    description, cost);

            treatmentService.addTreatment(treatment);

            logger.info("Treatment added. ID: {}", treatmentId);

        } catch (PatientNotFoundException |
                 DoctorNotFoundException |
                 DuplicateTreatmentException e) {

            System.out.println(e.getMessage());
            logger.warn("Treatment could not be added: {}", e.getMessage());
        }
    }

    // 10. Search Treatment
    public static void searchTreatment() {

        System.out.print("Enter Treatment ID: ");
        int id = readInt();

        try {
            Treatment treatment = treatmentService.findTreatment(id);
            System.out.println(treatment);

            logger.info("Treatment searched. ID: {}", id);

        } catch (TreatmentNotFoundException e) {
            System.out.println(e.getMessage());
            logger.warn("Treatment not found. ID: {}", id);
        }
    }

    // 11. Save Data
    public static void saveData() {

        jsonService.saveData(
                patientService.getPatients(), "patients.json");

        jsonService.saveData(
                doctorService.getDoctors(), "doctors.json");

        jsonService.saveData(
                appointmentService.getAppointments(), "appointments.json");

        jsonService.saveData(
                treatmentService.getTreatments(), "treatments.json");

        jsonService.saveData(
                billingService.getBills(), "bills.json");

        jsonService.saveData(
                billingService.getPayments(), "payments.json");

        System.out.println("Hospital data saved.");
        logger.info("Hospital data save operation completed.");
    }

    // 12. Load Data
    public static void loadData() {

        Map<Integer, Patient> patients = jsonService.loadData(
                "patients.json",
                new TypeReference<HashMap<Integer, Patient>>() {});

        Map<Integer, Doctor> doctors = jsonService.loadData(
                "doctors.json",
                new TypeReference<HashMap<Integer, Doctor>>() {});

        List<Appointment> appointments = jsonService.loadData(
                "appointments.json",
                new TypeReference<List<Appointment>>() {});

        List<Treatment> treatments = jsonService.loadData(
                "treatments.json",
                new TypeReference<List<Treatment>>() {});

        Map<Integer, Bill> bills = jsonService.loadData(
                "bills.json",
                new TypeReference<HashMap<Integer, Bill>>() {});

        List<Payment> payments = jsonService.loadData(
                "payments.json",
                new TypeReference<List<Payment>>() {});

        if (patients != null) {
            patientService.setPatients(patients);
        }

        if (doctors != null) {
            doctorService.setDoctors(doctors);
        }

        if (appointments != null) {
            appointmentService.setAppointments(appointments);
        }

        if (treatments != null) {
            treatmentService.setTreatments(treatments);
        }

        if (bills != null) {
            billingService.setBills(bills);
        }

        if (payments != null) {
            billingService.setPayments(payments);
        }

        System.out.println("Hospital data loaded.");
        logger.info("Hospital data loading operation completed.");
    }

    // 14. Stream API Menu
    public static void patientStreamMenu() {

        int choice;

        do {
            System.out.println("\n===== PATIENT STREAM REPORTS =====");
            System.out.println("1. Filter Patients By Disease");
            System.out.println("2. Sort Patients By Age");
            System.out.println("3. Count Total Patients");
            System.out.println("4. Display Patient Names");
            System.out.println("5. Back To Main Menu");

            System.out.print("Enter your choice: ");
            choice = readInt();

            logger.info("Patient Stream submenu selection: {}", choice);

            switch (choice) {

                case 1:
                    System.out.print("Enter Disease: ");
                    String disease = sc.nextLine();
                    patientService.filterPatientsByDisease(disease);
                    break;

                case 2:
                    patientService.sortPatientsByAge();
                    break;

                case 3:
                    patientService.countPatientsUsingStream();
                    break;

                case 4:
                    patientService.displayPatientNames();
                    break;

                case 5:
                    System.out.println("Returning to main menu.");
                    break;

                default:
                    System.out.println("Invalid choice.");
                    logger.warn("Invalid Stream submenu option: {}", choice);
            }

        } while (choice != 5);
    }

    // 15. Generate Report Using Thread
    public static void generateThreadReport() {

        HospitalReportThread reportThread =
                new HospitalReportThread(
                        patientService, appointmentService);

        reportThread.start();

        System.out.println("Report thread started.");
        logger.info("Hospital report thread started.");
    }

    // 16. Generate Report Using Runnable
    public static void generateRunnableReport() {

        Runnable reportTask = () -> {

            logger.info("Runnable hospital report started.");

            System.out.println("\n===== HOSPITAL REPORT USING RUNNABLE =====");

            System.out.println(
                    "Total Patients: " +
                    patientService.getPatients().size());

            System.out.println(
                    "Total Doctors: " +
                    doctorService.getDoctors().size());

            System.out.println(
                    "Total Appointments: " +
                    appointmentService.getAppointments().size());

            System.out.println(
                    "Total Treatments: " +
                    treatmentService.getTreatments().size());

            System.out.println("Report generated successfully.");

            logger.info("Runnable hospital report completed.");
        };

        Thread thread = new Thread(reportTask);
        thread.start();
    }

    // 17. Generate Bill
    public static void generateBill() {

        try {
            System.out.print("Enter Bill ID: ");
            int billId = readInt();

            System.out.print("Enter Patient ID: ");
            int patientId = readInt();

            Patient patient = patientService.findPatient(patientId);

            System.out.println("Patient: " + patient.getName());

            System.out.print("Enter Bill Date: ");
            String billDate = sc.nextLine();

            System.out.print("Enter Consultation Charge: ");
            double consultation = readDouble();

            System.out.print("Enter Test Charge: ");
            double test = readDouble();

            System.out.print("Enter Medicine Charge: ");
            double medicine = readDouble();

            System.out.print("Enter Treatment Charge: ");
            double treatment = readDouble();

            if (consultation < 0 || test < 0 ||
                medicine < 0 || treatment < 0) {

                System.out.println("Charges cannot be negative.");
                logger.warn("Negative billing charge entered.");
                return;
            }

            Bill bill = new Bill(
                    billId, patientId, billDate,
                    consultation, test, medicine, treatment);

            billingService.generateBill(bill);

            logger.info("Bill generation requested. Bill ID: {}", billId);

        } catch (PatientNotFoundException e) {
            System.out.println(e.getMessage());
            logger.warn("Cannot generate bill: patient not found.");
        }
    }

    // 19. Search Bill
    public static void searchBill() {

        System.out.print("Enter Bill ID: ");
        int billId = readInt();

        Bill bill = billingService.findBill(billId);

        if (bill == null) {
            System.out.println("Bill not found.");
            logger.warn("Bill not found. ID: {}", billId);
        } else {
            System.out.println(bill);
            logger.info("Bill searched successfully. ID: {}", billId);
        }
    }

    // 20. Record Payment
    public static void recordPayment() {

        System.out.print("Enter Payment ID: ");
        int paymentId = readInt();

        System.out.print("Enter Bill ID: ");
        int billId = readInt();

        System.out.print("Enter Amount Paid: ");
        double amount = readDouble();

        System.out.print("Enter Payment Date: ");
        String date = sc.nextLine();

        System.out.print("Enter Payment Method (Cash/UPI/Card): ");
        String method = sc.nextLine();

        Payment payment = new Payment(
                paymentId, billId, amount, date, method);

        billingService.addPayment(payment);

        logger.info("Payment recording requested. Payment ID: {}", paymentId);
    }

    // 21. Check Pending Amount
    public static void checkPendingAmount() {

        System.out.print("Enter Bill ID: ");
        int billId = readInt();

        Bill bill = billingService.findBill(billId);

        if (bill == null) {
            System.out.println("Bill not found.");
            logger.warn("Cannot check pending amount. Bill not found: {}", billId);
            return;
        }

        double pending = billingService.getPendingAmount(billId);

        System.out.println("Total Bill Amount: " +
                bill.getTotalAmount());

        System.out.println("Total Paid: " +
                billingService.getTotalPaid(billId));

        System.out.println("Pending Amount: " + pending);

        logger.info("Pending amount checked for Bill ID: {}", billId);
    }

    // 22. Display Payment History
    public static void displayPaymentHistory() {

        System.out.print("Enter Bill ID: ");
        int billId = readInt();

        Bill bill = billingService.findBill(billId);

        if (bill == null) {
            System.out.println("Bill not found.");
            logger.warn("Payment history unavailable. Bill not found: {}", billId);
            return;
        }

        billingService.displayPayments(billId);

        logger.info("Payment history displayed for Bill ID: {}", billId);
    }
}

