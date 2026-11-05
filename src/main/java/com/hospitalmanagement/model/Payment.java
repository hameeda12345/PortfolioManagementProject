package com.hospitalmanagement.model;

public class Payment {

    private int paymentId;
    private int billId;
    private double amountPaid;
    private String paymentDate;
    private String paymentMethod;

    // Default constructor
    public Payment() {
    }

    // Parameterized constructor
    public Payment(int paymentId, int billId, double amountPaid,
                   String paymentDate, String paymentMethod) {
        this.paymentId = paymentId;
        this.billId = billId;
        this.amountPaid = amountPaid;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
    }

    // Getters and Setters
    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Override
    public String toString() {
        return "\n===== PAYMENT DETAILS ====="
                + "\nPayment ID: " + paymentId
                + "\nBill ID: " + billId
                + "\nAmount Paid: " + amountPaid
                + "\nPayment Date: " + paymentDate
                + "\nPayment Method: " + paymentMethod;
    }
}