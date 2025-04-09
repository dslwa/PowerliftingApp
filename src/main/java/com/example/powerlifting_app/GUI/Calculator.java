package com.example.powerlifting_app.GUI;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.layout.*;

public class Calculator {
    private static VBox calculatorRoot;

    public static void calculatorDisplay() {
        Stage stage = new Stage();
        GUIUtils.setAppIcon(stage);
        stage.setTitle("Calculator");

        calculatorRoot = new VBox(15);
        calculatorRoot.setAlignment(Pos.CENTER);

        StackPane centeredPane = new StackPane(calculatorRoot);
        centeredPane.setId("centered-pane");

        Scene scene = new Scene(centeredPane, 300, 500);
        scene.getStylesheets().add(AthleteForm.class.getResource("/com/example/powerlifting_app/style.css").toExternalForm());

        showMainMenu();
        stage.setScene(scene);
        stage.show();
    }

    public static void showMainMenu() {
        calculatorRoot.getChildren().clear();

        Button BMI = new Button("Calculate BMI");
        Button IPF_GL = new Button("Calculate IPF GL");
        Button FFMI = new Button("Calculate FFMI");
        Button RM = new Button("Calculate One Rep Max");
        Button BMR = new Button("Calculate BMR");
        Button MeetAttempts = new Button("Meet Attmpts");


        BMI.setOnAction(ev -> Calculations.BMI(calculatorRoot));
        IPF_GL.setOnAction(ev -> Calculations.IPG_GL(calculatorRoot));
        FFMI.setOnAction(ev -> Calculations.FFMI(calculatorRoot));
        RM.setOnAction(ev -> Calculations.RM(calculatorRoot));
        BMR.setOnAction(ev-> Calculations.BMR(calculatorRoot));
        MeetAttempts.setOnAction(ev -> Calculations.MeetAttemptsCalculator(calculatorRoot));
        calculatorRoot.getChildren().addAll(BMI, IPF_GL, FFMI, RM, BMR, MeetAttempts);
    }
}

