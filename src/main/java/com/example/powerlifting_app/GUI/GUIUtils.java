package com.example.powerlifting_app.GUI;

import javafx.scene.image.Image;
import javafx.stage.Stage;

public class GUIUtils {
    public static void setAppIcon(Stage stage) {
        try {
            Image icon = new Image(GUIUtils.class.getResourceAsStream("/com/example/powerlifting_app/logo.jpg"));
            stage.getIcons().add(icon);
        } catch (Exception e) {
            System.out.println("Nie udało się załadować ikonki: " + e.getMessage());
        }
    }
}