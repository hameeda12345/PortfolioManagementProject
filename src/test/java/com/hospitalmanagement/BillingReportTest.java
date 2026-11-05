
package com.hospitalmanagement;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hospitalmanagement.model.Bill;
import com.hospitalmanagement.service.BillingService;
import com.hospitalmanagement.service.BillingReport;

@ExtendWith(MockitoExtension.class)
public class BillingReportTest {

    @Mock
    private BillingService billingService;

    @InjectMocks
    private BillingReport billingReport;

    @Test
    public void testGenerateBillingReport() {

        // Create sample bill
        Bill bill = new Bill(
                1, 101, "05-11-2026",
                500, 1000, 300, 700
        );

        // Create sample bill data
        Map<Integer, Bill> bills = new HashMap<>();
        bills.put(1, bill);

        // Define fake responses
        when(billingService.getBills()).thenReturn(bills);

        when(billingService.getTotalPaid(1))
                .thenReturn(1000.0);

        when(billingService.getPendingAmount(1))
                .thenReturn(1500.0);

        // Execute report and check for exceptions
        assertDoesNotThrow(() ->
                billingReport.generateBillingReport()
        );

        // Verify method calls
        verify(billingService, times(1)).getBills();

        verify(billingService, times(1))
                .getTotalPaid(1);

        verify(billingService, times(1))
                .getPendingAmount(1);
    }
}
