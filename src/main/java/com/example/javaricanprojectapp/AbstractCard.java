package com.example.javaricanprojectapp;

public abstract class AbstractCard implements Card {
    protected double balance;
    protected boolean active;
    protected double interestRate;
    protected String paymentDate;
    protected int cardNumber;
    protected String cardName;
    protected String expiryDate;
    protected int ID;

    private static int idCounter = 0;

    public AbstractCard(int CardNumber, String CardName, double interestRate, String expiryDate, double balance) {
        this.balance = balance;
        this.active = true;
        this.cardNumber = CardNumber;
        this.cardName = CardName;
        this.interestRate = interestRate;
        this.expiryDate = expiryDate;
        this.ID = generateUniqueID();
    }

    private synchronized int generateUniqueID() {
        return ++idCounter;
    }

    @Override
    public void activate() {
        this.active = true;
    }

    @Override
    public void deactivate() {
        this.active = false;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public double calculateInterest() {
        return balance * (interestRate / 100);
    }

    @Override
    public String getPaymentDate() {
        return paymentDate;
    }

    @Override
    public String getCardInfo(){
        return "Card Number: " + cardNumber + ", Card Name: " + cardName + ", Interest Rate: " + interestRate + ", Date: " + expiryDate;
    }
    @Override
    public String getType() {
        if (this instanceof CreditCard) {
            return "credit";
        } else if (this instanceof DebitCard) {
            return "debit";
        } else if (this instanceof PrepaidCard) {
            return "prepaid";
        } else {
            return "unknown";
        }
    }

    @Override
    public int getID(){
        return ID;
    }

    @Override
    public double getBalance(){
        return balance;
    }

    @Override
    public String getName(){
        return cardName;
    }
}
