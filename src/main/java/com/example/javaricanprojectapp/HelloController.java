package com.example.javaricanprojectapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.Parent;
import java.io.IOException;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("DO LOGIN/REGISTER STUFF");
    }

    // New login button action method
    @FXML
    protected void onLoginButtonClick() {
        // Here, you would include code to verify login credentials
        // For simplicity, we'll assume the login is successful and transition to the dashboard.

        try {
            // Load the dashboard-view.fxml file
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("dashboard-view.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) welcomeText.getScene().getWindow(); // Get the current window
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}