/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

/**
 *
 * @author THANHDAT
 */
public class Encrypt {

    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
    
//    public static void main(String[] args) {
//        Encrypt encrypt = new Encrypt();
//        
//        String password = "hello";
//        encrypt.hashPassword(password);
//        System.out.println(encrypt.hashPassword(password));
//        
//        boolean test = encrypt.checkPassword("hello", encrypt.hashPassword(password));
//        System.out.println(test);
//    }
}
