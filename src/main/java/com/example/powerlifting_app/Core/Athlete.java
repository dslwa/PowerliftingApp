package com.example.powerlifting_app.Core;

public class Athlete implements Printable {
    private static  int next_Id = 1;
    private int id;
    private String name;
    private String surname;
    private int age;
    private boolean sex; // false female true male
    private String federation;
    private double height; // in meters
    private double weight;
    private int weight_category;

    Athlete(){}
    public Athlete(String name, String surname, int age, boolean sex, String fed, double height, double weight, int weight_category) {
        this.id = next_Id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.sex = sex;
        this.federation = fed;
        this.height = height;
        this.weight = weight;
        this.weight_category = weight_category;
        next_Id++;
    }
    public int getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public String getSurname(){
        return this.surname;
    }
    public int getAge(){
        return this.age;
    }
    public boolean getSex(){
        return this.sex;
    }
    public String getFederation(){
        return this.federation;
    }
    public double getHeight(){
        return this.height;
    }
    public double getWeight(){
        return this.weight;
    }
    public int getWeight_category(){
        return this.weight_category;
    }
    public double calcualteBMI(){
        return weight / (height * height);
    }
    @Override
    public void print(){
        System.out.println("Name: " + this.name + "\n" +
                "Surname: " + this.surname + "\n" +
                "Age: " + this.age + "\n" +
                "Fedaration: " + this.federation + "\n" +
                "Height " + this.height + "\n" +
                "Weight: " + this.weight + "\n" +
                "Weight Category " + this.weight_category + "\n" +
                "BMI " + this.calcualteBMI() + "\n");
    }
}