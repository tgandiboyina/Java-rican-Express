package com.example.javaricanprojectapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
    private TextField searchField;
    @FXML
    private ComboBox<String> sortOptionsDropdown;

    @FXML
    private VBox transactionsContainer;
    @FXML
    private TextField transactionSearchField;
    @FXML
    private ComboBox<String> transactionSortOptionsDropdown;

    @FXML
    protected void onLogoutButtonClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("home-view.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Home");
            showMaximizedScreen(root, stage);
        } catch (IOException e) {
            System.out.println("Error logging out" + e);
        }
    }

    public void handleInvalidInput(NumberFormatException e) {
        warningLabel.setVisible(true);

        cardNumberField.clear();
        cardNameField.clear();
        expiryDateField.clear();
        balanceField.clear();

        cardNumberField.textProperty().addListener((_, _, newValue) -> {
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
            displayAllCards();

            cardNumberField.clear();
            cardNameField.clear();
            expiryDateField.clear();
            balanceField.clear();

        } catch (NumberFormatException e) {
            System.out.println("Invalid input");
            handleInvalidInput(e);
        }
    }

    @FXML
    private void onSearchKeyReleased() {
        String searchText = searchField.getText().toLowerCase();
        List<Card> filteredCards = User.getCards().stream()
                .filter(card -> card.getCardInfo().toLowerCase().contains(searchText))
                .collect(Collectors.toList());
        displayCards(filteredCards);
    }

    @FXML
    private void onSortCardsButtonClick() {
        String sortOption = sortOptionsDropdown.getValue();
        if (sortOption == null) {
            return;
        }

        List<Card> sortedCards;
        switch (sortOption) {
            case "Type":
                sortedCards = User.getCards().stream()
                        .sorted(Comparator.comparing(Card::getType))
                        .collect(Collectors.toList());
                break;
            case "Date Added":
                sortedCards = User.getCards().stream()
                        .sorted(Comparator.comparing(Card::getID))
                        .collect(Collectors.toList());
                break;
            case "Balance":
                sortedCards = User.getCards().stream()
                        .sorted(Comparator.comparing(Card::getBalance))
                        .collect(Collectors.toList());
                break;
            default:
                return;
        }
        displayCards(sortedCards);
    }

    @FXML
    private void onTransactionSearchKeyReleased() {
        String searchText = transactionSearchField.getText().toLowerCase();
        List<Transaction> filteredTransactions = User.getTransactions().stream()
                .filter(transaction -> transaction.toString().toLowerCase().contains(searchText))
                .collect(Collectors.toList());
        displayTransactions(filteredTransactions);
    }

    @FXML
    private void onSortTransactionsButtonClick() {
        String sortOption = transactionSortOptionsDropdown.getValue();
        if (sortOption == null) {
            return;
        }

        List<Transaction> sortedTransactions;
        switch (sortOption) {
            case "Card Name":
                sortedTransactions = User.getTransactions().stream()
                        .sorted(Comparator.comparing(transaction -> {
                            String cardName = User.getCardNameByID(transaction.getCardID());
                            return cardName != null ? cardName : "Unknown Card";
                        }))
                        .collect(Collectors.toList());
                break;
            case "Date":
                sortedTransactions = User.getTransactions().stream()
                        .sorted(Comparator.comparing(Transaction::getDate))
                        .collect(Collectors.toList());
                break;
            case "Amount":
                sortedTransactions = User.getTransactions().stream()
                        .sorted(Comparator.comparing(Transaction::getAmount))
                        .collect(Collectors.toList());
                break;
            case "Description":
                sortedTransactions = User.getTransactions().stream()
                        .sorted(Comparator.comparing(Transaction::getDescription))
                        .collect(Collectors.toList());
                break;
            default:
                return;
        }
        displayTransactions(sortedTransactions);
    }

    // Updated displayCards method
    private void displayAllCards() {
        displayCards(User.getCards());
    }

    private void displayCards(List<Card> cards) {
        cardsContainer.getChildren().clear();

        // Render card buttons
        for (Card card : cards) {
            Button cardButton = new Button(card.getCardInfo());
            cardButton.setOnAction(event -> openCardDetails(card));
            cardsContainer.getChildren().add(cardButton);
        }
    }

    private void displayAllTransactions() {
        displayTransactions(User.getTransactions());
    }

    private void displayTransactions(List<Transaction> transactions) {
        transactionsContainer.getChildren().clear();

        // Render transaction details as text
        for (Transaction transaction : transactions) {
            Label transactionLabel = new Label(transaction.toString());
            transactionsContainer.getChildren().add(transactionLabel);
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
            stage.setTitle("Card Details");
            showMaximizedScreen(root, stage);
        } catch (IOException e) {
            System.out.println("Error opening card details" + e);
        }
    }

    @FXML
    public void initialize() {
        System.out.println("Dashboard view initialized, displaying cards and transactions...");
        displayAllCards();
        displayAllTransactions();
    }
}
