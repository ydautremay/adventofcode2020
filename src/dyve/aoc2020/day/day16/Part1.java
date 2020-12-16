package dyve.aoc2020.day.day16;

import dyve.aoc2020.day.Part;
import dyve.aoc2020.input.InputReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Part1 extends Part {

    Pattern pattern = Pattern.compile(".+: (\\d+)-(\\d+) or (\\d+)-(\\d+)");

    List<Rule> rules = new ArrayList<>();

    public static void main(String[] args)throws Exception{
        new Part1().subMain(16);
    }

    @Override
    public Object execute(InputReader inputReader){
        List<String> entries = inputReader.stream().collect(Collectors.toList());

        boolean yourTicket = false;
        boolean nearby = false;
        int sum = 0;
        for(String entry : entries){
            if(!yourTicket){
                if(entry.equals("") || entry.equals("your ticket:")){
                    yourTicket = true;
                }else {
                    Matcher m = pattern.matcher(entry);
                    m.matches();
                    rules.add(new Rule(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2)), Integer.parseInt(m.group(3)), Integer.parseInt(m.group(4))));
                }
                continue;
            }
            if(!nearby){
                if(entry.equals("nearby tickets:")){
                    nearby = true;
                }
                continue;
            }
            //nearby tickets
            System.out.println(entry);
            sum += Arrays.stream(entry.split(",")).mapToInt(Integer::parseInt).filter(this::failsAllRule).sum();
        }
        return sum;
    }

    private boolean failsAllRule(int input){
        for (Rule r : rules){
            if(r.verify(input)){
                return false;
            }
        }
        System.out.printf("%d fails all rules%n", input);
        return true;
    }
}
