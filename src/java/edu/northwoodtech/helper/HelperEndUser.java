
package edu.northwoodtech.helper;

import edu.northwoodtech.business.EndUser;
import edu.northwoodtech.utility.PasswordUtil;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import edu.northwoodtech.utility.ValidatorUtil;
import java.time.LocalDateTime;
import javax.servlet.http.HttpSession;


//this is a helper class with various methods used for dealing with a EndUser 
public class HelperEndUser extends HttpServlet {
    
    //validation for the endUser and returns true if no it is valid
    //this is used during the registration proccess to make sure all required
    //information is entered and all entered information is correct
    //this also set all information to sesion if it is validated so the user
    //does not have to re enter valid information
    public static boolean validateEndUserInformation(HttpServletRequest request) {
        boolean isValid = true;
        HttpSession session = request.getSession();
        
        
        //validates the first and last name
        if(!ValidatorUtil.hasText(request.getParameter("endUserFirstName"))){
            request.setAttribute("endUserFirstName","");
            isValid = false;
        }else{
            session.setAttribute("endUserFirstName", request.getAttribute("endUserFirstName"));  
        }
        if(!ValidatorUtil.hasText(request.getParameter("endUserLastName"))){
            request.setAttribute("endUserLastName","");
            isValid = false;
        }else{
            session.setAttribute("endUserLastName", request.getAttribute("endUserLastName"));       
        }


        //validates the phone number
        if(!ValidatorUtil.isValidPhone(request.getParameter("endUserPhone"))){
            isValid = false;
        }
        else{
            session.setAttribute("endUserPhone", request.getAttribute("endUserPhone"));
        }
        //validates the email 
        if(!ValidatorUtil.isValidEmail(request.getParameter("endUserEmail"))&&
           ValidatorUtil.hasText(request.getParameter("endUserEmail"))){
                
                request.setAttribute("endUserEmail", null);
                isValid = false;
        }
        else{
            if(ValidatorUtil.hasText(request.getParameter("endUserEmail"))){
                session.setAttribute("endUserEmail", request.getAttribute("endUserEmail"));
            }
            else{
                session.setAttribute("endUserEmail", null);
            }
        }  
        //validates if it is okay to text
        if(request.getParameter("endUserText") !=null){
            session.setAttribute("endUserText", "checked");
        }
        else{
            session.setAttribute("endUserText", "");
        }
        
        //validates the password
        if(!PasswordUtil.validatePassword(request.getParameter("password"))){
            isValid = false;
        }
        return isValid;
    }
    
    public static EndUser getEndUser(HttpServletRequest request){
        HttpSession session = request.getSession();
        LocalDateTime dateAdded = LocalDateTime.now();
        
        String endUserFirstName = request.getParameter("endUserFirstName");
        System.out.println("first " + endUserFirstName);
        String endUserLastName = request.getParameter("endUserLastName");
        String endUserPhone = request.getParameter("endUserPhone");
        System.out.println("end User phone: " + endUserPhone);
        String endUserEmail = request.getParameter("endUserEmail");
        String username = request.getParameter("username");
        boolean endUserText = request.getParameter("endUserText") !=null;
        String salted = request.getParameter("password");
        String hashed = request.getParameter("password");
        Boolean isActive = request.getParameter("isActive") !=null;
        if(request.getParameter("dateAdded") != null){
                    dateAdded = LocalDateTime.parse(request.getParameter("dateAdded"));
        }
        //userType is hardcodded to Customer here, the user will always be a customer when registering
        //this makes it easier when converting
        EndUser endUser = new EndUser(endUserFirstName,endUserLastName,endUserPhone,endUserText,
                endUserEmail, username, salted, hashed ,isActive, dateAdded, "Customer");
        session.setAttribute("endUser", endUser);     
        
        return endUser;
    }


}//end of class
