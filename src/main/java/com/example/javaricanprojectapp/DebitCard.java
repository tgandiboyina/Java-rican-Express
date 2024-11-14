package com.example.javaricanprojectapp;

public class DebitCard extends AbstractCard{
    public DebitCard(int cardNumber, String cardName, String expiryDate, long balance) {
        super(cardNumber, cardName, 0, expiryDate, balance); // No interest for debit cards
    }

    @Override
    public void addCharge(double amount) {
        if (isActive() && balance >= amount) {
            balance -= amount; // Deduct directly from balance
        }
    }

    @Override
    public void makePayment(double amount) {
        balance += amount; // Add money to balance
    }

    @Override
    public String getCardInfo(){
        return "Card Type: Debit, Card Balance: " + balance + ", Card Number: " + cardNumber + ", Card Name: " + cardName + ", Interest Rate: " + interestRate + ", Date: " + expiryDate;
    }
}
