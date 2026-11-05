
package com.hospitalmanagement;

import static org.junit.jupiter.api.Assertions.assertEquals; //junit test

import org.junit.jupiter.api.Test;

import com.hospitalmanagement.model.Bill;

public class BillTest {

    @Test
    public void testCalculateTotal() {

        Bill bill = new Bill(
                101, 1, "05-11-2026",
                500, 1000, 300, 700
        );

        assertEquals(2500.0, bill.getTotalAmount());
    }
}
