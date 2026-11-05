
package com.hospitalmanagement.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hospitalmanagement.model.Appointment;
import com.hospitalmanagement.exception.DuplicateAppointmentException;
import com.hospitalmanagement.exception.AppointmentNotFoundException;

public class AppointmentService {

    private static final Logger logger =
            LoggerFactory.getLogger(AppointmentService.class);

    private List<Appointment> appointments = new ArrayList<>();

    // Add Appointment
    public void addAppointment(Appointment appointment)
            throws DuplicateAppointmentException {

        boolean exists = appointments.stream()
                .anyMatch(a -> a.getAppointmentId()
                        == appointment.getAppointmentId());

        if (exists) {
            logger.warn("Duplicate appointment ID: {}",
                    appointment.getAppointmentId());

            throw new DuplicateAppointmentException(
                    "Appointment already exists!");
        }

        appointments.add(appointment);

        logger.info("Appointment added successfully. Appointment ID: {}",
                appointment.getAppointmentId());
    }

    // Display Appointments
    public void displayAppointments() {

        if (appointments.isEmpty()) {
            logger.warn("No appointment records available.");
            System.out.println("No appointments found!");
            return;
        }

        appointments.forEach(System.out::println);

        logger.info("Appointment records displayed. Total appointments: {}",
                appointments.size());
    }

    // Find Appointment
    public Appointment findAppointment(int appointmentId)
            throws AppointmentNotFoundException {

        Appointment appointment = appointments.stream()
                .filter(a -> a.getAppointmentId() == appointmentId)
                .findFirst()
                .orElse(null);

        if (appointment == null) {

            logger.error("Appointment not found. Appointment ID: {}",
                    appointmentId);

            throw new AppointmentNotFoundException(
                    "Appointment not found!");
        }

        logger.info("Appointment found. Appointment ID: {}",
                appointmentId);

        return appointment;
    }

    // Get Appointments
    public List<Appointment> getAppointments() {
        return appointments;
    }

    // Set Appointments
    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;

        logger.info("Appointment records loaded. Total appointments: {}",
                appointments.size());
    }
}
