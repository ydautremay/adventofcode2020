package dyve.aoc2020.day.day2;

import dyve.aoc2020.input.InputReader;

import java.util.Arrays;
import java.util.List;

public class Part1 {

    public static void main(String[] args)throws Exception{
        Part1 part1 = new Part1();
        part1.execute();
    }

    private void execute() throws Exception{
        List<String> entries = InputReader.readInput("2");
        int nbValid = 0;

        for(String line : entries){
            String[] parts = line.split(":");

            String password = parts[1];
            String[] rules = parts[0].split(" ");
            String occ = rules[0];
            String letter = rules[1];

            String[] limits = occ.split("-");
            int min = Integer.parseInt(limits[0]);
            int max = Integer.parseInt(limits[1]);

            long nbOcc = password.chars().filter(c -> c == letter.charAt(0)).count();

            if(nbOcc >= min && nbOcc <= max){
                nbValid++;
            }
        }
        System.out.println(nbValid);
    }
}
