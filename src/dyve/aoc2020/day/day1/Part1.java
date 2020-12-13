package dyve.aoc2020.day.day1;

import dyve.aoc2020.input.InputReader;

import java.util.List;

public class Part1 {

    public static void main(String[] args)throws Exception{
        Part1 part1 = new Part1();
        part1.execute();
    }

    public void execute() throws Exception{
        List<String> entries = InputReader.readInput("1");


        for (String entry : entries){
            int value = Integer.parseInt(entry);
            int diff = 2020 - value;

            if(entries.contains(String.valueOf(diff))){
                System.out.println(value * diff);
                break;
            }
        }
    }
}
