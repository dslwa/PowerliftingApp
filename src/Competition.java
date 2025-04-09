import java.util.ArrayList;

public class Competition {
    private ArrayList<Results> results;

    Competition(){
        this.results = new ArrayList<>();
    }
    public void addResult(Results res){
        results.add(res);
    }
    public void sortByTotal(){
        results.sort((r1, r2) -> Double.compare(r1.getTotal(),r2.getTotal()));
    }
    public void sortByBW(){
        results.sort((r1, r2) ->
                Double.compare(r1.getAthlete().getWeight(), r2.getAthlete().getWeight()));
    }
    public void sortByGL(){
        results.sort((r1, r2) ->
                Double.compare(r2.calcualteIPFGL(), r1.calcualteIPFGL()));
    }
    public void printAll() {
        for (Results r : results) {
            r.print();
            System.out.println("----------------------");
        }
    }
}
