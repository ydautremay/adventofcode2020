package dyve.aoc2020.day.day6;

import dyve.aoc2020.input.InputReader;

import java.util.*;

public class Part2 {

    public static void main(String[] args)throws Exception{
        Part2 part1 = new Part2();
        part1.execute();
    }

    public void execute() throws Exception{
        List<String> entries = InputReader.readInput("6");

        List<Map<Character, Integer>> groups = new ArrayList<>();
        Map<Character, Integer> group = new HashMap<>();

        int count = 0;

        for(int i = 0; i < entries.size(); i++){
            int nbPerson = 0;
            Map<Character, Integer> answers = new HashMap<>();
            while(i < entries.size() - 2 && !entries.get(i + 1).isEmpty()){
                nbPerson ++;
                i++;
                for(char c : entries.get(i).toCharArray()){
                    answers.merge(c, 1, Integer::sum);
                }
            }
            int finalNbPerson = nbPerson;
            count += answers.values().stream().filter(nb -> nb == finalNbPerson).count();
        }

        System.out.println(count);
    }
}
