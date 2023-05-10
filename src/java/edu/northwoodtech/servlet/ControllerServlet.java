package edu.northwoodtech.servlet;

import edu.northwoodtech.business.EndUser;
import edu.northwoodtech.business.Rental;
import edu.northwoodtech.db.EndUserDb;
import edu.northwoodtech.db.RentalDb;
import edu.northwoodtech.helper.HelperAttribute;
import edu.northwoodtech.helper.HelperCreditCard;
import edu.northwoodtech.helper.HelperEndUser;
import edu.northwoodtech.helper.HelperFormDisplays;
import edu.northwoodtech.helper.HelperRental;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//this is the main servlet
public class ControllerServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response, String url)
            throws ServletException, IOException {
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    //method that will send the user to different pages of the website in this
    //website this is used for the nav bar
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String requestAction = request.getParameter("requestAction");
        //switch expresion used for the nav bar
        switch (requestAction.toLowerCase()) {
            case "index" ->
                forwardToIndex(request, response);
            case "register" ->
                forwardToRegister(request, response);
            case "vehicles" ->
                forwardToVehicles(request, response);
            case "profile" ->
                forwardToProfile(request, response);
            case "login" ->
                forwardToLogin(request, response);
            default ->
                forwardToError(request, response);
        }
    }

    //method that looks for the user submited.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String requestAction = request.getParameter("requestAction");
        String errorMessage = "";
        String errorMessageVehicles = "";

        //this makes sure requestAction is not null, if it is not null we check
        //to see if any buttons were clicked (we use this for our double button
        //on the vehicle rental confirmation page)
        if (request.getParameter("requestAction") != null) {

            switch (requestAction.toLowerCase()) {

                //this case is for rent, it is called when the endUser enters their credit card information
                case "rent" -> {

                    //this validates the card number and type
                    if (HelperCreditCard.validateCardNumberAndType(request)) {
                        //this validates the cvv number
                        if (HelperCreditCard.validateCvvNumber(request)) {
                            //this validates the expiry, if this is valid all verification is valid
                            // this will then create a credit card and rental, and update them in the database
                            //this also generates a confirmation number which will be used on the
                            //confirmation page which we will be sending the user to
                            if (HelperCreditCard.validateExpiry(request)) {
                                HelperCreditCard.createRecord(request);
                                HelperRental.createRecord(request);
                                request.getSession().setAttribute("confirmation", HelperRental.getConfirmationNumber());
                                forwardToConfirmation(request, response);
                                //displays an error message about the expiry
                            } else {
                                errorMessage = "Invalid Expiry.";
                                request.setAttribute("errorMessage", errorMessage);
                                forwardToVehicleRental(request, response);
                            }
                            //displays an error message about the cvv
                        } else {
                            errorMessage = "Invalid cvv number.";
                            request.setAttribute("errorMessage", errorMessage);
                            forwardToVehicleRental(request, response);
                        }
                        //displays an error message about the card number or type    
                    } else {
                        errorMessage = "Invalid credit card number for selected credit card type.";
                        request.setAttribute("errorMessage", errorMessage);
                        forwardToVehicleRental(request, response);
                    }
                }

                //this case is used for the endUser registration
                //we will check to make sure all information is entered correctly
                //we will then check to make sure a similar user does not exist
                case "results" -> {
                    //this validates the endUser registration is correct
                    if (HelperEndUser.validateEndUserInformation(request)) {
                        EndUser endUser = HelperEndUser.getEndUser(request);
                        //this try catch attempts to create a new endUser in the database
                        try {
                             EndUser newEndUser = new EndUser();
                            if(request.getParameter("endUserLogedinId").equals("-1")){
                                newEndUser = EndUserDb.register(endUser);
                            }
                            else{
                                request.getSession().removeAttribute("endUserLogedin");
                                EndUserDb.updateEndUser(endUser, request.getParameter("endUserLogedinId"));
                                newEndUser = endUser;
                            }


                            
                            
                            EndUser endUserLogedin = EndUserDb.validateUsername(newEndUser.getUsername());

                            String formatedPhone = HelperFormDisplays.formatPhone(endUserLogedin.getEndUserPhone());
                            request.getSession().setAttribute("endUserLogedin", endUserLogedin);
                            request.getSession().setAttribute("formatedPhone", formatedPhone);
                            forwardToResults(request, response);
                            //if an error was thrown the endUser probably already exists
                        } catch (SQLException e) {
                            errorMessage = "Please review your form, sql error";
                            request.setAttribute("errorMessage", errorMessage);
                            forwardToRegister(request, response);
                        }
                    } else {
                        errorMessage = "Please review your form, it did not validate";
                        request.setAttribute("errorMessage", errorMessage);
                        forwardToRegister(request, response);
                    }
                }
                //this case is for when the vehcile page is going to go to the rental confirmation page
                //this sets everything to session about the vechile selection
                case "rental" -> {
                    LocalDate dateStartStored = LocalDate.parse(request.getParameter("startTime"));
                    LocalDate dateEndStored = LocalDate.parse(request.getParameter("endTime"));
                    request.getSession().setAttribute("dateStartStored", dateStartStored);
                    request.getSession().setAttribute("dateEndStored", dateEndStored);
                    //this validates that a vehicle has been selected
                    if (HelperRental.validateSelected(request)) {
                        request.getSession().setAttribute("radioValue", request.getParameter("vehicle_id"));
                        //this validates all of the date stuff
                        if (HelperRental.validateDates(request)) {
                            Map<String, Rental> endUserRentalMap = new HashMap<>();
                            endUserRentalMap.put("rental", HelperRental.getSelectedVehicle(request));
                            HelperAttribute.setSessionAttributes(request, endUserRentalMap);
                            forwardToVehicleConfirmation(request, response);
                        } else {

                            errorMessageVehicles = "Start Date must be before the end date";
                            request.setAttribute("errorMessageVehicles", errorMessageVehicles);
                            forwardToVehicles(request, response);
                        }
                    } else {
                        errorMessageVehicles = "Please select a vehicle to rent";
                        request.setAttribute("errorMessageVehicles", errorMessageVehicles);
                        forwardToVehicles(request, response);
                    }
                }
                //the default is error, should never happen
                default ->
                    forwardToError(request, response);
            }
        } //this is where our double button one form comes into play
        else {
            //if the edit vehicle button is clicked the user will be sent
            //back the the vehile page to make changes
            if (request.getParameter("edit_vehicle_button") != null) {
                forwardToVehicles(request, response);

                //if the rent vehicle button is clicked the user will be sent to the
                //page with credit card information    
            } else if (request.getParameter("rent_vehicle_button") != null) {
                try {
                    request.getSession().setAttribute("creditCardTypes", RentalDb.getCreditCardTypes());
                    forwardToVehicleRental(request, response);
                } catch (SQLException e) {
                    forwardToVehicleConfirmation(request, response);
                }

            } //default error page
            else {
                forwardToError(request, response);
            }
        }
    }

    //forwards user to the homepage
    protected void forwardToIndex(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        HelperAttribute.removeSessionAttributes(request);
        processRequest(request, response, "/index.jsp");
    }

    //forwards user to the confirmation page
    protected void forwardToConfirmation(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response, "/confirmation.jsp");
    }

    //forwards to user to the Register page
    protected void forwardToRegister(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response, "/register.jsp");
    }

    //forwards to user to the Register results page
    protected void forwardToResults(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response, "/results.jsp");
    }

    //forwards to user to the vehicle_rental page
    protected void forwardToVehicleRental(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        processRequest(request, response, "/vehicle_rental.jsp");
    }

    //forwards to user to the login page
    protected void forwardToLogin(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response, "/login.jsp");
    }

    //forwards to user to the profile page
    protected void forwardToProfile(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response, "/profile.jsp");
    }

    //forwards to user to the vehicles page
    protected void forwardToVehicles(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response, "/vehicles.jsp");
    }

    //forwards to user to the vehicle confirmation page
    protected void forwardToVehicleConfirmation(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response, "/vehicle_confirmation.jsp");
    }

    //forwards to user to the error 404 page
    protected void forwardToError(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response, "/error_404.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Servlet for assignment A05";
    }

}
