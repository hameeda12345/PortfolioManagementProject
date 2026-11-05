
package com.hospitalmanagement.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hospitalmanagement.model.Bill;
import com.hospitalmanagement.model.Payment;

public class BillingService {

    private static final Logger logger =
            LoggerFactory.getLogger(BillingService.class);

    private Map<Integer, Bill> bills = new HashMap<>();
    private List<Payment> payments = new ArrayList<>();

    // Generate Bill
    public void generateBill(Bill bill) {

        if (bills.containsKey(bill.getBillId())) {

            logger.warn("Duplicate bill ID: {}", bill.getBillId());

            System.out.println("Bill ID already exists!");
            return;
        }

        bills.put(bill.getBillId(), bill);

        logger.info("Bill generated successfully. Bill ID: {}, Total: {}",
                bill.getBillId(), bill.getTotalAmount());

        System.out.println("Bill generated successfully.");
    }

    // Display Bills
    public void displayBills() {

        if (bills.isEmpty()) {
            logger.warn("No billing records available.");
            System.out.println("No bills found!");
            return;
        }

        bills.values().forEach(System.out::println);

        logger.info("Billing records displayed. Total bills: {}",
                bills.size());
    }

    // Find Bill
    public Bill findBill(int billId) {

        Bill bill = bills.get(billId);

        if (bill == null) {
            logger.warn("Bill not found. Bill ID: {}", billId);
            return null;
        }

        logger.info("Bill found. Bill ID: {}", billId);

        return bill;
    }

    // Add Payment
    public void addPayment(Payment payment) {

        Bill bill = bills.get(payment.getBillId());

        if (bill == null) {
            logger.warn("Payment rejected. Bill not found. Bill ID: {}",
                    payment.getBillId());

            System.out.println("Bill not found!");
            return;
        }

        if (payment.getAmountPaid() <= 0) {
            logger.warn("Invalid payment amount: {}",
                    payment.getAmountPaid());

            System.out.println("Payment amount must be greater than zero.");
            return;
        }

        double pendingAmount = getPendingAmount(payment.getBillId());

        if (payment.getAmountPaid() > pendingAmount) {
            logger.warn("Payment exceeds pending amount for Bill ID: {}",
                    payment.getBillId());

            System.out.println("Payment exceeds the pending amount!");
            return;
        }

        payments.add(payment);

        logger.info("Payment recorded successfully. Payment ID: {}, Bill ID: {}, Amount: {}",
                payment.getPaymentId(),
                payment.getBillId(),
                payment.getAmountPaid());

        System.out.println("Payment recorded successfully.");
    }

    // Calculate Total Paid
    public double getTotalPaid(int billId) {

        double totalPaid = payments.stream()
                .filter(p -> p.getBillId() == billId)
                .mapToDouble(Payment::getAmountPaid)
                .sum();

        logger.info("Total paid calculated for Bill ID: {}. Amount: {}",
                billId, totalPaid);

        return totalPaid;
    }

    // Calculate Pending Amount
    public double getPendingAmount(int billId) {

        Bill bill = bills.get(billId);

        if (bill == null) {
            logger.warn("Cannot calculate pending amount. Bill not found: {}",
                    billId);
            return 0;
        }

        double pendingAmount =
                bill.getTotalAmount() - getTotalPaid(billId);

        logger.info("Pending amount calculated for Bill ID: {}. Amount: {}",
                billId, pendingAmount);

        return pendingAmount;
    }

    // Display Payment History
    public void displayPayments(int billId) {

        boolean found = false;

        for (Payment payment : payments) {

            if (payment.getBillId() == billId) {
                System.out.println(payment);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No payments found for this bill.");
        }

        logger.info("Payment history displayed for Bill ID: {}",
                billId);
    }

    // Get Bills
    public Map<Integer, Bill> getBills() {
        return bills;
    }

    // Set Bills
    public void setBills(Map<Integer, Bill> bills) {
        this.bills = bills;

        logger.info("Bill records loaded. Total bills: {}",
                bills.size());
    }

    // Get Payments
    public List<Payment> getPayments() {
        return payments;
    }

    // Set Payments
    public void setPayments(List<Payment> payments) {
        this.payments = payments;

        logger.info("Payment records loaded. Total payments: {}",
                payments.size());
    }
}
