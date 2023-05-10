package edu.northwoodtech.business;

import java.io.Serializable;

//This is the CreditCard Object
//it has information for the id, card number, card cvv, and card Expiry
//has getters and setters for each field
public class CreditCard implements Serializable {

    private int id;
    private String cardNumber;
    private String cardCvv;
    private String cardExpiry;

    //no argument constructor
    public CreditCard() {
    }

    //constructor for creating the credit card, it takes in all the fields
    public CreditCard(int id, String cardNumber, String cardCvv, String cardExpiry) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.cardCvv = cardCvv;
        this.cardExpiry = cardExpiry;
    }

    public String getCardExpiry() {
        return cardExpiry;
    }

    public String getCardCvv() {
        return cardCvv;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setCardCvv(String cardCvv) {
        this.cardCvv = cardCvv;
    }

    public void setCardExpiry(String cardExpiry) {
        this.cardExpiry = cardExpiry;
    }

}//end of class

