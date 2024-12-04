package com.example.javaricanprojectapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.javaricanprojectapp.Utils.showMaximizedScreen;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("home-view.fxml"));
        stage.setTitle("Card APP");
        showMaximizedScreen(root, stage);
    }

    public static void main(String[] args) {
        launch();
    }
}