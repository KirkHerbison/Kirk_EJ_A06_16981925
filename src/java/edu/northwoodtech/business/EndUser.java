package edu.northwoodtech.business;

import java.io.Serializable;
import java.time.LocalDateTime;

//This is the EndUser Object
//it has information for the id,first name, last name, phone, ok to text, email,
//username, status, salted, date added, credit card, credit card type,
//and a end users rental
//has getters and setters for each field
public class EndUser implements Serializable {

    private int id;
    private String endUserFirstName;
    private String endUserLastName;
    private String endUserPhone;
    private boolean endUserText;
    private String endUserEmail;
    private String username;
    private boolean active;
    private String salted;
    private String hashed;
    private LocalDateTime dateAdded;
    private CreditCard creditCard;
    private CreditCardType creditCardType;
    private Rental rental;
    private String userType;

    //no argument constructor
    public EndUser() {
    }

    //constructor for creating the end user
    public EndUser(int id, CreditCard creditCard, CreditCardType creditCardType,
            Rental rental, String endUserFirstName, String endUserLastName,
            String endUserPhone, boolean endUserText, String endUserEmail,
            String username, String salted, String hashed, boolean active, LocalDateTime dateAdded, String userType) {
        this.id = id;
        this.creditCard = creditCard;
        this.creditCardType = creditCardType;
        this.rental = rental;
        this.endUserFirstName = endUserFirstName;
        this.endUserLastName = endUserLastName;
        this.endUserPhone = endUserPhone;
        this.endUserText = endUserText;
        this.endUserEmail = endUserEmail;
        this.username = username;
        this.salted = salted;
        this.hashed = hashed;
        this.active = active;
        this.dateAdded = dateAdded;
        this.userType = userType;
        
    }

    //constructor for creating the end user for create
    public EndUser(String endUserFirstName, String endUserLastName,
            String endUserPhone, boolean endUserText, String endUserEmail,
            String username, String salted, String hashed, boolean active, LocalDateTime dateAdded, String userType) {
        this.endUserFirstName = endUserFirstName;
        this.endUserLastName = endUserLastName;
        this.endUserPhone = endUserPhone;
        this.endUserText = endUserText;
        if(endUserEmail.equals("")){
            this.endUserEmail = null;
        }
        else{
            this.endUserEmail = endUserEmail;
        }
        this.username = username;
        this.salted = salted;
        this.hashed = hashed;
        this.active = active;
        this.dateAdded = dateAdded;
        this.userType = userType;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public CreditCardType getCreditCardType() {
        return creditCardType;
    }

    public String getSalted() {
        return salted;
    }

    public String getHashed() {
        return hashed;
    }

    public String getEndUserFirstName() {
        return endUserFirstName;
    }

    public String getEndUserLastName() {
        return endUserLastName;
    }

    public String getEndUserPhone() {
        return endUserPhone;
    }

    public boolean getEndUserText() {
        return endUserText;
    }

    public String getEndUserEmail() {
        return endUserEmail;
    }

    public String getUsername() {
        return username;
    }

    public int getId() {
        return id;
    }

    public boolean isEndUserText() {
        return endUserText;
    }

    public boolean isActive() {
        return active;
    }

    public LocalDateTime getDateAdded() {
        return dateAdded;
    }

    public Rental getRental() {
        return rental;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
    

    public void setRental(Rental rental) {
        this.rental = rental;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public void setCreditCardType(CreditCardType creditCardType) {
        this.creditCardType = creditCardType;
    }

}//end of class

