package com.example.powerlifting_app.Utils;

import com.example.powerlifting_app.Core.Athlete;
import com.example.powerlifting_app.Core.Competition;
import com.example.powerlifting_app.Core.Result;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class CSVUtilsTest {

    private static final String TEST_CSV_PATH = "test_export.csv";

    @BeforeEach
    public void prepareTestFile() {
        Competition competition = new Competition();
        Athlete a = new Athlete(1, "Test", "User", 25, true, "IPF", 1.80, 90.0, 93);
        Result r = new Result(a, 200, 140, 250, LocalDate.of(2024, 4, 1));
        competition.addResult(r);

        CSVUtils.exportToCSV(competition, TEST_CSV_PATH);
    }

    @Test
    public void testExportToCSV() {
        File file = new File(TEST_CSV_PATH);
        assertTrue(file.exists());
        assertTrue(file.length() > 0);
    }


    @AfterEach
    public void cleanup() {
        new File(TEST_CSV_PATH).delete();
    }
}