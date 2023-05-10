package edu.northwoodtech.helper;

import edu.northwoodtech.utility.DateUtil;
import static edu.northwoodtech.utility.ValidatorUtil.replaceNonNumerics;
import java.math.BigDecimal;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest; 
import java.text.NumberFormat;
import java.time.LocalDateTime;

//this helper class has various methods for formating display information
public class HelperFormDisplays extends HttpServlet {

    //this method just takes in a LocalDateTime and formats it to our desired date format
    public static String formatDate(LocalDateTime date) {
        String formatedDate = "";
        formatedDate = DateUtil.getFormattedDateTimeLong(date);
        return formatedDate;
    }

    //this just takes in request so that the formated currency can be stored in the request
    public static void formatCurrency(HttpServletRequest request) {
        BigDecimal bg = new BigDecimal(12357.9876);

        request.setAttribute("us", getFormatedCost(bg));
    }

    //this method takes in the cost and formats it to display in a way that looks nice
    public static String getFormatedCost(BigDecimal cost) {
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        return currency.format(cost);
    }

    //this method takes in a end users phone and formats it to display in a way that looks nice
    public static String formatPhone(String phone) {
        String formatedPhone = "";
        phone = replaceNonNumerics(phone);
        formatedPhone = "(" + phone.substring(0, 3) + ") " + phone.substring(3, 6) + "-" + phone.substring(6, 10);
        return formatedPhone;
    }

}//end of class
