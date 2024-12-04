package com.example.javaricanprojectapp;

public interface Card {
    TransactionStatus addCharge(double amount);
    TransactionStatus makePayment(double amount);
    double calculateInterest();
    String getPaymentDate();
    void activate();
    void deactivate();
    boolean isActive();
    String getCardInfo();
    String getType();
    int getID();
    double getBalance();
}
