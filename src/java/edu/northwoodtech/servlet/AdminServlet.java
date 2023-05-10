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
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "AdminServlet", urlPatterns = {"/AdminServlet"})
public class AdminServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response, String url)
            throws ServletException, IOException {
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String requestAction = request.getParameter("requestAction");
        //switch expresion used for the nav bar
        switch (requestAction.toLowerCase()) {
            case "index" ->
                forwardToIndex(request, response);
            case "employee" ->
                forwardToEmployee(request, response);
            case "logout" ->
                forwardToLogout(request, response);
            default ->
                forwardToError(request, response);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String requestAction = request.getParameter("requestAction");
        
            switch (requestAction.toLowerCase()) {

                //this case is for rent, it is called when the endUser enters their credit card information
                case "updatevehicle" -> {
                    
                    String[]allIds = request.getParameterValues("edit_id");
                    String[]allCosts = request.getParameterValues("costPerDay");
                    String[]allStatus = request.getParameterValues("isActiveVehicle");
                    String rowIndex = request.getParameter("edit_vehicle");
                    int row = Integer.parseInt(rowIndex);
                    
                    System.out.println(allIds[1]);
                    
                    
                    
                    
                    
                    forwardToError(request, response);
                    

                }
                //the default is error, should never happen
                default ->
                    forwardToError(request, response);
            }      
        
        
    }


        //forwards user to the homepage of the secure folder
    protected void forwardToIndex(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
                try {
                    request.getSession().setAttribute("creditCardTypes", RentalDb.getCreditCardTypes());
                    processRequest(request, response, "/secure/index.jsp");
                } catch (SQLException e) {
                    forwardToError(request, response);
                }
    }
    
    //forwards user to theemployee page
    protected void forwardToEmployee(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response, "/secure/employee.jsp");
    }
    
    //forwards user to the homepage and deletes session
    protected void forwardToLogout(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response, "/index.jsp");
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
