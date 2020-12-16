package dyve.aoc2020.day.day16;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Ticket {

    List<Integer> values;

    public Ticket(List<Integer> values) {
        this.values = values;
    }

    public Ticket(String entry){
        values = Arrays.stream(entry.split(",")).map(Integer::parseInt).collect(Collectors.toList());
    }
}
