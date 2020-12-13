package dyve.aoc2020.day.day7;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Bag {

    String color;

    Map<Bag, Integer> bags = new HashMap<>();

    public Bag(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bag bag = (Bag) o;
        return color.equals(bag.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }

    @Override
    public String toString() {
        return color;
    }

    public String toLongString(){
        return color + " -> " + bags.keySet();
    }
}
