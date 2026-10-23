package com.hospitalmanagement.model;

public class Treatment {

    private int treatmentId;
    private int patientId;
    private int doctorId;
    private String description;
    private double cost;

    public Treatment() {
    }

    public Treatment(int treatmentId, int patientId,
                     int doctorId, String description,
                     double cost) {

        this.treatmentId = treatmentId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.description = description;
        this.cost = cost;
    }

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
}