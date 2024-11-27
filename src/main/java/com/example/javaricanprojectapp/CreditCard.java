package com.example.javaricanprojectapp;

public class CreditCard extends AbstractCard{
    private int limit;

//    public CreditCard(int cardNumber, String cardName, int limit, double balance) {
//        super(cardNumber, paymentDate);
//        this.limit = limit;
//    }
    public CreditCard(int cardNumber, String cardName, String expiryDate) {
        super(cardNumber, cardName, .05, expiryDate, 0);
        limit = 1000;
    }

    @Override
    public String addCharge(double amount) {
        if (isActive()) {
            if (amount + balance <= limit){
                balance += amount;
                return "Charge added successfully!";
            }
            return "Charge declined: Over credit limit";
        }
        return "Card not active";
    }

    @Override
    public void makePayment(double amount) {
        balance -= amount;
    }

    @Override
    public String getCardInfo(){
        return "Card Type: Credit, Card Balance: " + balance + ", Card Number: " + cardNumber + ", Card Name: " + cardName + ", Interest Rate: " + interestRate + ", Date: " + expiryDate + ", Limit: " + limit ;
    }
}
