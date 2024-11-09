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

//    static class MonthData {
//        String month;
//        int days;
//
//        public MonthData(String month, int days) {
//            this.month = month;
//            this.days = days;
//        }
//    }

//    static class DayGenderResult {
//        int day;
//        String month;
//        String gender;
//
//        public DayGenderResult(int day, String month, String gender) {
//            this.day = day;
//            this.month = month;
//            this.gender = gender;
//        }
//    }

//    private static final List<MonthData> dArray = List.of(
//            new MonthData("January", 31),
//            new MonthData("February", 29),
//            new MonthData("March", 31),
//            new MonthData("April", 30),
//            new MonthData("May", 31),
//            new MonthData("June", 30),
//            new MonthData("July", 31),
//            new MonthData("August", 31),
//            new MonthData("September", 30),
//            new MonthData("October", 31),
//            new MonthData("November", 30),
//            new MonthData("December", 31)
//    );

    private static boolean validateNIC(String nicNumber) {
        if (validation(nicNumber)) {
            ExtractedData extractedData = extractData(nicNumber);
            int days = Integer.parseInt(extractedData.dayList);

            if (days < 0 || days > 866 || (days > 366 && days < 500)) {
                return false;
            }

//            DayGenderResult foundData = findDayAndGender(days, dArray);

            int year = Integer.parseInt(extractedData.year);
            return year >= 1953 && year <= 2005;

//            String month = foundData.month;
//            int day = foundData.day;
//            String gender = foundData.gender;

            // Formatting birthday
//            String bdayString = String.format("%02d-%s-%04d", day, month, year);
//            LocalDate birthday = LocalDate.parse(
//                    bdayString,
//                    DateTimeFormatter.ofPattern("dd-MMMM-yyyy")
//            );

//            String formattedDate = getFormattedDate(birthday);
//            System.out.println("Formatted Birthday: " + formattedDate);
//            System.out.println("Gender: " + gender);
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

//    private static DayGenderResult findDayAndGender(int days, List<MonthData> dArray) {
//        int dayList = days;
//        String month = "";
//        String gender = (dayList < 500) ? "Male" : "Female";
//
//        // Adjust days if gender is female
//        if (dayList >= 500) {
//            dayList -= 500;
//        }
//
//        for (MonthData data : dArray) {
//            if (dayList > data.days) {
//                dayList -= data.days;
//            } else {
//                month = data.month;
//                break;
//            }
//        }
//
//        return new DayGenderResult(dayList, month, gender);
//    }

//    private static String getFormattedDate(LocalDate date) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
//        return date.format(formatter);
//    }
}
