package dyve.aoc2020.day.day8;

import dyve.aoc2020.input.InputReader;

import java.util.*;
import java.util.stream.IntStream;

public class Part2 {

    public static void main(String[] args)throws Exception{
        Part2 part1 = new Part2();
        part1.execute();
    }

    public void execute() throws Exception{
        List<String> entries = InputReader.readInput("8");

        Map<Integer, String> commandsToReplace = new HashMap<>();
        IntStream.range(0, entries.size()).filter(i -> entries.get(i).matches("(nop|jmp) (\\+|-)\\d+")).forEach(i -> commandsToReplace.put(i, entries.get(i)));
        int accumulator = 0;
        for (int i : commandsToReplace.keySet()){
            List<String> newList = new ArrayList<>(entries);
            String command;
            if(commandsToReplace.get(i).contains("nop")){
                command = commandsToReplace.get(i).replaceAll("nop", "jmp");
            }else{
                command = commandsToReplace.get(i).replaceAll("jmp", "nop");
            }
            newList.set(i, command);
            try {
                accumulator = run(newList);
            }catch (Exception e){
                continue;
            }
            break;
        }
        System.out.println(accumulator);
    }

    private int run(List<String> entries) throws Exception{
        int cursor = 0;
        int accumulator = 0;
        Set<Integer> passedLines = new HashSet<>();

        while(cursor < entries.size()){
            if(passedLines.contains(cursor)){
                throw new Exception();
            }
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
        return accumulator;
    }
}
