package com.example.powerlifting_app.GUI;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;


public class Calculations {
    public static void BMI(VBox root) {
        root.getChildren().clear();

        TextField heightField = new TextField();
        heightField.setPromptText("Enter height in meters");

        TextField weightField = new TextField();
        weightField.setPromptText("Enter weight in kg");

        Label resultLabel = new Label();
        Label comLabel = new Label();
        Button calculateBtn = new Button("Calculate");
        Button backBtn = new Button("Back");

        calculateBtn.setOnAction(e -> {
            try {
                double height = Double.parseDouble(heightField.getText());
                double weight = Double.parseDouble(weightField.getText());
                double bmi = weight / (height * height);
                resultLabel.setText("Your BMI is: " + String.format("%.2f", bmi));
                if(bmi < 18.5){
                    comLabel.setText("Eat More!!");
                }else if (bmi >= 18.5 && bmi <= 25){
                    comLabel.setText("Your weight is Excellent");
                }else{
                    comLabel.setText("Better go on a diet");
                }
            } catch (NumberFormatException ex) {
                resultLabel.setText("Enter valid numbers!");
            }
        });

        backBtn.setOnAction(e -> Calculator.showMainMenu());

        root.getChildren().addAll(heightField, weightField, calculateBtn, resultLabel, comLabel ,backBtn);
    }
    public static void IPG_GL(VBox root){

        root.getChildren().clear();
        Button backBtn = new Button("Back");
        Button calculateBtn = new Button("Calculate");
        TextField weight = new TextField();
        weight.setPromptText("Enter weight in KG");
        TextField total = new TextField();
        total.setPromptText("Enter total in KG");

        ComboBox<String> sexBox = new ComboBox<>();
        sexBox.getItems().addAll("Male", "Female");

        ComboBox<String> eventTypeBox = new ComboBox<>();
        eventTypeBox.getItems().addAll("Classic 3-Lift", "Equipped 3-Lift", "Classic Bench", "Equipped Bench");

        Label resultLabel = new Label("Your GL is: ");

        calculateBtn.setOnAction(ev -> {
            try {
                double w = Double.parseDouble(weight.getText());
                double t = Double.parseDouble(total.getText());
                String sex = sexBox.getValue();
                String type = eventTypeBox.getValue();

                double A = 0, B = 0, C = 0;

                if (sex == null || type == null) {
                    resultLabel.setText("Please select both sex and event type.");
                    return;
                }

                // Male
                if (sex.equals("Male")) {
                    switch (type) {
                        case "Equipped 3-Lift" -> {
                            A = 1236.25115;
                            B = 1449.21864;
                            C = 0.01644;
                        }
                        case "Classic 3-Lift" -> {
                            A = 1199.72839;
                            B = 1025.18162;
                            C = 0.00921;
                        }
                        case "Equipped Bench" -> {
                            A = 381.22073;
                            B = 733.79378;
                            C = 0.02398;
                        }
                        case "Classic Bench" -> {
                            A = 320.98041;
                            B = 281.40258;
                            C = 0.01008;
                        }
                    }
                }

                // Female
                if (sex.equals("Female")) {
                    switch (type) {
                        case "Equipped 3-Lift" -> {
                            A = 758.63878;
                            B = 949.31382;
                            C = 0.02435;
                        }
                        case "Classic 3-Lift" -> {
                            A = 610.32796;
                            B = 1045.59282;
                            C = 0.03048;
                        }
                        case "Equipped Bench" -> {
                            A = 221.82209;
                            B = 357.00377;
                            C = 0.02937;
                        }
                        case "Classic Bench" -> {
                            A = 142.40398;
                            B = 442.52671;
                            C = 0.04724;
                        }
                    }
                }

                double coeff = 100 / (A - B * Math.exp(-C * w));
                double ipfgl = coeff * t;
                resultLabel.setText("IPF GL Score: " + String.format("%.2f", ipfgl));
            } catch (Exception ex) {
                resultLabel.setText("Invalid input!");
            }
        });
        backBtn.setOnAction(ev -> Calculator.showMainMenu());
        root.getChildren().addAll(weight, total, sexBox, eventTypeBox , calculateBtn, backBtn, resultLabel);
    }

