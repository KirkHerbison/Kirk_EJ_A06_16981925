/*
 * Copyright 2021 Rene Bylander and Northwood Tech.
 */

package edu.northwoodtech.utility;

import static edu.northwoodtech.utility.ValidatorUtil.hasText;
import static edu.northwoodtech.utility.ValidatorUtil.isPositiveWholeNumber;
import static edu.northwoodtech.utility.ValidatorUtil.replaceNonNumerics;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//this is a utility class that helps with the credit card
public class CreditCardUtil {

     /**
     * Determines if the credit card number is valid based on the Luhn
     * Algorithm. Credit card must be between 13 and 16 characters long
     * This function can be tested using these numbers 
     * Visa: 4111111111111111
     * MasterCard: 5555555555554444
     * AMEX: 378282246310005
     * Discover: 6011000990139424, 6011507698414636
     * @param ccNum a string representing a credit card number
     * @return 
     */
    public static boolean isValidCreditCard(String ccNum){

        int index;
        long number;
        boolean alternate = false;
        long total = 0;
        boolean isValid = false;

        
        if(hasText(ccNum)){
            ccNum = replaceNonNumerics(ccNum);
            index = ccNum.length();
            
            if(!isPositiveWholeNumber(ccNum)){
                isValid = false;
            }
            else{
                if(ccNum.length() > 12 && ccNum.length() < 17){
                    while(index-- > 0){
                        number = Long.parseLong(ccNum.substring(index, index + 1));
                        if(alternate){
                            number *= 2; 

                            if(number > 9)
                                number -= 9; 

                        }
                        total += number;  

                        alternate = !alternate;
                    }//end of while loop

                    isValid = (total % 10) == 0;

                }//end if for length check
                else{
                    isValid = false;
                }//end else for length check
            }//end else for !isPositiveWholeNumber

        }//end hasText()
        return isValid;        
    }//end isValidCreditCard
    
    public static boolean isValidCcTypeWithNumber(String ccNumber, String cardTypeShortDesc){
       
        Pattern pattern = null;
        Matcher matcher;
        boolean isValid = false;
        
        //Remove any non-numeric values
        ccNumber = replaceNonNumerics(ccNumber);

        //regular expression are from the below website
        //https://www.regular-expressions.info/creditcard.html
        
        switch(cardTypeShortDesc.toLowerCase()){
            case "visa":
                pattern = Pattern.compile("^4[0-9]{12}(?:[0-9]{3})?$");
                break;
            case "ds":
                pattern = Pattern.compile("^6(?:011|5[0-9]{2})[0-9]{12}$");
                break;
            case "amex":
                pattern = Pattern.compile("^3[47][0-9]{13}$");
                break; 
            case "mc":
                pattern = Pattern.compile("^(?:5[1-5][0-9]{2}|222[1-9]|22[3-9][0-9]|2[3-6][0-9]{2}|27[01][0-9]|2720)[0-9]{12}$");
                //pattern = Pattern.compile("^5[1-5][0-9]{14}$");
                //alternate pattern for mc
                //^222[1-9]|22[3-9][0-9]|2[3-6][0-9]{2}|27[01][0-9]|2720)[0-9]{12}
                break;
            default:
                break;       
        }
        //if pattern is not checked for null, then the matcher part of
        //pattern.matcher has the warning dereferncing possible null pointer
        if(pattern != null){
            matcher = pattern.matcher(ccNumber);
            isValid = matcher.matches();
        }
        
        return isValid;
    }
    
    public static boolean isValidCvv(String cvv, String cardTypeShortDesc){
        int digits;
        boolean isValid = false;
        
        if(isPositiveWholeNumber(cvv)){
            digits = Integer.parseInt(cvv);
            //the numbers in a cvv must be greater than zero 
            //(ex: 000 is not allowed).
            if(digits > 0){
                switch(cardTypeShortDesc.toLowerCase()){
                    case "visa":   
                    case "ds":    
                    case "mc":
                        if(cvv.length() == 3){
                           isValid = true;
                        } 
                       break;
                    case "amex": 
                        if(cvv.length() == 4){
                           isValid = true;
                        } 
                        break;
                    default:
                        break;       
                }
            }
        }
        
        return isValid;
    }
    
    public static boolean isValidExpiry(String text) {
        boolean isValid = true;
//regex provided by Thomas Dean, Jr. Nov, 2020
        if (text.matches("(?:0[1-9]|1[0-2])/[0-9]{4}")) {

            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
                DateTimeFormatter todayFormatter = DateTimeFormatter.ISO_DATE;
                YearMonth expiryYM = YearMonth.parse(text, formatter);
                LocalDate today = LocalDate.now();

                YearMonth todayYM = YearMonth.parse(today.toString(), todayFormatter);

                if (expiryYM.isBefore(todayYM) || expiryYM.equals(todayYM)) {
                    isValid = false;
                }

            }//end try 
            catch (DateTimeParseException p) {
                System.out.println("parse error" + p);
                isValid = false;
            }
        }//end of if text.matches 
        else {
            isValid = false;
        }

        return isValid;
        
    }//end method

}//end of class
