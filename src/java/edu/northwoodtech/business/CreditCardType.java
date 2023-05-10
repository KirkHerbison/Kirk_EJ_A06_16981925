package edu.northwoodtech.business;

import java.io.Serializable;

//This is the CreditCardType Object, examples would be visa, or master card
//it has information for the id, and short description of a card type
//has getters and setters for each field
public class CreditCardType implements Serializable {

    private int id;
    private String shortDesc;
    private String longDesc;
    private Boolean isActive;

    //no argument constructor
    public CreditCardType() {
    }

    //constructor for creating the end user
    public CreditCardType(int id, String shortDesc, String longDesc, Boolean isActive) {
        this.id = id;
        this.shortDesc = shortDesc.toLowerCase();
        this.longDesc = longDesc;
        this.isActive = isActive;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public int getId() {
        return id;
    }

    public String getLongDesc() {
        return longDesc;
    }

    public void setLongDesc(String longDesc) {
        this.longDesc = longDesc;
    }

    public void setId(int id) {
        this.id = id;
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

