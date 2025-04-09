package com.example.powerlifting_app;

import com.example.powerlifting_app.GUI.GUIUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/powerlifting_app/hello-view.fxml"));
        Parent root = loader.load();
        stage.setTitle("Powerlifting App");
        Scene scene = new Scene(root, 400, 400);
        scene.getStylesheets().add(getClass().getResource("/com/example/powerlifting_app/style.css").toExternalForm());
        stage.setScene(scene);
        GUIUtils.setAppIcon(stage);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}