
package com.hospitalmanagement.service;

import java.util.ArrayList;
import java.util.List;

import com.hospitalmanagement.model.Appointment;

public class AppointmentService {

    private List<Appointment> appointments = new ArrayList<>();

    // Add appointment
    public void addAppointment(Appointment appointment) {

        if (findAppointment(appointment.getAppointmentId()) != null) {
            System.out.println("Appointment ID already exists.");
            return;
        }

        appointments.add(appointment);
        System.out.println("Appointment added successfully.");
    }

    // Find appointment by ID
    public Appointment findAppointment(int appointmentId) {

        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentId() == appointmentId) {
                return appointment;
            }
        }

        return null;
    }

    // Display all appointments
    public void displayAppointments() {

        if (appointments.isEmpty()) {
            System.out.println("No appointments found.");
            return;
        }

        for (Appointment appointment : appointments) {
            System.out.println(appointment);
        }
    }

    // Getter
    public List<Appointment> getAppointments() {
        return appointments;
    }

    // Setter
    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }
}