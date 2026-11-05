
package com.hospitalmanagement.model;

public class Bill {

    private int billId;
    private int patientId;
    private String billDate;

    private double consultationCharge;
    private double testCharge;
    private double medicineCharge;
    private double treatmentCharge;

    private double totalAmount;

    // Default constructor
    public Bill() {
    }

    // Parameterized constructor
    public Bill(int billId, int patientId, String billDate,
                double consultationCharge,
                double testCharge,
                double medicineCharge,
                double treatmentCharge) {

        this.billId = billId;
        this.patientId = patientId;
        this.billDate = billDate;
        this.consultationCharge = consultationCharge;
        this.testCharge = testCharge;
        this.medicineCharge = medicineCharge;
        this.treatmentCharge = treatmentCharge;

        calculateTotal();
    }

    // Calculate total bill amount
    public void calculateTotal() {

        totalAmount = consultationCharge
                    + testCharge
                    + medicineCharge
                    + treatmentCharge;
    }

    // Getters and Setters

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public double getConsultationCharge() {
        return consultationCharge;
    }

    public void setConsultationCharge(double consultationCharge) {
        this.consultationCharge = consultationCharge;
        calculateTotal();
    }

    public double getTestCharge() {
        return testCharge;
    }

    public void setTestCharge(double testCharge) {
        this.testCharge = testCharge;
        calculateTotal();
    }

    public double getMedicineCharge() {
        return medicineCharge;
    }

    public void setMedicineCharge(double medicineCharge) {
        this.medicineCharge = medicineCharge;
        calculateTotal();
    }

    public double getTreatmentCharge() {
        return treatmentCharge;
    }

    public void setTreatmentCharge(double treatmentCharge) {
        this.treatmentCharge = treatmentCharge;
        calculateTotal();
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    @Override
    public String toString() {

        return "\n===== BILL DETAILS ====="
                + "\nBill ID: " + billId
                + "\nPatient ID: " + patientId
                + "\nBill Date: " + billDate
                + "\nConsultation Charge: " + consultationCharge
                + "\nTest Charge: " + testCharge
                + "\nMedicine Charge: " + medicineCharge
                + "\nTreatment Charge: " + treatmentCharge
                + "\nTotal Amount: " + totalAmount;
    }
}
