package com.example.javaricanprojectapp;

public interface Card {
    String addCharge(double amount);
    void makePayment(double amount);
    double calculateInterest();
    String getPaymentDate();
    void activate();
    void deactivate();
    boolean isActive();
    String getCardInfo();
}
