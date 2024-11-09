package com.maathru.backend.External.utils;

public class EmailUtils {
    public static String getEmailMessage(String name, String password) {
        return String.format("""
                Hello %s,
                                
                Your new account has been created. This is your system generated password: %s
                                
                Using above password login to our system.(Please change this password your first login)
                                
                Thank you,
                Team Maathru.
                """,name,password);
    }
}
