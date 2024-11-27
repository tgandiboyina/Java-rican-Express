package com.example.javaricanprojectapp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CardDetailsController {
    @FXML
    private Label cardDetailsLabel;

    private Card card;

    public void setCard(Card card) {
        this.card = card;
        displayCardDetails();
    }

    private void displayCardDetails() {
        if (card != null) {
            cardDetailsLabel.setText(card.getCardInfo());
        }
    }
}

