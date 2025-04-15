package com.example.powerlifting_app.GUI;

import com.example.powerlifting_app.Core.Competition;
import com.example.powerlifting_app.Core.Result;
import com.example.powerlifting_app.Utils.CSVUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.Comparator;
import java.util.List;

public class CompetitionTable {
    public static void showTable(Competition competition){
        Stage tableStage = new Stage();
        GUIUtils.setAppIcon(tableStage);
        tableStage.setTitle("Competition Table");

        Button sortByGL = new Button("Sort by IPFGL");
        Button sortByWeight = new Button("Sort by Weight");
        Button sortByTotal = new Button("Sort by Total");
        Button exportBtn = new Button("Export CSV");
        Button importBtn = new Button("Import CSV");

        TextField searchField = new TextField();
        searchField.setPromptText("Search by name:");
        TextField searchField2 = new TextField();
        searchField2.setPromptText("Search By Federation:");

        TableView<Result> tableView = new TableView<>();
        ObservableList<Result> shownList = FXCollections.observableArrayList(competition.getResults());
        tableView.setItems(shownList);

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            shownList.setAll(competition.getResults().stream()
                    .filter(r -> r.getAthlete().getName().toLowerCase().contains(newValue.toLowerCase()))
                    .toList());
        });

        searchField2.textProperty().addListener((observableValue, s, t1) -> {
            shownList.setAll(competition.getResults().stream()
                    .filter(r -> r.getAthlete().getFederation().toLowerCase().contains(t1.toLowerCase()))
                    .toList());
        });

        sortByGL.setOnAction(ev -> {
            competition.sortByGL();
            shownList.setAll(competition.getResults());
        });

        sortByWeight.setOnAction(ev -> {
            competition.sortByBW();
            shownList.setAll(competition.getResults());
        });

        sortByTotal.setOnAction(ev -> {
            competition.sortByTotal();
            shownList.setAll(competition.getResults());
        });

        exportBtn.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save CSV");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV files", "*.csv"));
            File file = fileChooser.showSaveDialog(tableStage);
            if (file != null) {
                CSVUtils.exportToCSV(competition, file.getAbsolutePath());
            }
        });

        importBtn.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open CSV");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV files", "*.csv"));
            File file = fileChooser.showOpenDialog(tableStage);
            if (file != null) {
                CSVUtils.importFromCSV(competition, file.getAbsolutePath());
                shownList.setAll(competition.getResults());
            }
        });

        TableColumn<Result, String> IDCol = new TableColumn<>("ID");
        IDCol.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(
                        String.valueOf(cellData.getValue().getAthlete_id())));

        TableColumn<Result, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(
                        cellData.getValue().getAthlete().getName()));

        TableColumn<Result, String> surnameCol = new TableColumn<>("Surname");
        surnameCol.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(
                        cellData.getValue().getAthlete().getSurname()));

        TableColumn<Result, String> weightCol = new TableColumn<>("Weight");
        weightCol.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(
                        String.valueOf(cellData.getValue().getAthlete().getWeight())));

        TableColumn<Result, String> weight_catCol = new TableColumn<>("Weight Category");
        weight_catCol.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(
                        String.valueOf(cellData.getValue().getAthlete().getWeight_category())));

        TableColumn<Result, String> totalCol = new TableColumn<>("Total");
        totalCol.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(
                        String.valueOf(cellData.getValue().getTotal())));

        TableColumn<Result, String> fedCol = new TableColumn<>("Federation");
        fedCol.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(
                        cellData.getValue().getAthlete().getFederation()));

        TableColumn<Result, String> glCol = new TableColumn<>("IPF GL");
        glCol.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(
                        String.format("%.2f", cellData.getValue().calcualteIPFGL())));

        TableColumn<Result, String> ageCol = new TableColumn<>("Age");
        ageCol.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(
                        String.valueOf(cellData.getValue().getAthlete().getAge())));

        tableView.getColumns().addAll(IDCol, surnameCol, weightCol, weight_catCol, fedCol, totalCol, glCol, ageCol);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        tableView.setRowFactory(tv -> {
            TableRow<Result> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && !row.isEmpty()) {
                    Result selected = row.getItem();
                    showLifterCharts(competition, selected.getAthlete_id());
                }
            });
            return row;
        });

        VBox vbox = new VBox(tableView);
        HBox Buttons = new HBox(10, sortByWeight, sortByGL, sortByTotal, exportBtn, importBtn);
        vbox.getChildren().addAll(Buttons, searchField, searchField2);

        Scene scene = new Scene(vbox, 600, 400);
        scene.getStylesheets().add(AthleteForm.class.getResource("/com/example/powerlifting_app/style.css").toExternalForm());
        tableStage.setScene(scene);
        tableStage.show();
    }

    private static void showLifterCharts(Competition competition, int athleteId) {
        List<Result> lifterResults = competition.getResults().stream()
                .filter(r -> r.getAthlete_id() == athleteId)
                .sorted(Comparator.comparing(Result::getDate))
                .toList();

        XYChart.Series<Number, Number> weightSeries = new XYChart.Series<>();
        weightSeries.setName("Total vs Weight");

        double minWeight = Double.MAX_VALUE, maxWeight = Double.MIN_VALUE;
        double minTotal = Double.MAX_VALUE, maxTotal = Double.MIN_VALUE;

        for (Result r : lifterResults) {
            double weight = r.getAthlete().getWeight();
            double total = r.getTotal();
            weightSeries.getData().add(new XYChart.Data<>(weight, total));
            minWeight = Math.min(minWeight, weight);
            maxWeight = Math.max(maxWeight, weight);
            minTotal = Math.min(minTotal, total);
            maxTotal = Math.max(maxTotal, total);
        }

        NumberAxis x1 = new NumberAxis(minWeight - 2, maxWeight + 2, 1);
        x1.setLabel("Weight (kg)");

        NumberAxis y1 = new NumberAxis(minTotal - 10, maxTotal + 10, 10);
        y1.setLabel("Total");

        ScatterChart<Number, Number> weightChart = new ScatterChart<>(x1, y1);
        weightChart.setTitle("Total vs Weight");
        weightChart.getData().add(weightSeries);

        VBox root = new VBox(weightChart);
        Scene scene = new Scene(root, 800, 600);
        Stage stage = new Stage();
        stage.setTitle("Progress for Athlete ID: " + athleteId);
        stage.setScene(scene);
        stage.show();
    }
}
