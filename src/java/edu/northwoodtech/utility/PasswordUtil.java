/* 
 * Copyright 2022 Rene Bylander at Northwood Tech
 */

package edu.northwoodtech.utility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * @author Rene Bylander
 * @created Mar 29, 2017
 * @updated April, 2021 - Removed unused methods
 * @updated July 2022 - added description to return param of validatePassword
 */
public class PasswordUtil {

    /**
     * password is: salt.concat(password)
     * @param password
     * @return
     * @throws NoSuchAlgorithmException 
     */
    public static String hashPassword(String password)
            throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.reset();
        md.update(password.getBytes());
        byte[] mdArray = md.digest();
        StringBuilder sb = new StringBuilder(mdArray.length * 2);
        for(byte b : mdArray){
            int v = b & 0xff;
            if(v < 16){
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
            
        }//end for loop
        return sb.toString();   
    }
    
    public static String getSalt(){
        SecureRandom r = new SecureRandom();
        byte[] saltBytes = new byte[32];
        r.nextBytes(saltBytes);
        return Base64.getEncoder().encodeToString(saltBytes); 
    }
//    public static String getHashedGeneratedPassword(){
//        String password = generatePassword();
//        String hashed = hashPassword(password);
//    }
    
    /**
     * https://rubular.com/r/Q66wtCcTUJ
     * @param password is the plain text password
     * @return true if password is at least 10 characters
     * must contain uppercase, lowercase, numbers, and special character
     */
    public static boolean validatePassword(String password){
        boolean isValid = false;
        
        if(password.matches("(?=^.{10,}$)(?=.*\\d)(?=.*[!@#$%^&*]+)(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$")){
            isValid = true;
        }
        
        return isValid;
    }
    
    /**
     * a generated password may not adhere to our password policy
     * @return 
     */
    public static String generatePassword(){
        String charSet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz_-+!@#$%";
        String password = "";
        int start;
        int stop;
        int minLength = 10;
        
        for (int i = 0; i <= minLength; i++) {
                // get a random character from the chars string
                start = getRandomNumber(charSet.length());
                stop = start + 1;
                password += charSet.substring(start, stop);
        }
        
        return password;
    
    }
    /**
     * Used in generatePassword()
     * @param maxValue
     * @return 
     */
    private static int getRandomNumber(int maxValue){
        double randomNumber;
        randomNumber = Math.floor(Math.random() * maxValue);
        
        return (int)randomNumber;
    }

}//end of class