    public static void FFMI(VBox root){
        root.getChildren().clear();

        TextField BW = new TextField();
        BW.setPromptText("Enter Bodyweight in KG");
        TextField height = new TextField();
        height.setPromptText("Enter height in meters");
        TextField bodyfat = new TextField();
        bodyfat.setPromptText("Enter BF in %");
        Button backBtn = new Button("Back");
        Button calculateBtn = new Button("Calculate");
        Label res =  new Label("Your FFMI is ");
        Label com = new Label();

        calculateBtn.setOnAction(e -> {
            try {
                double bw = Double.parseDouble(BW.getText());
                double h = Double.parseDouble(height.getText());
                double bf = Double.parseDouble(bodyfat.getText());

                double ffmi = bw * (1 - bf / 100) / Math.pow(h, 2);
                res.setText(String.format("Your FFMI is " + "%2f", ffmi));

                if (ffmi < 18) {
                    com.setText("You are weak / Natural");
                } else if (ffmi >= 18 && ffmi <= 20) {
                    com.setText("You are average / Natural");
                } else if (ffmi > 20 && ffmi <= 22) {
                    com.setText("You are lifting some weights / Above average / Natural");
                } else if (ffmi > 22 && ffmi <= 25) {
                    com.setText("Respect for you / Natural bodybuilder");
                } else {
                    com.setText("You are taking some gear Bro");
                }
            }catch (Exception exception){
                res.setText("Please enter valid data");
            }
        });

        backBtn.setOnAction(e -> Calculator.showMainMenu());
        root.getChildren().addAll(BW, height, bodyfat, calculateBtn, res, com, backBtn);
    }
    public static void RM(VBox root){
        root.getChildren().clear();

        TextField Weight = new TextField();
        Weight.setPromptText("Enter Weight in kg");
        TextField Reps = new TextField();
        Reps.setPromptText("Enter number of reps");

        Button calculateBtn =  new Button("Calcualte");
        Button backBtn = new Button("back");

        Label res = new Label();

        calculateBtn.setOnAction(ev -> {
            try {
                double reps = Double.parseDouble(Reps.getText());
                double weight = Double.parseDouble(Weight.getText());
                double rm = (100 * weight) / (101.3 - 2.67123 * reps);
                res.setText(String.format("Your one rep max is %2f", rm));
            }catch (Exception ex){
                res.setText("Please enter valid data");
            }
        });

        backBtn.setOnAction(ev -> Calculator.showMainMenu());
        root.getChildren().addAll(Weight, Reps, calculateBtn, res, backBtn);
    }

    public static void BMR(VBox root){
        root.getChildren().clear();

        TextField weightField = new TextField();
        weightField.setPromptText("Enter weight (kg)");

        TextField heightField = new TextField();
        heightField.setPromptText("Enter height (cm)");

        TextField ageField = new TextField();
        ageField.setPromptText("Enter age");

        ComboBox<String> sexBox = new ComboBox<>();
        sexBox.getItems().addAll("Male", "Female");

        Button calcBtn = new Button("Calculate");
        Button backBtn = new Button("Back");

        Label resultLabel = new Label();

        calcBtn.setOnAction(e -> {
            try {
                double weight = Double.parseDouble(weightField.getText());
                double height = Double.parseDouble(heightField.getText());
                int age = Integer.parseInt(ageField.getText());
                String sex = sexBox.getValue();

                double bmr;
                if ("Male".equals(sex)) {
                    bmr = 10 * weight + 6.25 * height - 5 * age + 5;
                } else {
                    bmr = 10 * weight + 6.25 * height - 5 * age - 161;
                }

                resultLabel.setText("Your BMR is: " + String.format("%.2f", bmr) + " kcal/day");

            } catch (Exception ex) {
                resultLabel.setText("Please enter valid data!");
            }
        });

        backBtn.setOnAction(e -> Calculator.showMainMenu());

        root.getChildren().addAll(weightField, heightField, ageField, sexBox, calcBtn, resultLabel, backBtn);
    }

    private static double getRpeFactor(int reps, double rpe) {
        // uproszczona tabela RPE (możesz rozbudować)
        double[][] rpeTable = {
                // RPE 6.0 ... RPE 10.0
                {0.86, 0.89, 0.92, 0.94, 0.96}, // 1 rep
                {0.82, 0.85, 0.88, 0.91, 0.94}, // 2 reps
                {0.79, 0.82, 0.86, 0.89, 0.92}, // 3 reps
                {0.76, 0.79, 0.83, 0.86, 0.89}, // 4 reps
                {0.74, 0.77, 0.80, 0.84, 0.87}, // 5 reps
        };

        int row = Math.max(0, Math.min(reps - 1, rpeTable.length - 1));
        int col = switch (String.valueOf(rpe)) {
            case "6" -> 0;
            case "7" -> 1;
            case "8" -> 2;
            case "9" -> 3;
            case "10" -> 4;
            default -> 2; // fallback: RPE 8
        };

        return rpeTable[row][col];
    }
    public static void MeetAttemptsCalculator(VBox root) {
        root.getChildren().clear();

        TextField weightField = new TextField();
        weightField.setPromptText("Enter weight lifted (kg)");

        TextField repsField = new TextField();
        repsField.setPromptText("Enter number of reps");

        ComboBox<String> rpeBox = new ComboBox<>();
        rpeBox.getItems().addAll("6", "7", "8", "9", "10");
        rpeBox.setPromptText("Select RPE");

        Button calculateBtn = new Button("Calculate Attempts");
        Button backBtn = new Button("Back");

        Label resultLabel = new Label();
        Label first = new Label();
        Label second = new Label();
        Label third = new Label();

        calculateBtn.setOnAction(e -> {
            try {
                double weight = Double.parseDouble(weightField.getText());
                int reps = Integer.parseInt(repsField.getText());
                double rpe = Double.parseDouble(rpeBox.getValue());

                double factor = getRpeFactor(reps, rpe);
                double est1RM = weight / factor;

                first.setText("1st Attempt (~90%): " + String.format("%.1f", est1RM * 0.9) + " kg");
                second.setText("2nd Attempt (~95%): " + String.format("%.1f", est1RM * 0.95) + " kg");
                third.setText("3rd Attempt (~100%): " + String.format("%.1f", est1RM) + " kg");

                resultLabel.setText("Estimated 1RM: " + String.format("%.1f", est1RM) + " kg");
            } catch (Exception ex) {
                resultLabel.setText("Please enter valid inputs.");
            }
        });

        backBtn.setOnAction(e -> Calculator.showMainMenu());

        root.getChildren().addAll(weightField, repsField, rpeBox, calculateBtn, resultLabel, first, second, third, backBtn);
    }
}