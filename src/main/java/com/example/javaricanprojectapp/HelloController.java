package com.example.javaricanprojectapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;


public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private ComboBox<String> cardTypeDropdown;

    @FXML
    private TextField cardNumberField;
    @FXML
    private TextField cardNameField;
    @FXML
    private TextField expiryDateField;
    @FXML
    private TextField balanceField;
    @FXML
    private VBox cardsContainer;

    private List<Card> userCards = new ArrayList<>();

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("DO LOGIN/REGISTER STUFF");
    }

    // Login button action method
    @FXML
    protected void onLoginButtonClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("dashboard-view.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) welcomeText.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // "Add Card" button click method
    @FXML
    protected void onAddCardButtonClick() {
        String cardType = cardTypeDropdown.getValue();
        String cardNumberText = cardNumberField.getText();
        String cardName = cardNameField.getText();
        String expiryDate = expiryDateField.getText();
        String balanceText = balanceField.getText();

        try {
            double balance = Double.parseDouble(balanceText);
            int cardNumber = Integer.parseInt(cardNumberText);
            Card newCard;

            // Create card based on selected type
            switch (cardType) {
                case "Credit Card":
                    newCard = new CreditCard(cardNumber, cardName, expiryDate);
                    break;
                case "Debit Card":
                    newCard = new DebitCard(cardNumber, cardName, expiryDate, balance);
                    break;
                case "Prepaid Card":
                    newCard = new PrepaidCard(cardNumber, cardName, expiryDate, balance);
                    break;
                default:
                    return;
            }

            // Add card to user's card list and display it
            userCards.add(newCard);
            displayCard(newCard);

            // Clear input fields
            cardNumberField.clear();
            cardNameField.clear();
            expiryDateField.clear();
            balanceField.clear();

        } catch (NumberFormatException e) {
            System.out.println("Invalid balance input");
        }
    }

    // Method to display a card in the `cardsContainer`
    private void displayCard(Card card) {
        String cardInfo = card.getCardInfo(); // Ensure `getCardInfo` returns a string description of the card
        Text cardText = new Text(cardInfo);
        cardsContainer.getChildren().add(cardText);
    }
}