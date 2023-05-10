/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.northwoodtech.helper;

import edu.northwoodtech.business.Vehicle;
import edu.northwoodtech.db.VehicleUnitsDb;
import edu.northwoodtech.utility.DateUtil;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

//this helper class deals with loading the information of the vehicle page
public class HelperLoadPageData {
    
    // loads the page data for vehicles
    // this generates the list for the table as well as setting the date information
    public static void loadPageDataVehicle(HttpServletRequest request) throws SQLException {
//        Map<String, List<Vehicle>> vehicleTypeMap = new HashMap<>();
//        vehicleTypeMap.put("vehicleTypes", VehicleUnitsDb.get());
//        request.getSession().setAttribute("vehicleTypes", VehicleUnitsDb.get());
//        LocalDate today = DateUtil.getCurrentDate();
//        LocalDate tomorrow = DateUtil.addDaysToDate(today, 1);
//        LocalDate todayMax = DateUtil.addDaysToDate(today, 30);
//        LocalDate tomorrowMax = DateUtil.addDaysToDate(tomorrow, 30);
//        request.setAttribute("today", today);
//        request.setAttribute("tomorrow", tomorrow.toString());
//        request.setAttribute("todayMax", todayMax);
//        request.setAttribute("tomorrowMax", tomorrowMax.toString());
//        HelperAttribute.setSessionAttributes(request, vehicleTypeMap);
    }
}
