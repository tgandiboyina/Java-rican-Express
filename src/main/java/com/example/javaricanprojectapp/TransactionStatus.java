package com.example.javaricanprojectapp;

public class TransactionStatus {
    private boolean success;
    private String message;
    private double updatedBalance;

    public TransactionStatus(boolean success, String message) {
        this.success = success;
        this.message = message;
        this.updatedBalance = updatedBalance;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
