package com.hospitalmanagement.app;

import com.hospitalmanagement.service.PatientService;
import com.hospitalmanagement.service.AppointmentService;

public class HospitalReportRunnable implements Runnable {

    private PatientService patientService;
    private AppointmentService appointmentService;

    public HospitalReportRunnable(
            PatientService patientService,
            AppointmentService appointmentService) {

        this.patientService = patientService;
        this.appointmentService = appointmentService;
    }

    @Override
    public void run() {

        System.out.println("\n===== HOSPITAL REPORT USING RUNNABLE =====");

        System.out.println("Generating Patient Report...");

        int patientCount = patientService.getPatients().size();

        System.out.println("Total Patients: " + patientCount);

        System.out.println("Generating Appointment Report...");

        int appointmentCount =
                appointmentService.getAppointments().size();

        System.out.println("Total Appointments: " + appointmentCount);

        System.out.println("Report generation completed.");

        System.out.println(
                "Executed by: " +
                Thread.currentThread().getName()
        );
    }
}