
package com.hospitalmanagement.model;

import java.util.Objects;

public class Treatment {

    private int treatmentId;
    private int patientId;
    private int doctorId;
    private String description;
    private double cost;

    // Default constructor
    public Treatment() {
    }

    // Parameterized constructor
    public Treatment(int treatmentId, int patientId,
                     int doctorId, String description,
                     double cost) {

        this.treatmentId = treatmentId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.description = description;
        this.cost = cost;
    }

    // Getters and Setters
    public int getTreatmentId() {
        return treatmentId;
    }

    public void setTreatmentId(int treatmentId) {
        this.treatmentId = treatmentId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    // toString method
    @Override
    public String toString() {
        return "Treatment{" +
                "treatmentId=" + treatmentId +
                ", patientId=" + patientId +
                ", doctorId=" + doctorId +
                ", description='" + description + '\'' +
                ", cost=" + cost +
                '}';
    }

    // equals method
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Treatment)) return false;

        Treatment other = (Treatment) obj;
        return treatmentId == other.treatmentId;
    }

    // hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(treatmentId);
    }
}
