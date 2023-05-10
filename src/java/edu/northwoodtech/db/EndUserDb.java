/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.northwoodtech.db;

import edu.northwoodtech.business.CreditCard;
import edu.northwoodtech.business.CreditCardType;
import edu.northwoodtech.business.EndUser;
import edu.northwoodtech.business.Rental;
import edu.northwoodtech.utility.DateUtil;
import edu.northwoodtech.utility.PasswordUtil;
import static edu.northwoodtech.utility.PasswordUtil.getSalt;
import static edu.northwoodtech.utility.ValidatorUtil.replaceNonNumerics;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;

//works with the EndUser database
public class EndUserDb {

    //takes in a EndUser object, likely from the registration page, and verifys the information.
    //if the information validates the endUser information will be put into the
    //end user table and the credentials table
    //it returns a endUser object that is either null or not null for verification on the servlet
    public static EndUser register(EndUser endUser) throws SQLException, ServletException {

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        connection.setAutoCommit(false);
        PreparedStatement psEndUser = null;
        PreparedStatement psEndUserAccess = null;
        //creating salt for password
        String salt = getSalt();
        String hashed;
        // .getSalted() returns the users entered password for register/
        String salted = salt.concat(endUser.getSalted());
        //try catch for the password hash
        try {
            hashed = PasswordUtil.hashPassword(salted);
        } catch (NoSuchAlgorithmException ex) {
            throw new ServletException(ex);
        }

        int rows = 0;
        int newEndUserId = 0;

        //this is the sql for insterting a endUser into endUser table
        String endUserSQL = "INSERT INTO end_user(first_name, last_name, phone_number, "
                + "can_text, email_address, active) "
                + "VALUES(?,?,?,?,?,?)";
        //this is the sql for insterting a end users credentials into credentials table
        String endUserAccessSQL = "INSERT INTO end_user_access(end_user_id, user_name, "
                + "salted, hashed, end_user_type_id, active) "
                + "VALUES(?,?,?,?,?,?)";

        //this try catch attempts to do all inputs, if there is an error it will be sent to catch and do a rollback
        try {
            //order matters
            psEndUser = connection.prepareStatement(endUserSQL, Statement.RETURN_GENERATED_KEYS);

            psEndUser.setString(1, endUser.getEndUserFirstName());
            psEndUser.setString(2, endUser.getEndUserLastName());
            psEndUser.setString(3, replaceNonNumerics(endUser.getEndUserPhone()));
            psEndUser.setBoolean(4, endUser.getEndUserText());
            if (endUser.getEndUserEmail() == null) {
                psEndUser.setNull(5, java.sql.Types.VARCHAR);
            } else {
                psEndUser.setString(5, endUser.getEndUserEmail());
            }
            psEndUser.setBoolean(6, true);

            rows = psEndUser.executeUpdate();

            if (rows > 0) {
                ResultSet rs = psEndUser.getGeneratedKeys();
                if (rs.next()) {
                    newEndUserId = rs.getInt(1);
                    //create getter and setter for EndUser.endUser.Id
                    endUser.setId(newEndUserId);
                }
            }
            psEndUserAccess = connection.prepareStatement(endUserAccessSQL);
            psEndUserAccess.setInt(1, endUser.getId());
            psEndUserAccess.setString(2, endUser.getUsername());
            psEndUserAccess.setString(3, salt);
            psEndUserAccess.setString(4, hashed);
            psEndUserAccess.setInt(5, 4);
            psEndUserAccess.setBoolean(6, true);
            psEndUserAccess.executeUpdate();

            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            String ePlus = "EndUserDb: public register() - " + e;
            throw new SQLException(ePlus);
        } finally {
            DbHelper.closePreparedStatment(psEndUser);
            DbHelper.closePreparedStatment(psEndUserAccess);
            pool.freeConnection(connection);
        }

        return endUser;
    }

