package dyve.aoc2020.day.day16;

public class Rule {

    String name;

    int min1;

    int max1;

    int min2;

    int max2;

    public Rule(int min1, int max1, int min2, int max2) {
        this.min1 = min1;
        this.max1 = max1;
        this.min2 = min2;
        this.max2 = max2;
    }

    public Rule(String name, int min1, int max1, int min2, int max2) {
        this.name = name;
        this.min1 = min1;
        this.max1 = max1;
        this.min2 = min2;
        this.max2 = max2;
    }

    public boolean verify(int input){
        return (input >= min1 && input <= max1) || (input >= min2 && input <= max2);
    }

    public String toString(){
        return name;
    }
}
