package dyve.aoc2020.day.day5;

import dyve.aoc2020.input.InputReader;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.List;

public class Part1 {

    public static void main(String[] args)throws Exception{
        Part1 part1 = new Part1();
        part1.execute();
    }

    public void execute() throws Exception{
        List<String> entries = InputReader.readInput("5");

        int maxId = entries.stream().mapToInt(this::getId).max().orElse(0);

        System.out.println(maxId);
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
