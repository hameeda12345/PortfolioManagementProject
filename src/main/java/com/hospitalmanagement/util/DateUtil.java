
package com.hospitalmanagement.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtil {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd-MM-uuuu");

    // Validate appointment date
    public static boolean isValidDate(String date) {

        try {
            LocalDate parsedDate =
                    LocalDate.parse(date, FORMATTER);

            return !parsedDate.isBefore(LocalDate.now());

        } catch (DateTimeParseException | NullPointerException e) {
            return false;
        }
    }

    // Format date
    public static String formatDate(LocalDate date) {
        return date.format(FORMATTER);
    }
}
