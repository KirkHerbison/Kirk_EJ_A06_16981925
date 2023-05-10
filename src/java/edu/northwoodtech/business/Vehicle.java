/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.northwoodtech.business;

import edu.northwoodtech.helper.HelperFormDisplays;
import java.io.Serializable;
import java.math.BigDecimal;

//This is the Vehicle Object
//it has information for the id, year, make, model, capacity, vehicle type,
//and cost per day of a vehicle
//has getters and setters for each field
public class Vehicle implements Serializable {

    private int ID;
    private String vehicleYear;
    private String make;
    private String model;
    private int capacity;
    private String vehicleType;
    private BigDecimal costPerDay;
    private String shortDesc;
    private Boolean isActive;    

    public Vehicle() {
        //no argument constructor
    }

    public Vehicle(int ID, String vehicleYear, String make, String model, int capacity, String vehicleType, BigDecimal costPerDay, String shortDesc, Boolean isActive) {
        this.ID = ID;
        this.vehicleYear = vehicleYear;
        this.make = make;
        this.model = model;
        this.capacity = capacity;
        this.vehicleType = vehicleType;
        this.costPerDay = costPerDay;
        this.shortDesc = shortDesc;
        this.isActive = isActive;        
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getVehicleYear() {
        return vehicleYear;
    }

    public void setVehicleYear(String vehicle_year) {
        this.vehicleYear = vehicle_year;
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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleTypeId(String vehicle_type) {
        this.vehicleType = vehicle_type;
    }

    public BigDecimal getCostPerDay() {
        return costPerDay;
    }

    public void setActive(BigDecimal cost_per_day) {
        this.costPerDay = cost_per_day;
    }

    public String getFormatedCost() {
        return HelperFormDisplays.getFormatedCost(costPerDay);
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
    
    

}//end of class
