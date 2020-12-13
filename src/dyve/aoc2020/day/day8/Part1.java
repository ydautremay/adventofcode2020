package dyve.aoc2020.day.day8;

import dyve.aoc2020.input.InputReader;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Part1 {

    public static void main(String[] args)throws Exception{
        Part1 part1 = new Part1();
        part1.execute();
    }

    public void execute() throws Exception{
        List<String> entries = InputReader.readInput("8");

        int cursor = 0;
        int accumulator = 0;
        Set<Integer> passedLines = new HashSet<>();

        while(cursor < entries.size() && !passedLines.contains(cursor)){
            passedLines.add(cursor);
            String entry = entries.get(cursor);
            String[] parts = entry.split(" ");
            String command = parts[0];
            int value = Integer.parseInt(parts[1]);
            switch (command){
                case "acc" -> {
                    accumulator += value;
                    cursor ++;
                }
                case "jmp" -> cursor += value;
                case "nop" -> cursor++;
            }
        }
        System.out.println(accumulator);
    }
}
