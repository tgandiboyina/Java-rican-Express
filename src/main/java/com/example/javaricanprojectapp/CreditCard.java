package com.example.javaricanprojectapp;

public class CreditCard extends AbstractCard{
    private int limit;

    public CreditCard(double interestRate, String paymentDate, int limit) {
        super(interestRate, paymentDate);
        this.limit = limit;
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
}
