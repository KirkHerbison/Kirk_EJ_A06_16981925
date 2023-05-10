
package edu.northwoodtech.helper;

import edu.northwoodtech.business.EndUser;
import edu.northwoodtech.business.Rental;
import edu.northwoodtech.business.Vehicle;
import edu.northwoodtech.db.RentalDb;
import edu.northwoodtech.utility.DateUtil;
import java.math.BigDecimal;
import java.sql.SQLException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import javax.servlet.ServletContext;


//this helper class is used for various methodds which help with the end user rental
public class HelperRental extends HttpServlet {
    
    //validation The start date is before the end date
    //this makes sure that the start date is not before or equale to the end date
    //this returns true only if the dates are valid
    public static boolean validateDates(HttpServletRequest request) {
        boolean isValid = false;
        LocalDate startTime = LocalDate.parse(request.getParameter("startTime"));
        LocalDate endTime = LocalDate.parse(request.getParameter("endTime")); 
        if(startTime.isBefore(endTime)){
            isValid = true;
        }
        return isValid;
    }
    
    //this method is used to validate that the user has selected a vechicle
    //this method only returns true if a vehicle has been selected
    public static boolean validateSelected(HttpServletRequest request) {
        boolean isValid = true;
        try{
            //this is just used to verify, if it doesn't verify it will go to catch and return false
           Integer.parseInt(request.getParameter("vehicle_id"));
        }
        catch(NumberFormatException e){
            isValid = false;
        }

        return isValid;
    }
    
    //returns Rental information bassed on the selected radio button for vehicles
    //this will create a rental object and return it
    public static Rental getSelectedVehicle(HttpServletRequest request){
        
        int vehicleId = Integer.parseInt(request.getParameter("vehicle_id"));
               
        ServletContext sc = request.getServletContext();
        
        Rental rental = null;
        Vehicle selectedVehicle = null;   
        List<Vehicle> vehicles = (List <Vehicle>)sc.getAttribute("vehicleTypes");       
        selectedVehicle = vehicles.stream().filter(vehicle -> vehicle.getID() == vehicleId).findFirst().get();    
        rental = vehicleTypeToRental(selectedVehicle);
        addDatesToRental(request, rental);
        addTotalRentDays(request, rental);
        setFormatedCostPerDay(rental);
        setTotalCost(rental);
        
        
        return rental;
    }
    
    //converts vehicleType object to end User rental object and returns the rental
    public static Rental vehicleTypeToRental(Vehicle vehicleType){
        
        Rental rental = new Rental();
        
        rental.setVehicleId(vehicleType.getID());
        rental.setVehicleType(vehicleType.getVehicleType());
        rental.setMake(vehicleType.getMake());
        rental.setModel(vehicleType.getModel());
        rental.setVehicleYear(vehicleType.getVehicleYear());
        rental.setCostPerDay(vehicleType.getCostPerDay());
        
        return rental;
    }
    
    //takes in a end user rental object and gets the days between the start and end date of tht object.
    //adds the total days to the rental object
    public static void addTotalRentDays(HttpServletRequest request, Rental rental){
        rental.setTotalDays(DateUtil.getDaysBetween(LocalDate.parse(request.getParameter("startTime")),
                LocalDate.parse(request.getParameter("endTime"))));
    }
    
    //takes in the rental object and the request
    //sets the start and end time from the request to the rental object
    public static void addDatesToRental(HttpServletRequest request, Rental rental){
        rental.setStartTime(DateUtil.getFormattedDateFull(
                LocalDate.parse(request.getParameter("startTime"))));
        rental.setEndTime(DateUtil.getFormattedDateFull(
                LocalDate.parse(request.getParameter("endTime"))));
        
    }
    
    
    //takes in the rental object
    //sets the total cost to the rental based on the days and cost  per day
    public static void setTotalCost(Rental rental){
        long totalDays = rental.getTotalDays();
        BigDecimal costPerDay = rental.getCostPerDay();
        String totalCost = HelperFormDisplays.getFormatedCost(costPerDay.multiply(BigDecimal.valueOf(totalDays)));
        rental.setTotalCost(totalCost);
    }
    
    //take the end user Rental object
    //sets the formated cost per day using the helperform displays class
    public static void setFormatedCostPerDay(Rental rental){
        rental.setFormatedCostPerDay(HelperFormDisplays.getFormatedCost(rental.getCostPerDay()));
    }
    
    //validates the start time is before the end time and returns true if it is
    public static boolean get(HttpServletRequest request) {
        boolean isValid = false;
        LocalDate startTime = LocalDate.parse(request.getParameter("startTime"));
        LocalDate endTime = LocalDate.parse(request.getParameter("endTime"));
                
        if(startTime.isBefore(endTime)){
            isValid = true;
        }
        
        return isValid;
    }   
    
        // looks at the endUserLogedin and checks to see if they have a rental
        // if they do it updates the rental or if they don't it inserts the rental
        public static void createRecord(HttpServletRequest request){
                try{
                    EndUser endUserLogedin = (EndUser)request.getSession().getAttribute("endUserLogedin");
                    if(endUserLogedin.getRental() == null){
                        RentalDb.insertRental(request);
                    }
                    else{
                        RentalDb.updateRental(request);
                    }

                        
                }catch (SQLException ex){
                    System.out.println(ex);
                }        
    }
        
    //this method creates and returns a confrimation number    
    public static String getConfirmationNumber(){
        //notice the zero, one, O, and I have been removed
        //the string has 32 characters 0-31
        String chars = "23456789ABCDEFGHJKLMNPQRSTUVWXYZ";
        String confirmation = "";
        int min = 0;
        int max = 31;
        int maxLength = 20;
        for(int i = 0; i < maxLength; i++){
            confirmation += chars.charAt(ThreadLocalRandom.current().nextInt(min, max));
        }
        return confirmation;
    }

}//end of class
