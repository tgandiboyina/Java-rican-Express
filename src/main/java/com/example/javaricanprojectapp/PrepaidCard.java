package com.example.javaricanprojectapp;

public class PrepaidCard extends AbstractCard{
    public PrepaidCard(int cardNumber, String cardName, String expiryDate, double balance) {
        super(cardNumber, cardName, 0, expiryDate, balance); // No interest for debit cards
    }

    @Override
    public TransactionStatus addCharge(double amount) {
        if (isActive()) {
            if (balance >= amount){
                balance -= amount;
                return new TransactionStatus(true,"Charge added successfully!", balance-amount);
            }
            return new TransactionStatus(false,"Insufficient funds!");
        }
        return new TransactionStatus(false,"Card not active");
    }

    @Override
    public TransactionStatus makePayment(double amount) {
        if (isActive()) {
            balance += amount;
            return new TransactionStatus(true,"Funds added successfully!", balance+amount);
        }
        return new TransactionStatus(false, "Card not active");
    }

    @Override
    public String getCardInfo(){
        return "Name: " + cardName + ", Type: Prepaid, Balance: " + balance + ", Number: " + cardNumber + ", Interest Rate: " + interestRate + ", Expiry: " + expiryDate;
    }
}
