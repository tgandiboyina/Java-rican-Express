package com.example.javaricanprojectapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.io.IOException;

import static com.example.javaricanprojectapp.Utils.showMaximizedScreen;

public class DashboardController {

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

    @FXML
    protected void onLogoutButtonClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("home-view.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            showMaximizedScreen(root, stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleInvalidInput(NumberFormatException e) {
        warningLabel.setVisible(true);

        cardNumberField.clear();
        cardNameField.clear();
        expiryDateField.clear();
        balanceField.clear();

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
            long balance = Long.parseLong(balanceText);
            int cardNumber = Integer.parseInt(cardNumberText);
            Card newCard;

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

            User.addCard(newCard);
            displayCards();

            cardNumberField.clear();
            cardNameField.clear();
            expiryDateField.clear();
            balanceField.clear();

        } catch (NumberFormatException e) {
            System.out.println("Invalid input");
            handleInvalidInput(e);
        }
    }

//    private void displayCards() {
//        cardsContainer.getChildren().clear();
//
//        for (Card card : User.getCards()) {
//            Label cardLabel = new Label(card.getCardInfo());
//            cardsContainer.getChildren().add(cardLabel);
//        }
//    }
// Updated displayCards method
    private void displayCards() {
        cardsContainer.getChildren().clear();

        //render card buttons
        for (Card card : User.getCards()) {
            Button cardButton = new Button(card.getCardInfo());
            cardButton.setOnAction(event -> openCardDetails(card));
            cardsContainer.getChildren().add(cardButton);
        }
    }

    // Method to open Card Details page
    private void openCardDetails(Card card) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("card-details-view.fxml"));
            Parent root = loader.load();

            // Pass card data to CardDetailsController
            CardDetailsController controller = loader.getController();
            controller.setCard(card);
            Stage stage = (Stage) cardsContainer.getScene().getWindow();
            showMaximizedScreen(root, stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        System.out.println("Dashboard view initialized, displaying cards...");
        displayCards();
    }
}