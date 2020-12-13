package dyve.aoc2020.day.day13;

import dyve.aoc2020.input.InputReader;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Part1 {

    public static void main(String[] args)throws Exception{
        Part1 part1 = new Part1();
        part1.execute();
    }

    public void execute() throws Exception{
        List<String> entries = InputReader.readInput("13");
        long timestamp = Long.parseLong(entries.get(0));
        System.out.println(timestamp);

        Map<Long, Long> schedules = new HashMap<>();
        String buses = entries.get(1);
        for(String split : buses.split(",")){
            long nextPassage = 0;
            if(split.equals("x")){
                continue;
            }
            long bus = Long.parseLong(split);
            long nbLoops = timestamp / bus;
            System.out.println("nbLoops " + nbLoops);
            nextPassage = (nbLoops + 1) * bus;
            System.out.println(bus + " : " + nextPassage);
            schedules.put(bus, nextPassage);
        }

        Map.Entry<Long, Long> min = schedules.entrySet().stream().min(Map.Entry.comparingByValue()).get();
        long wait = min.getValue() - timestamp;
        System.out.println(min.getKey() * wait);
    }
}
