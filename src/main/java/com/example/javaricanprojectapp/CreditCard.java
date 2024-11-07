package com.example.javaricanprojectapp;

public class CreditCard extends AbstractCard{
    private int limit;

//    public CreditCard(int cardNumber, String cardName, int limit, double balance) {
//        super(cardNumber, paymentDate);
//        this.limit = limit;
//    }
    public CreditCard(int cardNumber, String cardName, String expiryDate) {
        super(cardNumber, cardName, .05, expiryDate);
        this.balance=0;
        limit = 0;
    }

    @Override
    public void addCharge(double amount) {
        if (isActive()) {
            if (amount + balance < limit){
                balance += amount;
            }
            else {
                System.out.println("Charge declined: Over credit limit");
            }
        }
    }

    @Override
    public void makePayment(double amount) {
        balance -= amount;
    }

    @Override
    public String getCardInfo(){
        return "Card Type: Credit, Card Balance: " + balance + ", Card Number: " + cardNumber + ", Card Name: " + cardName + ", Interest Rate: " + interestRate + ", Date: " + expiryDate;
    }
}
