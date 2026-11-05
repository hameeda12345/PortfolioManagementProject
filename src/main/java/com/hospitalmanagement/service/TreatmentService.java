
package com.hospitalmanagement.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hospitalmanagement.model.Treatment;
import com.hospitalmanagement.exception.DuplicateTreatmentException;
import com.hospitalmanagement.exception.TreatmentNotFoundException;

public class TreatmentService {

    private static final Logger logger =
            LoggerFactory.getLogger(TreatmentService.class);

    private List<Treatment> treatments = new ArrayList<>();

    // Add Treatment
    public void addTreatment(Treatment treatment)
            throws DuplicateTreatmentException {

        boolean exists = treatments.stream()
                .anyMatch(t -> t.getTreatmentId()
                        == treatment.getTreatmentId());

        if (exists) {
            logger.warn("Duplicate treatment ID: {}",
                    treatment.getTreatmentId());

            throw new DuplicateTreatmentException(
                    "Treatment already exists!");
        }

        treatments.add(treatment);

        logger.info("Treatment added successfully. Treatment ID: {}",
                treatment.getTreatmentId());
    }

    // Display Treatments
    public void displayTreatments() {

        if (treatments.isEmpty()) {
            logger.warn("No treatment records available.");
            System.out.println("No treatments found!");
            return;
        }

        treatments.forEach(System.out::println);

        logger.info("Treatment records displayed. Total treatments: {}",
                treatments.size());
    }

    // Find Treatment
    public Treatment findTreatment(int treatmentId)
            throws TreatmentNotFoundException {

        Treatment treatment = treatments.stream()
                .filter(t -> t.getTreatmentId() == treatmentId)
                .findFirst()
                .orElse(null);

        if (treatment == null) {
            logger.error("Treatment not found. Treatment ID: {}",
                    treatmentId);

            throw new TreatmentNotFoundException(
                    "Treatment not found!");
        }

        logger.info("Treatment found. Treatment ID: {}", treatmentId);

        return treatment;
    }

    // Get Treatments
    public List<Treatment> getTreatments() {
        return treatments;
    }

    // Set Treatments
    public void setTreatments(List<Treatment> treatments) {
        this.treatments = treatments;

        logger.info("Treatment records loaded. Total treatments: {}",
                treatments.size());
    }
}
