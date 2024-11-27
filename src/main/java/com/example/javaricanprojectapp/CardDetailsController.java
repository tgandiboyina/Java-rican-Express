package com.example.javaricanprojectapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class CardDetailsController {

    @FXML
    private Label cardDetailsLabel;

    @FXML
    private TextField chargeAmountField;

    @FXML
    private Label chargeStatusLabel;

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

    @FXML
    protected void onAddChargeButtonClick(ActionEvent event) {
        if (card != null) {
            try {
                double amount = Double.parseDouble(chargeAmountField.getText());
                String status = card.addCharge(amount);
                chargeStatusLabel.setText(status);
                displayCardDetails(); // Refresh card details after adding charge
            } catch (NumberFormatException e) {
                chargeStatusLabel.setText("Invalid amount. Please enter a valid number.");
            }
        } else {
            chargeStatusLabel.setText("No card selected.");
        }
    }

    @FXML
    protected void onBackButtonClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard-view.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


