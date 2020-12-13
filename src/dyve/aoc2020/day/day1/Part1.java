package dyve.aoc2020.day.day1;

import dyve.aoc2020.day.Part;
import dyve.aoc2020.input.InputReader;

import java.util.List;
import java.util.stream.Collectors;

public class Part1 extends Part {

    public static void main(String[] args)throws Exception{
        new Part1().subMain(1);
    }

    @Override
    public Object execute(InputReader inputReader){
        List<String> entries = inputReader.stream().collect(Collectors.toList());

        for (String entry : entries){
            int value = Integer.parseInt(entry);
            int diff = 2020 - value;

            if(entries.contains(String.valueOf(diff))){
                return (value * diff);
            }
        }
        return "";
    }
}
