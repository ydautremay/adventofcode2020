package dyve.aoc2020.day.day9;

import dyve.aoc2020.input.InputReader;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Part1 {

    public static void main(String[] args)throws Exception{
        Part1 part1 = new Part1();
        part1.execute();
    }

    public void execute() throws Exception{
        List<String> entries = InputReader.readInput("9");
        List<Long> input = entries.stream().map(Long::parseLong).collect(Collectors.toList());

        System.out.println(IntStream.range(25, input.size()).filter(i -> !isValid(i, input)).mapToLong(input::get).findFirst().orElse(0));
    }

    private boolean isValid(int i, List<Long> input) {
        for(int j = i-25; j < i; j++){
            for(int k = j; k < i; k++){
                if(input.get(j) + input.get(k)== input.get(i)){
                    System.out.printf("%d + %d = %d%n", input.get(j), input.get(k), input.get(i));
                    return true;
                }
            }
        }
        return false;
    }
}
