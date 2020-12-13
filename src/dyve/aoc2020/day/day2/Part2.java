package dyve.aoc2020.day.day2;

import dyve.aoc2020.input.InputReader;

import java.util.List;

public class Part2 {

    public static void main(String[] args)throws Exception{
        Part2 part2 = new Part2();
        part2.execute();
    }

    private void execute() throws Exception{
        List<String> entries = InputReader.readInput("2");
        int nbValid = 0;

        for(String line : entries){
            String[] parts = line.split(":");

            String password = parts[1].trim();
            String[] rules = parts[0].split(" ");
            String occ = rules[0];
            char letter = rules[1].charAt(0);

            String[] positions = occ.split("-");
            int pos1 = Integer.parseInt(positions[0]);
            int pos2 = Integer.parseInt(positions[1]);

            int nbOcc = 0;
            if(password.charAt(pos1 - 1) == letter){
                nbOcc++;
            }
            if(password.charAt(pos2 - 1) == letter){
                nbOcc++;
            }
            if(nbOcc == 1){
                nbValid++;
            }
        }
        System.out.println(nbValid);
    }
}
