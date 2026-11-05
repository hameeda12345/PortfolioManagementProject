
package com.hospitalmanagement.service;

import com.hospitalmanagement.model.Bill;
import java.util.Map;

public class BillingReport {

    private BillingService billingService;

    // Constructor
    public BillingReport(BillingService billingService) {
        this.billingService = billingService;
    }

    // Display complete billing report
    public void generateBillingReport() {

        // Get all bills only once
        Map<Integer, Bill> bills = billingService.getBills();

        if (bills.isEmpty()) {
            System.out.println("No billing records available.");
            return;
        }

        double totalRevenue = 0;
        double totalCollected = 0;
        double totalPending = 0;

        System.out.println("\n========== HOSPITAL BILLING REPORT ==========");

        for (Bill bill : bills.values()) {

            double billAmount = bill.getTotalAmount();

            double paidAmount =
                    billingService.getTotalPaid(bill.getBillId());

            double pendingAmount =
                    billingService.getPendingAmount(bill.getBillId());

            totalRevenue += billAmount;
            totalCollected += paidAmount;
            totalPending += pendingAmount;

            System.out.println("\nBill ID: " + bill.getBillId());
            System.out.println("Patient ID: " + bill.getPatientId());
            System.out.println("Bill Amount: " + billAmount);
            System.out.println("Amount Paid: " + paidAmount);
            System.out.println("Pending Amount: " + pendingAmount);

            if (pendingAmount == 0) {
                System.out.println("Payment Status: PAID");
            } else if (paidAmount > 0) {
                System.out.println("Payment Status: PARTIALLY PAID");
            } else {
                System.out.println("Payment Status: UNPAID");
            }
        }

        System.out.println("\n========== BILLING SUMMARY ==========");
        System.out.println("Total Bills: " + bills.size());
        System.out.println("Total Revenue: " + totalRevenue);
        System.out.println("Total Collected: " + totalCollected);
        System.out.println("Total Pending: " + totalPending);
        System.out.println("=====================================");
    }
}
