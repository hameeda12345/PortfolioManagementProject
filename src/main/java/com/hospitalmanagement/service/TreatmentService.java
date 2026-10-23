
package com.hospitalmanagement.service;

import java.util.ArrayList;
import java.util.List;

import com.hospitalmanagement.model.Treatment;

public class TreatmentService {

    private List<Treatment> treatments = new ArrayList<>();

    // Add a new treatment
    public void addTreatment(Treatment treatment) {
        treatments.add(treatment);
        System.out.println("Treatment added successfully.");
    }

    // Search for a treatment using its ID
    public Treatment findTreatment(int treatmentId) {
        for (Treatment treatment : treatments) {
            if (treatment.getTreatmentId() == treatmentId) {
                return treatment;
            }
        }

        return null;
    }

    // Display all treatments
    public void displayTreatments() {
        if (treatments.isEmpty()) {
            System.out.println("No treatments found.");
            return;
        }

        System.out.println("\n===== TREATMENT DETAILS =====");

        for (Treatment treatment : treatments) {
            System.out.println(treatment);
        }
    }

    // Get all treatments
    public List<Treatment> getTreatments() {
        return treatments;
    }

    // Replace the existing treatment list
    public void setTreatments(List<Treatment> treatments) {
        this.treatments = treatments;
    }
}