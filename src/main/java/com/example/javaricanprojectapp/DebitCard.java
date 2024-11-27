package com.example.javaricanprojectapp;

public class DebitCard extends AbstractCard{
    public DebitCard(int cardNumber, String cardName, String expiryDate, long balance) {
        super(cardNumber, cardName, 0, expiryDate, balance); // No interest for debit cards
    }

    @Override
    public String addCharge(double amount) {
        if (isActive()) {
            if (balance >= amount){
                balance -= amount;
                return "Charge added successfully!";
            }
            return "Insufficient funds!";
        }
        return "Card not active";
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
