/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/ServletListener.java to edit this template
 */
package edu.northwoodtech.utility;

import edu.northwoodtech.business.Vehicle;
import edu.northwoodtech.db.VehicleUnitsDb;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Enumeration;
import java.util.HashMap;

import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author Kirk
 */
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        
        ServletContext sc = sce.getServletContext();
        
        int currentYear = LocalDate.now().getYear();
        String developer = sc.getInitParameter("developer");
        
        sc.setAttribute("currentYear", currentYear);
        sc.setAttribute("developer", developer);
        
        try{
            loadPageData(sc);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
    }
    
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();
        removeContextAttributes(sc);
    }
    
    private void setContextAttributes(ServletContext sc, Map map){
        map.forEach((key, value)-> 
        sc.setAttribute(key.toString(), value));
    }
    
    private void removeContextAttributes(ServletContext sc){
        Enumeration<String> sessionList = sc.getAttributeNames();

        while (sessionList.hasMoreElements()) {
            String current = sessionList.nextElement();
            sc.removeAttribute(current);
        }
    }
    
    private void loadPageData(ServletContext sc) throws SQLException{
        Map<String, List<Vehicle>> vehicleTypeMap = new HashMap<>();
        vehicleTypeMap.put("vehicleTypes", VehicleUnitsDb.get());
        sc.setAttribute("vehicleTypes", VehicleUnitsDb.get());
        LocalDate today = DateUtil.getCurrentDate();
        LocalDate tomorrow = DateUtil.addDaysToDate(today, 1);
        LocalDate todayMax = DateUtil.addDaysToDate(today, 30);
        LocalDate tomorrowMax = DateUtil.addDaysToDate(tomorrow, 30);
        sc.setAttribute("today", today);
        sc.setAttribute("tomorrow", tomorrow.toString());
        sc.setAttribute("todayMax", todayMax);
        sc.setAttribute("tomorrowMax", tomorrowMax.toString());
        setContextAttributes(sc, vehicleTypeMap);
    }


}
