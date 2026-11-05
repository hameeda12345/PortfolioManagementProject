package com.hospitalmanagement.app;

import com.hospitalmanagement.model.AppointmentStatus;

public class EnumDemo {

    public static void main(String[] args) {

        AppointmentStatus status = AppointmentStatus.SCHEDULED;

        System.out.println("Appointment Status: " + status);

        if (status == AppointmentStatus.SCHEDULED) {
            System.out.println("Your appointment is scheduled.");
        }
    }
}