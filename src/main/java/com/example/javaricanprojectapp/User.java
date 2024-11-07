package com.example.javaricanprojectapp;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private List<Card> cards;
    //still need a list of transaction objects that also tells which card was used for each transaction

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

    public void clearCards() {
        cards.clear();
    }
}