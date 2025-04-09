package com.example.powerlifting_app.GUI;

import com.example.powerlifting_app.Core.Athlete;
import com.example.powerlifting_app.Core.Competition;
import com.example.powerlifting_app.Core.Result;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AthleteForm{
    public static void showForm(Competition competition){
        Stage formStage = new Stage();
        formStage.setTitle("Athlete Addition");
        GUIUtils.setAppIcon(formStage);
        VBox formRoot = new VBox();
        Scene formScene = new Scene(formRoot, 300, 500);
        formScene.getStylesheets().add(AthleteForm.class.getResource("/com/example/powerlifting_app/style.css").toExternalForm());
        formStage.setScene(formScene);

        TextField nameField = new TextField();
        nameField.setPromptText("Name");

        TextField surnameField = new TextField();
        surnameField.setPromptText("Surname");

        TextField ageField = new TextField();
        ageField.setPromptText("Ahe");

        TextField sexField = new TextField();
        sexField.setPromptText("Sex");

        TextField federationField = new TextField();
        federationField.setPromptText("Federation");

        TextField heightField = new TextField();
        heightField.setPromptText("Height (eg. 1.75)");

        TextField weightField = new TextField();
        weightField.setPromptText("Weight");

        TextField weightCategoryField = new TextField();
        weightCategoryField.setPromptText("Weight Category");

        TextField squatField = new TextField();
        squatField.setPromptText("Squat");

        TextField benchField = new TextField();
        benchField.setPromptText("Bench");

        TextField deadliftField = new TextField();
        deadliftField.setPromptText("Deadlift");

        Button saveButton = new Button("Save");

        formRoot.getChildren().addAll(
                nameField, surnameField, ageField, sexField, federationField,
                heightField, weightField, weightCategoryField,
                squatField, benchField, deadliftField,
                saveButton
        );

        formStage.show();

        saveButton.setOnAction(ev -> {
            try {
                String name = nameField.getText();
                String surname = surnameField.getText();
                int age = Integer.parseInt(ageField.getText());
                boolean sex = sexField.getText().equalsIgnoreCase("m");
                String federation = federationField.getText();
                double height = Double.parseDouble(heightField.getText());
                double weight = Double.parseDouble(weightField.getText());
                int weightCat = Integer.parseInt(weightCategoryField.getText());
                double squat = Double.parseDouble(squatField.getText());
                double bench = Double.parseDouble(benchField.getText());
                double deadlift = Double.parseDouble(deadliftField.getText());

                Athlete newAthlete = new Athlete(name, surname, age, sex, federation, height, weight, weightCat);
                Result newResult = new Result(newAthlete, squat, bench, deadlift);

                competition.addResult(newResult);
                System.out.println("Athlete Added: " + name + " " + surname);
                formStage.close();
            } catch (Exception ex) {
                System.out.println("Wrong format of a recordw!");
            }
        });
    }
}
