public class Main {
    public static void main(String args[]){
        Competition zawody = new Competition();
        zawody.addResult(new Results(new Athlete("Adam", "Nowak", 28, true, "IPF", 1.75, 83.5, 83), 220, 145, 260));
        zawody.addResult(new Results(new Athlete("Piotr", "Kowalski", 24, true, "IPF", 1.80, 92.0, 93), 235, 160, 270));
        zawody.addResult(new Results(new Athlete("Kacper", "Wójcik", 22, true, "IPF", 1.72, 73.4, 74), 200, 130, 230));
        zawody.addResult(new Results(new Athlete("Jan", "Zieliński", 31, true, "IPF", 1.82, 101.2, 105), 250, 170, 285));
        zawody.addResult(new Results(new Athlete("Marek", "Dąbrowski", 26, true, "IPF", 1.68, 65.0, 66), 180, 120, 210));
        zawody.addResult(new Results(new Athlete("Paweł", "Szymański", 29, true, "IPF", 1.90, 108.3, 120), 270, 180, 300));
        zawody.addResult(new Results(new Athlete("Karol", "Lewandowski", 25, true, "IPF", 1.78, 85.7, 93), 225, 150, 250));
        zawody.addResult(new Results(new Athlete("Filip", "Król", 23, true, "IPF", 1.74, 69.2, 74), 190, 125, 215));
        zawody.addResult(new Results(new Athlete("Tomasz", "Lis", 27, true, "IPF", 1.76, 88.1, 93), 240, 155, 265));
        zawody.addResult(new Results(new Athlete("Michał", "Góral", 30, true, "IPF", 1.85, 95.3, 105), 245, 165, 275));
        zawody.addResult(new Results(new Athlete("Daniel", "Salawa", 20, true, "IPF", 1.72, 73.5, 74), 175, 125, 230));
        zawody.printAll();
        zawody.sortByBW();
        zawody.printAll();
    }
}
