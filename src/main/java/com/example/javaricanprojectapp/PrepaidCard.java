package com.example.javaricanprojectapp;

public class PrepaidCard extends AbstractCard{
    public PrepaidCard(String paymentDate) {
        super(0, paymentDate); // No interest for prepaid cards
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
}
