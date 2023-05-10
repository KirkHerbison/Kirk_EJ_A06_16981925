/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.northwoodtech.db;

import edu.northwoodtech.business.CreditCardType;
import edu.northwoodtech.business.EndUser;
import edu.northwoodtech.business.Rental;
import edu.northwoodtech.utility.DateUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

//works with the rental table
public class RentalDb {

    //This will update the credit card information for the logged in user,
    //it can only be called if the user already has a credit card
    //this takes in the request and uses information stored in the session to edit
    //
    public static void updateCreditCard(HttpServletRequest request) throws SQLException {

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        connection.setAutoCommit(false);
        PreparedStatement psCreditCard = null;

        EndUser endUser = (EndUser) request.getSession().getAttribute("endUserLogedin");
        int creditCardTypeId = 0;
        //gets the description form the vehicle_rental.jsp
        String desc = request.getParameter("creditCardTypes");
        //this sets the credit card id bassed on the description
        if (desc.equals("visa")) {
            creditCardTypeId = 1;
        } else if (desc.equals("mc")) {
            creditCardTypeId = 2;
        } else if (desc.equals("ds")) {
            creditCardTypeId = 3;
        } else {
            creditCardTypeId = 4;
        }

        //this is the sql for updating a credit card. it takes all credit card information
        String creditCardSQL = "UPDATE credit_card"
                + " SET credit_card_type_id = ?, card_number= ?, card_expiry = ?, card_cvv = ? "
                + "WHERE end_user_id = ? ";
        try {
            //order matters
            psCreditCard = connection.prepareStatement(creditCardSQL, Statement.RETURN_GENERATED_KEYS);
            psCreditCard.setInt(1, creditCardTypeId);
            psCreditCard.setString(2, request.getParameter("cardNumber"));
            psCreditCard.setString(3, request.getParameter("cardExpire"));
            psCreditCard.setString(4, request.getParameter("cvvNumber"));
            psCreditCard.setInt(5, endUser.getId());
            psCreditCard.executeUpdate();

            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            String ePlus = "RentalDb: public register() - " + e;
            throw new SQLException(ePlus);
        } finally {
            DbHelper.closePreparedStatment(psCreditCard);
            pool.freeConnection(connection);
        }
    }

    //this is used to insert a new credit card into the database,
    //only one credit card may be insterted per user
    public static void insertCreditCard(HttpServletRequest request) throws SQLException {

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        connection.setAutoCommit(false);

        PreparedStatement psCreditCard = null;

        EndUser endUser = (EndUser) request.getSession().getAttribute("endUserLogedin");
        int creditCardTypeId = 0;
        String desc = request.getParameter("creditCardTypes");
        if (desc.equals("visa")) {
            creditCardTypeId = 1;
        } else if (desc.equals("mc")) {
            creditCardTypeId = 2;
        } else if (desc.equals("ds")) {
            creditCardTypeId = 3;
        } else {
            creditCardTypeId = 4;
        }

        //the sql for inserting a credit card, all verification is done before getting to this point so no errors SHOULD occur
        //will do rollback if something does happen
        String creditCardSQL = "INSERT INTO credit_card(end_user_id, credit_card_type_id, card_number, "
                + "card_expiry, card_cvv) "
                + "VALUES(?,?,?,?,?)";
        try {
            //order matters
            psCreditCard = connection.prepareStatement(creditCardSQL, Statement.RETURN_GENERATED_KEYS);

            psCreditCard.setInt(1, endUser.getId());
            psCreditCard.setInt(2, creditCardTypeId);
            psCreditCard.setString(3, request.getParameter("cardNumber"));
            psCreditCard.setString(4, request.getParameter("cardExpire"));
            psCreditCard.setString(5, request.getParameter("cvvNumber"));

            psCreditCard.executeUpdate();

            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            String ePlus = "RentalDb: public register() - " + e;
            throw new SQLException(ePlus);
        } finally {
            DbHelper.closePreparedStatment(psCreditCard);
            pool.freeConnection(connection);
        }
    }

