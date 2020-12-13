package dyve.aoc2020.day.day4;

import dyve.aoc2020.input.InputReader;

import java.util.*;

public class Part1 {

    public static void main(String[] args)throws Exception{
        Part1 part1 = new Part1();
        part1.execute();
    }

    public void execute() throws Exception{
        List<String> entries = InputReader.readInput("4");
        Set<Passport> passports = new HashSet<>();

        Map<String, String> currentData = new HashMap<>();
        for(String entry : entries){
            if("".equals(entry)){
                System.out.println(currentData);
                passports.add(Passport.fromMap(currentData));
                currentData.clear();
                continue;
            }
            String[] fields = entry.split(" ");
            for(String field : fields){
                String[] map = field.split(":");
                currentData.put(map[0], map[1]);
            }
        }
        if(!currentData.isEmpty()){
            passports.add(Passport.fromMap(currentData));
        }

        System.out.println(passports.stream().filter(Passport::isValid).count());
    }
}
