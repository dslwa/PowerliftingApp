package com.example.powerlifting_app;

import com.example.powerlifting_app.Core.*;
import com.example.powerlifting_app.GUI.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.time.LocalDate;

public class HelloController {
    private final Competition competition = new Competition();

    public HelloController() {
        competition.addResult(new Result(
                new Athlete(1,"Adam", "Nowak", 25, true, "IPF", 1.78, 83.5, 83),
                210, 140, 250,
                LocalDate.of(2024, 3, 15)
        ));

        competition.addResult(new Result(
                new Athlete(2,"Piotr", "Kowalski", 22, true, "IPF", 1.80, 92.3, 93),
                225, 155, 270,
                LocalDate.of(2024, 4, 2)
        ));
    }

    @FXML
    private Button addAthleteButton;

    @FXML
    private Button showCompetitionButton;

    @FXML
    private Button calculatorButton;

    @FXML
    protected void onAddAthleteClick() {
        AthleteForm.showForm(competition);
    }

    @FXML
    protected void onShowCompetitionClick() {
        CompetitionTable.showTable(competition);
    }

    @FXML
    protected void onCalculatorClick() {
        Calculator.calculatorDisplay();
    }
}
