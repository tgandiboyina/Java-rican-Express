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
import java.io.IOException;


public class MainController {
    @FXML
    private Label welcomeText;

    @FXML
    private Label warningLabel;

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

    private User user = new User("placeholderUsername");

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

    @FXML
    protected void onLogoutButtonClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("home-view.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) cardsContainer.getScene().getWindow(); // Assumes `cardsContainer` or another node is part of the scene
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleInvalidInput(NumberFormatException e) {
        // Display warning label
        warningLabel.setVisible(true);

        // Clear all input fields
        cardNumberField.clear();
        cardNameField.clear();
        expiryDateField.clear();
        balanceField.clear();

        // Set listeners on fields to hide warning when user types again

        cardNumberField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                warningLabel.setVisible(false);
            }
        });

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
            user.addCard(newCard);
            displayCards();

            // Clear input fields
            cardNumberField.clear();
            cardNameField.clear();
            expiryDateField.clear();
            balanceField.clear();

        } catch (NumberFormatException e) {
            System.out.println("Invalid balance input");
            handleInvalidInput(e);
        }
    }



    // Method to display a card in the `cardsContainer`
    private void displayCards() {
        cardsContainer.getChildren().clear(); // Clear current display

        for (Card card : user.getCards()) {
            Label cardLabel = new Label(card.getCardInfo()); // Example of displaying card name
            cardsContainer.getChildren().add(cardLabel);
        }
    }

//    @FXML
//    public void initialize() {
//        displayCards();
//    }
}