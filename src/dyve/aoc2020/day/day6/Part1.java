package dyve.aoc2020.day.day6;

import dyve.aoc2020.input.InputReader;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Part1 {

    public static void main(String[] args)throws Exception{
        Part1 part1 = new Part1();
        part1.execute();
    }

    public void execute() throws Exception{
        List<String> entries = InputReader.readInput("6");

        List<Set<Character>> groups = new ArrayList<>();
        Set<Character> group = new HashSet<>();

        for(String entry : entries){
            System.out.println(entry);
            if(entry.isEmpty()){
                System.out.println(group);
                System.out.println();
                groups.add(group);
                group = new HashSet<>();
            }
            for(char c : entry.toCharArray()){
                group.add(c);
            }
        }
        groups.add(group);

        System.out.println(groups.stream().mapToInt(Set::size).sum());
    }
}
