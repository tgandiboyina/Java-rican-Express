package com.example.javaricanprojectapp;

public class DebitCard extends AbstractCard{
    public DebitCard(String paymentDate) {
        super(0, paymentDate); // No interest for debit cards
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
}
