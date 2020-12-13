package dyve.aoc2020.day.day1;

import dyve.aoc2020.input.InputReader;

import java.util.List;
import java.util.stream.Collectors;

public class Part2 {

    public static void main(String[] args)throws Exception{
        Part2 part1 = new Part2();
        part1.execute();
    }

    public void execute() throws Exception{
        List<String> entries = InputReader.readInput("1");
        List<Integer> values = entries.stream().map(e -> Integer.parseInt(e)).collect(Collectors.toList());

        outer : for (int i = 0; i < values.size(); i++){
            for(int j = 0; j < values.size(); j++){
                if(i == j){
                    continue;
                }
                int sum = values.get(i) + values.get(j);
                int diff = 2020 - sum;

                if(values.contains(diff)){
                    System.out.println(values.get(i) * values.get(j) * diff);
                    break outer;
                }
            }
        }
    }
}
