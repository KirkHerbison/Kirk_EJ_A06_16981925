/* 
 * Copyright 2021 Rene Bylander at Northwood Technical College
 */

package edu.northwoodtech.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Rene Bylander
 * @created Jul 17, 2017
 * @updated Sept, 2020 - to take advantage of JDK 11 strip() method
 * and fix isAnyPositiveNumber which would not accept a single digit number
 * @updated August 2021 to split credit card validation into its own file
 * @updated May 2022 added validation for phone and selectedType
 */
public class ValidatorUtil {

    /**
     * Static methods can be access without
     * instantiating the ValidatorUtil class, you must
     * however, go through the class to get to the function
     * @param text: any String variable that you want to see 
     * if there is a value in it
     * @return isValid - true, there is a value. false there is not.
     */
    public static boolean hasText(String text){
        boolean isValid = false;

        if(text != null){
            //strip() the leading and trailing whitespace then check
            //if the text has a length
            if(text.strip().length() > 0 ){
                isValid = true;
            }
                
        }
        return isValid;
    }//hasText
    
    /**
     * Used with select options that have a "choose one" as option 0
     * This does not tell you which option, just that it is not "choose one"
     * @param id
     * @return 
     */
    public static boolean isValidSelectedType(int id){
        return id > 0;
    }
    /**
     * Works for 10 digit phone numbers only
     * You can send in a formatted phone and non-numerics are removed 
     * for validation purposes only(123)456-7890
     * @param text
     * @return 
     */
    public static boolean isValidPhone(String text){
        boolean isValid = false;
        text = replaceNonNumerics(text);
        if(text != null){
                    if(text.length() == 10)
            isValid = isPositiveWholeNumber(text);
        }

        
        return isValid;
    }
           
    public static boolean isPositiveWholeNumber(String text){
        boolean isValid = false;

        if(hasText(text)){
            if(text.matches("[0-9]+")){
                isValid = true;
            }              
        }
        return isValid;
    }//end isPositiveWholeNumber

    public static boolean isAlphabetic(String text) {
            //English language string only, with or without spaces
            // ^[a-zA-Z]+( [a-zA-z]+)*$
            boolean isValid = false;
           
            // Text only, no numbers, spaces or special characters
            //  [a-zA-Z]+
            //Allow a space but not a dash
            // "^\\p{L}+(?: \\p{L}+)*$"
            //Allows a space and/or dash
            // "^\\p{L}+(?:[ \\p{Pd}]\\p{L}+)*$"
            //String must start with at least one, not space
            //String can contain few words, but every word beside 
            //first must have space before it. Strings from any language
            //if(text.matches("^\\p{L}+(?: \\p{L}+)*$")) 
            if(hasText(text)){
                //work around for St. Paul
                if(text.contains(".")){
                    text = text.replaceAll("[\\.]+", "");
                }
                //this works for a space (La Duke) or a hypen (Lens-Lens)4/3/2020
                if(text.matches("^\\p{L}+(?:[\\s\\p{Pd}]\\p{L}+)*$"))
                    isValid = true;
                //try to get a dot and a space for St. Paul
                //not working yet
                //if(text.matches("^\\p{L}+(?:[\\s\\p{P}]\\p{L}+)*$"))
            }
           
            return isValid;

    } // End of isAlphabetic()

    public static boolean isAnyWholeNumber(String text){
        boolean isValid = false;
        
        if(hasText(text)){
            if(text.matches("[-?, 0-9]+")){
                isValid = true;
            }
                
        }
        return isValid;
    }//end isAnyWholeNumber

    public static boolean isAnyDecimalNumber(String text){
        /*
            ^           # Beginning of string
            [+-]?       # Optional plus or minus character
            (           # Followed by either:
            (           # Start of first option
            \d+         # One or more digits
            (\.\d*)?    # Optionally followed by: one decimal point and zero or more digits
            )           # End of first option
            |           # or
            (\.\d+)     # One decimal point followed by one or more digits
            )           # End of grouping of the OR options
            $           # End of string (i.e. no extra characters remaining)
        */
        boolean isValid = false;
        if(hasText(text)){
            if(text.matches("^[+-]?((\\d+(\\.\\d*)?)|(\\.\\d+))$")){
                isValid = true;           
            }
        }
        return isValid;
    }//end isAnyDecimalNumber
    
    public static boolean isAnyPositiveNumber(String text){
        boolean isValid = false;
        if(hasText(text)){
            if(text.matches("(\\+)?([0-9]+((\\.)?[0-9]+))")){
                isValid = true;           
            }
        }
           
        return isValid;
    }//end isAnyPositiveNumber
    
     //this will get rid of all non-numerical characters INCLUDING a dot
    public static String replaceNonNumerics(String text){
        //call the hasText to insure the variable text is not null and has data
        if(hasText(text)){
            text = text.replaceAll("[^\\d]", "");
        }
        return text;
    }
     
    public static String replaceCharacters(String text){
        //first argument is a regular express
        //replace all $ and , and - and ( and ) and spaces with an empty string
        if(hasText(text)){
            text = text.replaceAll("[\\$,\\,\\-,\\(,\\),\\s]+", "");
        }
        return text;
    }
    
    public static boolean isValidEmail(String emailAddress) {
        Pattern pattern;
        Matcher matcher;
        //This expression handles much more then just making sure the @ 
        //and ._ _ _ is correct...
        final String REGX_EMAIL= "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                     + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        //Pattern holds the regular expression
        pattern = Pattern.compile(REGX_EMAIL);
        //matcher object to regex a string against my pattern
        matcher = pattern.matcher(emailAddress);
        //return if email is valid (true) or not (false)
        return matcher.matches();
    }//end isValidEmail

   
    public static boolean isValidZipCode(String zipCode){
        //pattern matching for US zip code ONLY
        Pattern pattern;
        Matcher matcher;
        
        final String REGX_ZIP= "^[0-9]{5}(-[0-9]{4})?$";
        //Pattern holds the regular expression
        pattern = Pattern.compile(REGX_ZIP);
        //matcher object to regex a string against my pattern
        matcher = pattern.matcher(zipCode);
        //return if email is valid (true) or not (false)
        return matcher.matches();
    }//end isValidZipCode
    
}//end class
