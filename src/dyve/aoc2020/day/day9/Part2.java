package dyve.aoc2020.day.day9;

import dyve.aoc2020.input.InputReader;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Part2 {

    public static void main(String[] args)throws Exception{
        Part2 part1 = new Part2();
        part1.execute();
    }

    public void execute() throws Exception{
        List<String> entries = InputReader.readInput("9");
        List<Long> input = entries.stream().map(Long::parseLong).collect(Collectors.toList());

        long invalid = IntStream.range(25, input.size()).filter(i -> !isValid(i, input)).mapToLong(input::get).findFirst().orElse(0);
        System.out.printf("invalid = %d%n", invalid);

        long sum = 0;
        loop: for(int i = 0; i < input.size(); i++){
            long total = 0;
            int cursor = i;
            long smallest = Long.MAX_VALUE;
            long largest = 0;
            while (total <= invalid && cursor < input.size()){
                smallest = Long.min(input.get(cursor), smallest);
                largest = Long.max(input.get(cursor), largest);
                total += input.get(cursor);
                if(total == invalid){
                    System.out.printf("from %d to %d%n", i,  cursor, invalid);
                    for(int j = i; j <= cursor; j++){
                        System.out.printf("%d + ", input.get(j));
                    }
                    System.out.printf(" = %d%n", total);
                    sum = smallest + largest;
                    break loop;
                }
                cursor++;
            }
        }
        System.out.println(sum);
    }

    private boolean isValid(int i, List<Long> input) {
        for(int j = i-25; j < i; j++){
            for(int k = j; k < i; k++){
                if(input.get(j) + input.get(k)== input.get(i)){
                    return true;
                }
            }
        }
        return false;
    }
}