    //update enduser from the register page
    public static void updateEndUser(EndUser endUser, String id) throws SQLException, ServletException {

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        connection.setAutoCommit(false);

        PreparedStatement psEndUser = null;
        PreparedStatement psEndUserAccess = null;

        //creating salt for password
        String salt = getSalt();
        String hashed;
        // .getSalted() returns the users entered password for register/
        String salted = salt.concat(endUser.getSalted());
        //try catch for the password hash
        try {
            hashed = PasswordUtil.hashPassword(salted);
        } catch (NoSuchAlgorithmException ex) {
            throw new ServletException(ex);
        }

        //sql for updating a logged in end users rental information
        String endUserSQL = "UPDATE end_user "
                + "SET first_name = ?, last_name = ?, phone_number = ?, can_text = ?, email_address = ?, date_modified =  now() "
                + "WHERE id = ? ";

        //this is the sql for updating a end users credentials into credentials table
        String endUserAccessSQL = "UPDATE end_user_access "
                + "Set user_name = ?, salted = ?, hashed = ? "
                + "WHERE end_user_id = ?";

        try {
            //order matters
            psEndUser = connection.prepareStatement(endUserSQL);

            psEndUser.setString(1, endUser.getEndUserFirstName());
            psEndUser.setString(2, endUser.getEndUserLastName());
            psEndUser.setString(3, replaceNonNumerics(endUser.getEndUserPhone()));
            psEndUser.setBoolean(4, endUser.getEndUserText());
            if (endUser.getEndUserEmail() == null) {
                psEndUser.setNull(5, java.sql.Types.VARCHAR);
            } else {
                psEndUser.setString(5, endUser.getEndUserEmail());
            }
            psEndUser.setInt(6, Integer.parseInt(id));
            psEndUser.executeUpdate();

            psEndUserAccess = connection.prepareStatement(endUserAccessSQL);
            psEndUserAccess.setString(1, endUser.getUsername());
            psEndUserAccess.setString(2, salt);
            psEndUserAccess.setString(3, hashed);
            psEndUserAccess.setInt(4, Integer.parseInt(id));
            psEndUserAccess.executeUpdate();

            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            String ePlus = "RentalDb: public register() - " + e;
            throw new SQLException(ePlus);
        } finally {
            DbHelper.closePreparedStatment(psEndUser);
            pool.freeConnection(connection);
        }
    }

    //fixed for EJ-A01
    //This is a validation for a user login, it searches the database for a case where the username and password match,
    //it then returns a end user object that is either null or not null for logic in the servlet
    //if the end user is not null it returns the entire end user object so that
    //the logged in end user can be added to session
    public static EndUser validateUsername(String username) throws SQLException {

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        PreparedStatement ps = null;
        ResultSet rs = null;

        EndUser endUserLogin = null;
        CreditCard creditCard = null;
        CreditCardType creditCardType = null;
        Rental rental = null;

        //sql for finding a end user bassed on username and password
        String sql = "SELECT * "
                + "FROM end_user eu "
                + "JOIN end_user_access eua ON eu.id = eua.end_user_id "
                + "LEFT JOIN credit_card cc ON eu.id = cc.end_user_id "
                + "LEFT JOIN credit_card_type ct ON cc.credit_card_type_id = ct.id "
                + "LEFT JOIN rental r ON eu.id = r.end_user_id "
                + "LEFT JOIN end_user_type eut ON eut.id = eua.end_user_type_id "
                + "WHERE eua.user_name = ?";

        System.out.println(sql);

        //this try catch attempts to do all inputs, if there is an error it will be sent to catch and do a rollback
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();

            if (rs.next()) {
                if (rs.getInt("cc.id") > 0) {
                    creditCard = new CreditCard(
                            rs.getInt("cc.id"),
                            rs.getString("cc.card_number"),
                            rs.getString("cc.card_cvv"),
                            rs.getString("cc.card_expiry"));
                }
                if (rs.getInt("ct.id") > 0) {
                    creditCardType = new CreditCardType(
                            rs.getInt("ct.id"),
                            rs.getString("ct.short_desc"),
                            rs.getString("ct.long_desc"),
                            rs.getBoolean("ct.active"));
                }
                if (rs.getInt("r.id") > 0) {
                    rental = new Rental(
                            rs.getInt("r.id"),
                            rs.getInt("r.vehicle_id"),
                            rs.getString("r.rental_start_date"),
                            rs.getString("r.rental_end_date"));
                }

                //creating the validated loged in end user object
                endUserLogin = new EndUser(
                        rs.getInt("eu.id"),
                        creditCard,
                        creditCardType,
                        rental,
                        rs.getString("eu.first_name"),
                        rs.getString("eu.last_name"),
                        rs.getString("eu.phone_number"),
                        rs.getBoolean("eu.can_text"),
                        rs.getString("eu.email_address"),
                        rs.getString("eua.user_name"),
                        rs.getString("eua.salted"),
                        rs.getString("eua.hashed"),
                        rs.getBoolean("eua.active"),
                        DateUtil.getLocalDateTime(rs.getTimestamp("date_added")),
                        rs.getString("eut.long_desc"));

            }

        } catch (SQLException e) {
            System.out.println(e);
            throw new SQLException("get() - UnitTypeDb: " + e);
        } finally {
            DbHelper.closeResultSet(rs);
            DbHelper.closePreparedStatment(ps);
            pool.freeConnection(connection);
        }
        return endUserLogin;
    }
}//end of class
