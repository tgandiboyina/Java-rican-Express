package com.example.javaricanprojectapp;
import java.time.LocalDate;

public class Transaction {
    private String description;
    private double amount;
    private double newBalance;
    private int cardID;
    private LocalDate date;

    public Transaction(String description, double amount, double newBalance, int cardID) {
        this.description = description;
        this.amount = amount;
        this.newBalance = newBalance;
        this.cardID = cardID;
        this.date = LocalDate.now();
    }

    // Getters and toString() method for logging
}
