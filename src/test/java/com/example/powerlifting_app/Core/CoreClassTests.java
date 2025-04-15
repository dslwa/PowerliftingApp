package com.example.powerlifting_app.Core;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CoreClassTests {

    @Test
    void testAthleteBMI() {
        Athlete athlete = new Athlete(1, "Jan", "Kowalski", 28, true, "IPF", 1.80, 90.0, 93);
        double expectedBMI = 90.0 / (1.80 * 1.80);
        assertEquals(expectedBMI, athlete.calcualteBMI(), 0.01);
    }

    @Test
    void testResultTotalCalculation() {
        Athlete athlete = new Athlete(2, "Anna", "Nowak", 25, false, "IPF", 1.65, 63.0, 63);
        Result result = new Result(athlete, 150, 100, 180, LocalDate.now());
        assertEquals(430, result.getTotal());
    }

    @Test
    void testIPFGLMale() {
        Athlete athlete = new Athlete(3, "Piotr", "Lis", 30, true, "IPF", 1.75, 85.0, 93);
        Result result = new Result(athlete, 200, 150, 250, LocalDate.now());

        double A = 1199.72839;
        double B = 1025.18162;
        double C = 0.00921;
        double coeff = 100 / (A - B * Math.exp(-C * 85.0));
        double expectedGL = coeff * 600;

        assertEquals(expectedGL, result.calcualteIPFGL(), 0.01);
    }

    @Test
    void testIPGGLFemale() {
        Athlete athlete = new Athlete(4, "Maria", "Zielinska", 27, false, "IPF", 1.70, 60.0, 63);
        Result result = new Result(athlete, 120, 90, 150, LocalDate.now());

        double A = 610.32769;
        double B = 1045.59282;
        double C = 0.03048;
        double coeff = 100 / (A - B * Math.exp(-C * 60.0));
        double expectedGL = coeff * 360;

        assertEquals(expectedGL, result.calcualteIPFGL(), 0.01);
    }

    @Test
    void testCompetitionSortingByTotal() {
        Competition competition = new Competition();
        Athlete a1 = new Athlete(1, "A", "Z", 28, true, "IPF", 1.80, 90.0, 93);
        Athlete a2 = new Athlete(2, "B", "Y", 29, true, "IPF", 1.75, 85.0, 93);

        competition.addResult(new Result(a1, 200, 150, 250, LocalDate.now()));
        competition.addResult(new Result(a2, 220, 160, 270, LocalDate.now()));

        competition.sortByTotal();
        List<Result> results = competition.getResults();
        assertEquals(a1.getId(), results.get(0).getAthlete().getId());
        assertEquals(a2.getId(), results.get(1).getAthlete().getId());
    }

    @Test
    void testCompetitionSortingByBodyWeight() {
        Competition competition = new Competition();
        Athlete a1 = new Athlete(1, "C", "X", 24, true, "IPF", 1.75, 80.0, 83);
        Athlete a2 = new Athlete(2, "D", "W", 26, true, "IPF", 1.77, 75.0, 83);

        competition.addResult(new Result(a1, 180, 120, 220, LocalDate.now()));
        competition.addResult(new Result(a2, 180, 120, 220, LocalDate.now()));

        competition.sortByBW();
        List<Result> results = competition.getResults();
        assertEquals(a2.getId(), results.get(0).getAthlete().getId());
    }

    @Test
    void testCompetitionSortingByGL() {
        Competition competition = new Competition();
        Athlete a1 = new Athlete(1, "E", "V", 30, true, "IPF", 1.80, 95.0, 105);
        Athlete a2 = new Athlete(2, "F", "U", 22, true, "IPF", 1.80, 85.0, 93);

        competition.addResult(new Result(a1, 210, 140, 250, LocalDate.now()));
        competition.addResult(new Result(a2, 210, 140, 250, LocalDate.now()));

        competition.sortByGL();
        List<Result> results = competition.getResults();
        assertTrue(results.get(0).calcualteIPFGL() >= results.get(1).calcualteIPFGL());
    }
}