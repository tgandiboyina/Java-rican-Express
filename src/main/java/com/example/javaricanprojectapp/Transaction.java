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

    // Getters
    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public double getNewBalance() {
        return newBalance;
    }

    public int getCardID() {
        return cardID;
    }

    public LocalDate getDate() {
        return date;
    }

    // Setters
    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setNewBalance(double newBalance) {
        this.newBalance = newBalance;
    }

    public void setCardID(int cardID) {
        this.cardID = cardID;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Description='" + description + '\'' +
                ", Amount=" + amount +
                ", New Balance=" + newBalance +
                ", CardID=" + cardID +
                ", Date=" + date;
    }

    public String toString(String cardName){
        return "Description='" + description + '\'' +
                ", Amount=" + amount +
                ", New Balance=" + newBalance +
                ", CardName=" + cardName +
                ", Date=" + date;
    }
}

