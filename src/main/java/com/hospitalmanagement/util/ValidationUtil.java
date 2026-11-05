
package com.hospitalmanagement.util;

public class ValidationUtil {

    // Validate name
    public static boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    // Validate phone number
    public static boolean isValidPhone(String phone) {
        return phone != null && phone.matches("[6-9][0-9]{9}");
    }

    // Validate ID
    public static boolean isValidId(int id) {
        return id > 0;
    }

    // Validate patient age
    public static boolean isValidAge(int age) {
        return age > 0 && age <= 120;
    }

    // Validate cost
    public static boolean isValidCost(double cost) {
        return Double.isFinite(cost) && cost >= 0;
    }

    // Validate gender
    public static boolean isValidGender(String gender) {
        return gender != null &&
               (gender.equalsIgnoreCase("Male") ||
                gender.equalsIgnoreCase("Female") ||
                gender.equalsIgnoreCase("Other"));
    }
}
