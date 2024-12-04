package com.example.javaricanprojectapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;

import static com.example.javaricanprojectapp.Utils.showMaximizedScreen;

public class HomeController {

    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("DO LOGIN/REGISTER STUFF");
    }

    // Login button action method
    @FXML
    protected void onLoginButtonClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("dashboard-view.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setTitle("Dashboard");
            showMaximizedScreen(root, stage);
        } catch (IOException e) {
            System.out.println("Error logging in: " + e);
        }
    }

    @FXML
    public void initialize() {
        System.out.println("Home view initialized");
    }
}
