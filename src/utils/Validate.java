/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

/**
 *
 * @author ACER
 */
public class Validate {
    public static boolean isValidUsername(String username) {
        return username.matches("[a-zA-Z0-9_]{6,20}");
    }
    public static boolean isValidPassword(String password) {
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        return password.matches(regex);
    }
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return email.matches(emailRegex);
    }
    public static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("\\d{10,11}"); 

    }
    public  static boolean isMatchPass(String pass, String confirmPass) {
        return pass.equals(confirmPass);
    
    
    
    }
    
}
