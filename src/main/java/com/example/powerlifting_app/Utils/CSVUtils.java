package com.example.powerlifting_app.Utils;

import com.example.powerlifting_app.Core.Athlete;
import com.example.powerlifting_app.Core.Competition;
import com.example.powerlifting_app.Core.Result;

import java.io.*;
import java.time.LocalDate;

public class CSVUtils {

    public static void exportToCSV(Competition competition, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("ID,Name,Surname,Age,Sex,Federation,Height,Weight,Weight Category,Squat,Bench,Deadlift,Total,IPF GL,Date\n");

            for (Result r : competition.getResults()) {
                var a = r.getAthlete();
                writer.write(String.format("%d,%s,%s,%d,%s,%s,%.2f,%.2f,%d,%.2f,%.2f,%.2f,%.2f,%.2f,%s\n",
                        a.getId(),
                        a.getName(),
                        a.getSurname(),
                        a.getAge(),
                        a.getSex() ? "Male" : "Female",
                        a.getFederation(),
                        a.getHeight(),
                        a.getWeight(),
                        a.getWeight_category(),
                        r.getSquat(),
                        r.getBench(),
                        r.getDeadlift(),
                        r.getTotal(),
                        r.calcualteIPFGL(),
                        r.getDate().toString()
                ));
            }

            System.out.println("Export successful!");
        } catch (IOException e) {
            System.out.println("Export failed: " + e.getMessage());
        }
    }

    public static void importFromCSV(Competition competition, String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine(); // skip header

            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");

                if (fields.length < 15) {
                    System.out.println("Invalid row: " + line);
                    continue;
                }

                int id = Integer.parseInt(fields[0]);
                String name = fields[1];
                String surname = fields[2];
                int age = Integer.parseInt(fields[3]);
                boolean sex = fields[4].equalsIgnoreCase("Male");
                String fed = fields[5];
                double height = Double.parseDouble(fields[6]);
                double weight = Double.parseDouble(fields[7]);
                int weightCategory = Integer.parseInt(fields[8]);
                double squat = Double.parseDouble(fields[9]);
                double bench = Double.parseDouble(fields[10]);
                double deadlift = Double.parseDouble(fields[11]);

                LocalDate date;
                try {
                    date = LocalDate.parse(fields[14]);
                } catch (Exception e) {
                    System.out.println("Invalid date format in line: " + line);
                    continue;
                }

                Athlete athlete = new Athlete(id, name, surname, age, sex, fed, height, weight, weightCategory);
                Result result = new Result(athlete, squat, bench, deadlift, date);
                competition.addResult(result);
            }

            System.out.println("Import successful!");
        } catch (Exception e) {
            System.out.println("Import failed: " + e.getMessage());
        }
    }
}