package com.maathru.backend.External.utils;

public class EmailUtils {
    public static String getEmailMessage(String name, String password) {
        return "Hello " + name + ",\n\n" +
                "Your new account has been created. This is your system generated password: "
                + password + "\n\n" +
                "Using above password login to our system.(Please change this password your first login)\n\n" +
                "The Support Team";
    }
}
