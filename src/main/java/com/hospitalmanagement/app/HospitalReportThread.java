
package com.hospitalmanagement.app;

import com.hospitalmanagement.service.PatientService;
import com.hospitalmanagement.service.AppointmentService;

public class HospitalReportThread extends Thread {

    private PatientService patientService;
    private AppointmentService appointmentService;

    // Constructor
    public HospitalReportThread(
            PatientService patientService,
            AppointmentService appointmentService) {

        this.patientService = patientService;
        this.appointmentService = appointmentService;
    }

    // Thread execution method
    @Override
    public void run() {

        System.out.println("\n===== HOSPITAL REPORT =====");

        // Patient Report
        System.out.println("Generating Patient Report...");

        int patientCount =
                patientService.getPatients().size();

        System.out.println("Total Patients: " + patientCount);

        // Appointment Report
        System.out.println("Generating Appointment Report...");

        int appointmentCount =
                appointmentService.getAppointments().size();

        System.out.println("Total Appointments: " + appointmentCount);

        System.out.println("Report generation completed.");

        System.out.println(
            "Executed by: " + Thread.currentThread().getName()
        );
    }
}
