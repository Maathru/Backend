package com.maathru.backend.Domain.validation.impl;

import com.maathru.backend.Domain.validation.ValidNIC;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class NICValidator implements ConstraintValidator<ValidNIC, String> {
    @Override
    public void initialize(ValidNIC constraintAnnotation) {
    }

    @Override
    public boolean isValid(String nic, ConstraintValidatorContext context) {
        if (nic == null || nic.isEmpty()) {
            return false;
        }

        return validateNIC(nic);
    }

    static class ExtractedData {
        String year;
        String dayList;
        String character;

        public ExtractedData(String year, String dayList, String character) {
            this.year = year;
            this.dayList = dayList;
            this.character = character;
        }
    }

    private static final int MAX_DAYS = 866;
    private static final int FEMALE_OFFSET = 500;
    private static final int MAX_MALE_DAYS = 366;
    private static final int MIN_VALID_YEAR = 1953;
    private static final int MAX_VALID_YEAR = 2005;

    private static boolean validateNIC(String nicNumber) {
        if (validation(nicNumber)) {
            ExtractedData extractedData = extractData(nicNumber);
            int days = Integer.parseInt(extractedData.dayList);

            if (days < 0 || days > MAX_DAYS || (days > MAX_MALE_DAYS && days < FEMALE_OFFSET)) {
                return false;
            }

            int year = Integer.parseInt(extractedData.year);
            return year >= MIN_VALID_YEAR && year <= MAX_VALID_YEAR;
        } else {
            return false;
        }
    }


    private static boolean validation(String nicNumber) {
        if (nicNumber.length() == 10) {
            // Check if first 9 characters are digits and the 10th character is 'x' or 'v' (case-insensitive)
            return nicNumber.substring(0, 9).matches("\\d+") &&
                    nicNumber.substring(9).matches("[xXvV]");
        } else if (nicNumber.length() == 12) {
            // Check if all 12 characters are digits
            return nicNumber.matches("\\d+");
        }
        return false;
    }

    private static ExtractedData extractData(String nicNumber) {
        String year = "";
        String dayList = "";
        String character = "";

        if (nicNumber.length() == 10) {
            year = nicNumber.substring(0, 2);
            dayList = nicNumber.substring(2, 5);
            character = nicNumber.substring(9);
        } else if (nicNumber.length() == 12) {
            year = nicNumber.substring(0, 4);
            dayList = nicNumber.substring(4, 7);
            character = "no";
        }

        return new ExtractedData(year, dayList, character);
    }
}
