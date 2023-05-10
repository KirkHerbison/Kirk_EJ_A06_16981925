package edu.northwoodtech.helper;

import edu.northwoodtech.business.EndUser;
import edu.northwoodtech.db.RentalDb;
import edu.northwoodtech.utility.CreditCardUtil;
import java.sql.SQLException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

//this is a helper class that is used for various credit card methods
public class HelperCreditCard extends HttpServlet {

    //validation The credit card type fits the credit card number
    //returns true only if the card number and type are valid together
    public static boolean validateCardNumberAndType(HttpServletRequest request) {
        boolean isValid = false;
        if (CreditCardUtil.isValidCcTypeWithNumber(request.getParameter("cardNumber"), request.getParameter("creditCardTypes"))) {
            isValid = true;
        }
        return isValid;
    }

    //validation The ccv number
    //returns true only if the card cvv number is valid for the card type
    public static boolean validateCvvNumber(HttpServletRequest request) {
        boolean isValid = false;
        if (CreditCardUtil.isValidCvv(request.getParameter("cvvNumber"), request.getParameter("creditCardTypes"))) {
            isValid = true;
        }
        return isValid;
    }

    //validation The Expiry date
    //returns true only if the expiry is valid
    public static boolean validateExpiry(HttpServletRequest request) {
        boolean isValid = false;
        if (CreditCardUtil.isValidExpiry(request.getParameter("cardExpire"))) {
            isValid = true;
        }
        return isValid;
    }

    //this method takes in all information from the request/session and attempts to either insert or update
    //if the loged in end user has no credit card on record they will insert otherwise they will update
    //if an error occurs during this (it should not) a statment will be printed out to consol
    public static void createRecord(HttpServletRequest request) {
        try {
            EndUser endUserLogedin = (EndUser) request.getSession().getAttribute("endUserLogedin");
            if (endUserLogedin.getCreditCard() == null) {
                RentalDb.insertCreditCard(request);
            } else {
                RentalDb.updateCreditCard(request);
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

}//end of class
