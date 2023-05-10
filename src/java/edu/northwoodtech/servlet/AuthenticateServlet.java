/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package edu.northwoodtech.servlet;

import edu.northwoodtech.business.EndUser;
import edu.northwoodtech.db.EndUserDb;
import edu.northwoodtech.db.RentalDb;
import edu.northwoodtech.helper.HelperFormDisplays;
import edu.northwoodtech.helper.HelperLoadPageData;
import edu.northwoodtech.utility.PasswordUtil;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//this is the servlet that is used for login validation
public class AuthenticateServlet extends HttpServlet {

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
        switch (requestAction.toLowerCase()) {
            case "logout" ->
                forwardToLogin(request, response);
            default ->
                forwardToError(request, response);
        }
            
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //these are all of the fields from the login page
        String requestAction = request.getParameter("requestAction");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //this is looking for requestAction, the request should always be login right now
        //I have a switch statment incase I add something to this servlet
        switch (requestAction.toLowerCase()) {
            case "login" -> {
                try {
                    //this validates to make sure the user information entered is
                    //valid and does not break
                    //once it validates a end user object will be created and set to session
                    if (EndUserDb.validateUsername(username) != null) {
                        EndUser endUserLogedin = EndUserDb.validateUsername(username);
                        String salt = endUserLogedin.getSalted();
                        String salted = salt.concat(password);
                        try {
                            String hashed = PasswordUtil.hashPassword(salted);

                            if (hashed.equals(endUserLogedin.getHashed())) {
                                String formatedPhone = HelperFormDisplays.formatPhone(endUserLogedin.getEndUserPhone());
                                request.getSession().setAttribute("endUserLogedin", endUserLogedin);
                                request.getSession().setAttribute("formatedPhone", formatedPhone);
                                String endUserType = endUserLogedin.getUserType();
                                
                                if(endUserType.equals("Manager") || endUserType.equals("Developer") || endUserType.equals("Staff Employee")){
                                    forwardToSecure(request, response);
                                }else{
                                    forwardToResults(request, response);
                                }
                                
                            } else {
                                forwardToError(request, response);
                            }

                        } catch (NoSuchAlgorithmException ex) {
                            throw new ServletException(ex);
                        }
                    } else {
                        forwardToError(request, response);
                    }
                } catch (SQLException ex) {
                    forwardToError(request, response);
                }
            }
        }
    }
    
    //forwards to the login page
    protected void forwardToLogin(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        session.invalidate();
        processRequest(request, response, "/login.jsp");
    }

    //forwards to the index page
    protected void forwardToIndex(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        processRequest(request, response, "/index.jsp");
    }

    //forwards to a login page with an error message
    protected void forwardToError(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        processRequest(request, response, "/login_error.jsp");
    }

    //forwards to user to the Register results page
    protected void forwardToResults(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response, "/results.jsp");
    }
    
    //forwards to user to the Secure index
    protected void forwardToSecure(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
                try {
                    request.getSession().setAttribute("creditCardTypes", RentalDb.getCreditCardTypes());
                } catch (SQLException e) {
                    forwardToError(request, response);
                }
        processRequest(request, response, "/secure/index.jsp");
    }

    //forwards to user to the vehicles page 
    protected void forwardToVehicles(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        try {
            HelperLoadPageData.loadPageDataVehicle(request);
        } catch (SQLException ex) {
            throw new ServletException();
        }
        processRequest(request, response, "/vehicles.jsp");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
