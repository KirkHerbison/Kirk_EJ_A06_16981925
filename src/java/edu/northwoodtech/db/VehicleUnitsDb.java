package edu.northwoodtech.db;

import edu.northwoodtech.business.Vehicle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//works with the vehicle table
public class VehicleUnitsDb {
    
    public VehicleUnitsDb(){
        
    }//end of constructor
    
    //this method creates and returns a list of vehicles from the databse
    public static List<Vehicle> get() throws SQLException{
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        
        PreparedStatement ps = null;
        ResultSet rs = null;
         
        //creating the vehicle array from the database
        List<Vehicle> vehicles = new ArrayList<>();
        Vehicle vehicle;
        
        //sql which will return all vehciles from the databse
        String sql = "SELECT v.id, v.vehicle_year, v.make, v.model, v.max_passengers, vt.long_desc, vt.cost_per_day, vt.short_desc, vt.active "
                + "FROM vehicle v "
                + "JOIN vehicle_type vt on v.vehicle_type_id = vt.id";
        
        
        try{
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            
            //this while loop crates the vehicle objects and adds them to a list
            while(rs.next()){
                vehicle = new Vehicle(
                        rs.getInt("id"),
                        rs.getString("vehicle_year"),
                        rs.getString("make"),
                        rs.getString("model"),
                        rs.getInt("max_passengers"),
                        rs.getString("long_desc"),
                        rs.getBigDecimal("cost_per_day"),
                        rs.getString("short_desc"),
                        rs.getBoolean("active")); 
                vehicles.add(vehicle);      
            }
            
        }catch(SQLException e){
            throw new SQLException("get() - UnitTypeDb: " + e);
        }
        finally{
            DbHelper.closeResultSet(rs);
            DbHelper.closePreparedStatment(ps);
            pool.freeConnection(connection);
        }
        return vehicles;
    }
}//end of class

