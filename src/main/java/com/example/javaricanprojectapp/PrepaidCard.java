package com.example.javaricanprojectapp;

public class PrepaidCard extends AbstractCard{
    public PrepaidCard(int cardNumber, String cardName, String expiryDate, double balance) {
        super(cardNumber, cardName, 0, expiryDate); // No interest for debit cards
    }

    @Override
    public void addCharge(double amount) {
        if (isActive() && balance >= amount) {
            balance -= amount;
        }
    }

    @Override
    public void makePayment(double amount) {
        balance += amount; // Load funds onto the prepaid card
    }

    @Override
    public String getCardInfo(){
        return "Card Type: Prepaid, Card Balance: " + balance + ", Card Number: " + cardNumber + ", Card Name: " + cardName + ", Interest Rate: " + interestRate + ", Date: " + expiryDate;
    }
}
