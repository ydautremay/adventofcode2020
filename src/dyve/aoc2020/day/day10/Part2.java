package dyve.aoc2020.day.day10;

import dyve.aoc2020.input.InputReader;

import java.util.List;
import java.util.stream.Collectors;

public class Part2 {

    public static void main(String[] args)throws Exception{
        Part2 part1 = new Part2();
        part1.execute();
    }

    public void execute() throws Exception{
        List<String> entries = InputReader.readInput("10");

        entries.add("0");
        List<Integer> inputs = entries.stream().map(Integer::parseInt).sorted().collect(Collectors.toList());

        long nbArrangements = 1;

        for(int i = 0; i < inputs.size() - 1; i++){
            int nbConsecutive = 1;
            while(i < inputs.size() - 1 && inputs.get(i + 1) - inputs.get(i) == 1 ){
                nbConsecutive ++;
                i++;
            }
            if(nbConsecutive == 3){
                nbArrangements *= 2;
            }else if(nbConsecutive == 4){
                nbArrangements *= 4;
            }else if(nbConsecutive == 5){
                nbArrangements *= 7;
            }else if(nbConsecutive == 6){
                nbArrangements *= 13;
            }else if(nbConsecutive > 6){
                throw new AssertionError();
            }
        }
        System.out.println(nbArrangements);
    }
}
