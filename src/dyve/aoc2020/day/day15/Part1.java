package dyve.aoc2020.day.day15;

import dyve.aoc2020.day.Part;
import dyve.aoc2020.input.InputReader;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Part1 extends Part {

    Map<Integer, List<Integer>> numbers = new HashMap<>();

    public static void main(String[] args)throws Exception{
        new Part1().subMain(15);
    }

    @Override
    public Object execute(InputReader inputReader){
        String entry = inputReader.stream().findFirst().get();
        List<Integer> start = Arrays.stream(entry.split(",")).map(Integer::parseInt).collect(Collectors.toList());
        int lastNumber = 0;
        for(int i = 0; i < start.size(); i++){
            List<Integer> turns = new ArrayList<>();
            turns.add(i + 1);
            numbers.put(start.get(i), turns);
            System.out.printf("turn : %d, spoken : %d%n", i + 1, start.get(i));
        }

        for(int i = start.size() + 1; i < 2020; i++){
            List<Integer> lastPair = numbers.computeIfAbsent(lastNumber, k -> new ArrayList<>());
            if(lastPair.size() == 0){
                lastPair.add(i);
                lastNumber = 0;
            }else {
                if (lastPair.size() == 1) {
                    lastPair.add(i);
                } else {
                    lastPair.set(0, lastPair.get(1));
                    lastPair.set(1, i);
                }
                lastNumber = lastPair.get(1) - lastPair.get(0);
            }
            if(i%1_000_000 == 0) {
                System.out.printf("turn %d, Spoken : %d%n", i, lastNumber);
            }
        }

        return lastNumber;
    }
}
