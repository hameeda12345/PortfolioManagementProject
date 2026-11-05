
package com.hospitalmanagement.util;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class TimeUtil {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("HH:mm")
                    .withResolverStyle(ResolverStyle.STRICT);

    // Validate appointment time
    public static boolean isValidTime(String time) {

        try {
            LocalTime.parse(time, FORMATTER);
            return true;

        } catch (DateTimeParseException | NullPointerException e) {
            return false;
        }
    }
}