    //this is used to update a end users rental information. a endUser may only
    //have one rental so we use this if they change to a different rental
    public static void updateRental(HttpServletRequest request) throws SQLException {

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        connection.setAutoCommit(false);

        PreparedStatement psCreditCard = null;

        EndUser endUser = (EndUser) request.getSession().getAttribute("endUserLogedin");
        Rental rental = (Rental) request.getSession().getAttribute("rental");
        LocalDate dateStart = (LocalDate) request.getSession().getAttribute("dateStartStored");
        LocalDate dateEnd = (LocalDate) request.getSession().getAttribute("dateEndStored");

        //sql for updating a logged in end users rental information
        String creditCardSQL = "UPDATE rental"
                + " SET vehicle_id = ?, rental_start_date = ?, rental_end_date = ? "
                + "WHERE end_user_id = ? ";
        try {
            //order matters
            psCreditCard = connection.prepareStatement(creditCardSQL, Statement.RETURN_GENERATED_KEYS);

            psCreditCard.setInt(1, rental.getVehicleId());
            psCreditCard.setDate(2, DateUtil.getSqLDate(dateStart));
            psCreditCard.setDate(3, DateUtil.getSqLDate(dateEnd));
            psCreditCard.setInt(4, endUser.getId());
            psCreditCard.executeUpdate();

            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            String ePlus = "RentalDb: public register() - " + e;
            throw new SQLException(ePlus);
        } finally {
            DbHelper.closePreparedStatment(psCreditCard);
            pool.freeConnection(connection);
        }
    }

    //this is used to insert a endUser rental into the database
    //only one may be inserted per endUser
    //verification should be done before this point to make sure there
    //isn't more than one record
    public static void insertRental(HttpServletRequest request) throws SQLException {

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        connection.setAutoCommit(false);

        PreparedStatement psRental = null;

        Rental rental = (Rental) request.getSession().getAttribute("rental");
        EndUser endUser = (EndUser) request.getSession().getAttribute("endUserLogedin");
        LocalDate dateStart = (LocalDate) request.getSession().getAttribute("dateStartStored");
        LocalDate dateEnd = (LocalDate) request.getSession().getAttribute("dateEndStored");
        String rentalSQL = "INSERT INTO rental"
                + "(end_user_id, vehicle_id, rental_start_date, rental_end_date) "
                + "VALUES(?,?,?,?)";
        try {
            //order matters
            psRental = connection.prepareStatement(rentalSQL, Statement.RETURN_GENERATED_KEYS);

            psRental.setInt(1, endUser.getId());
            psRental.setInt(2, rental.getVehicleId());
            psRental.setDate(3, DateUtil.getSqLDate(dateStart));
            psRental.setDate(4, DateUtil.getSqLDate(dateEnd));

            psRental.executeUpdate();

            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            String ePlus = "RentalDb: public register() - " + e;
            throw new SQLException(ePlus);
        } finally {
            DbHelper.closePreparedStatment(psRental);
            pool.freeConnection(connection);
        }
    }
    
    
    //this gets the list of credit card tpyes
    
    //this method creates and returns a list of vehicles from the databse
    public static List<CreditCardType> getCreditCardTypes() throws SQLException{
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        
        PreparedStatement ps = null;
        ResultSet rs = null;
         
        //creating the vehicle array from the database
        List<CreditCardType> creditCardTypes = new ArrayList<>();
        CreditCardType creditCardType;
        
        //sql which will return all vehciles from the databse
        String sql = "SELECT id, short_desc, long_desc, active "
                + "FROM credit_card_type";
        
        
        try{
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            
            //this while loop crates the credit card type objects and adds them to a list
            while(rs.next()){
                creditCardType = new CreditCardType(
                        rs.getInt("id"),
                        rs.getString("short_desc"),
                        rs.getString("long_desc"),
                        rs.getBoolean("active")); 
                creditCardTypes.add(creditCardType);      
            }
            
        }catch(SQLException e){
            throw new SQLException("get() - UnitTypeDb: " + e);
        }
        finally{
            DbHelper.closeResultSet(rs);
            DbHelper.closePreparedStatment(ps);
            pool.freeConnection(connection);
        }
        return creditCardTypes;
    }
}
