package dyve.aoc2020.day.day5;

import dyve.aoc2020.input.InputReader;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Part2 {

    public static void main(String[] args)throws Exception{
        Part2 part1 = new Part2();
        part1.execute();
    }

    public void execute() throws Exception{
        List<String> entries = InputReader.readInput("5");

        List<Integer> ids = entries.stream().map(this::getId).sorted().collect(Collectors.toList());
        List<Integer> all = Stream.iterate(21, i -> i < 996, i -> i + 1).collect(Collectors.toList());

        all.removeAll(ids);
        System.out.println(all);
    }

    private int getId(String partitioning) {
        String rowPartition = partitioning.substring(0, 7);
        String columnPartition = partitioning.substring(7);

        int row = determine(rowPartition, 0, 127);
        int col = determine(columnPartition, 0, 7);

        return row * 8 + col;
    }

    private int determine(String partition, int min, int max){
        if (max - min == 1) {
            if(partition.equals("F") || partition.equals("L")){
                return min;
            }else{
                return max;
            }
        }
        int median = BigDecimal.valueOf(max).subtract(BigDecimal.valueOf(min)).divide(BigDecimal.valueOf(2), RoundingMode.CEILING).intValue();
        if(partition.charAt(0) == 'F' || partition.charAt(0) == 'L'){
            return determine(partition.substring(1), min, min + median - 1);
        }else{
            return determine(partition.substring(1), min + median, max);
        }
    }
}
