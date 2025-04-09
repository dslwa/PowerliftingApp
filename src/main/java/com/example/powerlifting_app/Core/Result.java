package com.example.powerlifting_app.Core;

public class Result implements Printable {
    private int athlete_id;
    private Athlete athlete;
    private double squat;
    private double bench;
    private double deadlift;
    private double total;
    public Result(Athlete a1, double sq, double bp, double dl){
        this.athlete_id = a1.getId();
        this.athlete = a1;
        this.squat = sq;
        this.bench = bp;
        this.deadlift = dl;
        this.total = sq + bp + dl;
    }
    public Athlete getAthlete(){
        return this.athlete;
    }
    public int getAthlete_id(){ return this.athlete_id; }
    public double getSquat() {
        return this.squat;
    }
    public double getBench() {
        return this.bench;
    }
    public double getDeadlift() {
        return this.deadlift;
    }
    public double getTotal() {
        return this.total;
    }

    public double calcualteIPFGL(){
        double weight = this.athlete.getWeight();
        boolean sex = this.athlete.getSex();
        double A, B, C;
        double IPF_GL;
        if(sex == true){
            A = 1199.72839;
            B = 1025.18162;
            C = 0.00921;
        }else{
            A = 610.32769;
            B = 1045.59282;
            C = 0.03048;
        }
        IPF_GL = 100 / (A - B * Math.exp(-C * weight));
        return IPF_GL * this.total;
    }
    @Override
    public void print(){
        System.out.println("Name: " + this.athlete.getName());
        System.out.println("Surname: " + this.athlete.getSurname());
        System.out.println("Total: " + this.total);
        System.out.println("Weight: " + this.athlete.getWeight());
        System.out.println("IPF_GL: " + calcualteIPFGL());

    }
}
