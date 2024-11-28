package com.example.javaricanprojectapp;

import java.util.ArrayList;
import java.util.List;

public class User {
    private static String username = "placeholderUsername";
    private static List<Card> cards = new ArrayList<>();
    private static List<Transaction> transactions = new ArrayList<>();

    // Private constructor to prevent instantiation
    private User() {}

    public static void setUsername(String newUsername) {
        username = newUsername;
    }

    public static String getUsername() {
        return username;
    }

    public static void addCard(Card card) {
        cards.add(card);
    }

    public static List<Card> getCards() {
        return cards;
    }

    public static void clearCards() {
        cards.clear();
    }

    public static void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public static List<Transaction> getTransactions() {
        return transactions;
    }

    public static void clearTransactions() {
        transactions.clear();
    }
}
