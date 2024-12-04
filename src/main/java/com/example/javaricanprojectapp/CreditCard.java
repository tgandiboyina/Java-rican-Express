package com.example.javaricanprojectapp;

public class CreditCard extends AbstractCard{
    private int limit;

    public CreditCard(int cardNumber, String cardName, String expiryDate) {
        super(cardNumber, cardName, .05, expiryDate, 0);
        limit = 1000;
    }

    @Override
    public TransactionStatus addCharge(double amount) {
        if (isActive()) {
            if (amount + balance <= limit){
                balance += amount;
                return new TransactionStatus(true, "Charge added successfully!", amount + balance);
            }
            return new TransactionStatus(false, "Charge declined: Over credit limit");
        }
        return new TransactionStatus(false,"Card not active");
    }

    @Override
    public TransactionStatus makePayment(double amount) {
        if (isActive()) {
            if (balance - amount >= 0) {
                balance -= amount;
                return new TransactionStatus(true,"Payment added successfully!", balance-amount);
            }
            return new TransactionStatus(false,"Payment declined: Payment cannot be greater than current balance: $" + balance);
        }
        return new TransactionStatus(false, "Card not active");
    }

    @Override
    public String getCardInfo(){
        return "Name: " + cardName +  ", Type: Credit, Balance: " + balance + ", Number: " + cardNumber + ", Interest Rate: " + interestRate + ", Expiry: " + expiryDate + ", Limit: " + limit ;
    }
}
