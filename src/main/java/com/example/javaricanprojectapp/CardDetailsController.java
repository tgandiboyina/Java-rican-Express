package com.example.javaricanprojectapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import static com.example.javaricanprojectapp.Utils.showMaximizedScreen;

public class CardDetailsController {

    @FXML
    private Label cardDetailsLabel;

    @FXML
    private TextField chargeAmountField;

    @FXML
    private Button paymentButton;

    @FXML
    private Label statusLabel;

    private Card card;

    public void setCard(Card card) {
        this.card = card;
        displayCardDetails();
        updatePaymentButton();
    }

    private void updatePaymentButton() {
        if (card instanceof CreditCard) {
            paymentButton.setText("Make Payment");
        } else {
            paymentButton.setText("Add Funds");
        }
    }

    private void displayCardDetails() {
        if (card != null) {
            cardDetailsLabel.setText(card.getCardInfo());
        }
    }

    private void createTransaction(String desc, double amount, double newAmount, int cardID) {
        //add string and double
        Transaction transaction = new Transaction(desc, amount, newAmount, cardID);
        User.addTransaction(transaction);
    }

    @FXML
    protected void onPaymentButtonClick() {
        if (card != null) {
            try {
                double amount = Double.parseDouble(chargeAmountField.getText());
                TransactionStatus status = card.makePayment(amount);
                statusLabel.setText(status.getMessage());
                if (status.isSuccess()) {
                    createTransaction("Payment", amount, status.getUpdatedBalance(), card.getID());
                    displayCardDetails(); // Refresh the card details.
                }
            } catch (NumberFormatException e) {
                statusLabel.setText("Invalid amount. Please enter a valid number.");
            }
        } else {
            statusLabel.setText("No card selected.");
        }
    }

    @FXML
    protected void onAddChargeButtonClick() {
        if (card != null) {
            try {
                double amount = Double.parseDouble(chargeAmountField.getText());
                TransactionStatus status = card.addCharge(amount);
                statusLabel.setText(status.getMessage());
                if (status.isSuccess()) {
                    createTransaction("Charge", amount, status.getUpdatedBalance(), card.getID());
                    displayCardDetails(); // Refresh card details after adding charge
                }
            } catch (NumberFormatException e) {
                statusLabel.setText("Invalid amount. Please enter a valid number.");
            }
        } else {
            statusLabel.setText("No card selected.");
        }
    }

    @FXML
    protected void onBackButtonClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard-view.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Dashboard");
            showMaximizedScreen(root, stage);
        } catch (IOException e) {
            System.out.println("Error going back to dashboard" +  e);
        }
    }
}


