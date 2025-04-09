package com.example.powerlifting_app.GUI;

import com.example.powerlifting_app.Core.Competition;
import com.example.powerlifting_app.Core.Result;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CompetitionTable {
    public static void showTable(Competition competition){
        Stage tableStage = new Stage();
        GUIUtils.setAppIcon(tableStage);
        tableStage.setTitle("Competition Table");

        Button sortByGL = new Button("Sort by IPFGL");
        Button sortByWeight = new Button ("Sort by Weight ");
        Button sortByTotal = new Button(" Sort by Total ");


        TextField searchField = new TextField();
        searchField.setPromptText("Search by name:");
        TextField searchField2 = new TextField();
        searchField2.setPromptText("Search By Federation: ");



        TableView<Result> tableView = new TableView<>();
        ObservableList<Result> shownList = FXCollections.observableArrayList(competition.getResults());
        tableView.setItems(shownList);

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            shownList.setAll(competition.getResults().stream()
                    .filter(r -> r.getAthlete().getName().toLowerCase().contains(newValue.toLowerCase()))
                    .toList());
        });

        searchField2.textProperty().addListener(((observableValue, s, t1) -> {
            shownList.setAll(competition.getResults().stream()
                            .filter(r -> r.getAthlete().getFederation().toLowerCase().contains(t1.toLowerCase()))
                            .toList()
            );
        }));


        sortByGL.setOnAction(ev -> {
            competition.sortByGL();
            shownList.setAll(competition.getResults());
        } );

        sortByWeight.setOnAction(ev -> {
            competition.sortByBW();
            shownList.setAll(competition.getResults());
        });

        sortByTotal.setOnAction(ev ->{
            competition.sortByTotal();
            shownList.setAll(competition.getResults());
        });

        //ID
        TableColumn<Result, String> IDCol = new TableColumn<>(("ID"));
        IDCol.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(
                        String.valueOf(cellData.getValue().getAthlete_id())));
        // Name
        TableColumn<Result, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(
                        cellData.getValue().getAthlete().getName()));

        // Surname
        TableColumn<Result, String> surnameCol = new TableColumn<>("Surname");
        surnameCol.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(
                        cellData.getValue().getAthlete().getSurname()));

        // Weight
        TableColumn<Result, String> weightCol = new TableColumn<>("Weight");
        weightCol.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(
                        String.valueOf(cellData.getValue().getAthlete().getWeight())));
        // Weight_class
        TableColumn<Result, String> weight_catCol = new TableColumn<>("Weight Category");
        weight_catCol.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(
                        String.valueOf(cellData.getValue().getAthlete().getWeight_category())));
        // Total
        TableColumn<Result, String> totalCol = new TableColumn<>("Total");
        totalCol.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(
                        String.valueOf(cellData.getValue().getTotal())));
        //Federation
        TableColumn<Result, String> fedCol = new TableColumn<>("Federation");
        fedCol.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(String.valueOf(cellData.getValue().getAthlete().getFederation())));
        // IPF GL
        TableColumn<Result, String> glCol = new TableColumn<>("IPF GL");
        glCol.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(
                        String.format("%.2f", cellData.getValue().calcualteIPFGL())));

        TableColumn<Result, String> ageCol = new TableColumn<>("Age");
        ageCol.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(
                        String.format("%d", cellData.getValue().getAthlete().getAge())));

        tableView.getColumns().addAll(IDCol, surnameCol, weightCol, weight_catCol, fedCol, totalCol, glCol, ageCol);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        VBox vbox = new VBox(tableView);
        vbox.getChildren().add(sortByWeight);
        vbox.getChildren().add(sortByGL);
        vbox.getChildren().add(sortByTotal);
        vbox.getChildren().add(searchField);
        vbox.getChildren().add(searchField2);
        Scene scene = new Scene(vbox, 600, 400);
        scene.getStylesheets().add(AthleteForm.class.getResource("/com/example/powerlifting_app/style.css").toExternalForm());
        tableStage.setScene(scene);
        tableStage.show();
    }
}
