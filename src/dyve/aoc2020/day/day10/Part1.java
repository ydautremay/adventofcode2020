package dyve.aoc2020.day.day10;

import dyve.aoc2020.input.InputReader;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Part1 {

    public static void main(String[] args)throws Exception{
        Part1 part1 = new Part1();
        part1.execute();
    }

    public void execute() throws Exception{
        List<String> entries = InputReader.readInput("10");


        List<Integer> inputs = entries.stream().map(Integer::parseInt).sorted().collect(Collectors.toList());
        System.out.println(inputs);

        int diffs1 = 0;
        int diffs3 = 0;

        if(inputs.get(0) == 1){
            diffs1++;
        }else{
            diffs3++;
        }

        for(int i = 1; i < inputs.size(); i++){
            if(inputs.get(i) - inputs.get(i - 1) == 1){
                diffs1++;
            }else{
                diffs3++;
            }
        }
        diffs3++;

        System.out.println(diffs1 * diffs3);
    }
}
