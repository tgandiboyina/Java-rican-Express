package com.example.javaricanprojectapp;

public abstract class AbstractCard implements Card {
    protected double balance;
    protected boolean active;
    protected double interestRate;
    protected String paymentDate;

    public AbstractCard(double interestRate, String paymentDate) {
        this.balance = 0;
        this.active = true;
        this.interestRate = interestRate;
        this.paymentDate = paymentDate;
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
}
