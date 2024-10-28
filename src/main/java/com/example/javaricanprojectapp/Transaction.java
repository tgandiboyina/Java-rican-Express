package com.example.javaricanprojectapp;
import java.time.LocalDate;

public class Transaction {
    private String description;
    private double amount;
    private LocalDate date;

    public Transaction(String description, double amount) {
        this.description = description;
        this.amount = amount;
        this.date = LocalDate.now();
    }

    // Getters and toString() method for logging
}
