package com.example.javaricanprojectapp;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private List<Card> cards;

    public User(String username) {
        this.username = username;
        this.cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return cards;
    }
}