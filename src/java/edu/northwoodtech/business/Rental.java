package edu.northwoodtech.business;
import java.io.Serializable;
import java.math.BigDecimal;


//This is the Rental Object
//it has information for the id, vehicle id, vehicle year, vehicle make, vehicle model,
//vehicle type, cost per day, formated cost per day, start time, end time,
//total days, and total cost
//has getters and setters for each field
public class Rental implements Serializable{

    private int id;
    private int vehicleId;
    private String vehicleYear;
    private String make;
    private String model;
    private String vehicleType;
    private BigDecimal costPerDay;
    private String formatedCostPerDay;
    //formated times will be saved here
    private String startTime;
    private String endTime;
    private long totalDays;
    private String totalCost;
    
    //no argument constructor
    public Rental(){
    }

    
   //this constructor is used for setting everything on creation
    public Rental(int vehicleId, String vehicleYear, String model, String make, 
            String vehicleType, BigDecimal costPerDay, String startTime,
            String endTime, long totalDays, String totalCost, String formatedCostPerDay) {

        this.vehicleId = vehicleId;
        this.vehicleYear = vehicleYear;
        this.model = model;
        this.make = make;
        this.vehicleType = vehicleType;
        this.costPerDay = costPerDay;
        this.startTime = startTime;
        this.endTime = endTime;
        this.totalDays = totalDays;
        this.totalCost = totalCost;
        this.formatedCostPerDay = formatedCostPerDay;
    }
    
    //this constructor is used to set only the vehicle information for a rental
    public Rental(int id, int vehicleId,  
            String startTime, String endTime) {

        this.id = id;
        this.vehicleId = vehicleId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    
    public String getVehicleYear() {
        return vehicleYear;
    }

    public void setVehicleYear(String vehicleYear) {
        this.vehicleYear = vehicleYear;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVehicleType() {
        return vehicleType;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public BigDecimal getCostPerDay() {
        return costPerDay;
    }

    public void setCostPerDay(BigDecimal costPerDay) {
        this.costPerDay = costPerDay;
    }
    
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    
    public long getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(long totalDays) {
        this.totalDays = totalDays;
    }
    

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    
    
    public String getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(String totalCost) {
        this.totalCost = totalCost;
    }
    
    
    public String getFormatedCostPerDay() {
        return formatedCostPerDay;
    }

    public void setFormatedCostPerDay(String formatedCostPerDay) {
        this.formatedCostPerDay = formatedCostPerDay;
    }
}//end of class