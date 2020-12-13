package dyve.aoc2020.day.day7;

import dyve.aoc2020.input.InputReader;

import java.util.*;
import java.util.regex.Pattern;

public class Part2 {

    private Pattern begin = Pattern.compile("(\\D*) bag[s]? contain (\\d) (\\D*) bags?\\.?");
    private Pattern middle = Pattern.compile("(\\d) (\\D*) bags?\\.?");
    private Pattern noOther = Pattern.compile("(\\D*) bag[s]? contain no other bags\\.");

    private Map<String, Bag> bags = new HashMap<>();

    private Bag shinyGold;

    public static void main(String[] args)throws Exception{
        Part2 part1 = new Part2();
        part1.execute();
    }

    public void execute() throws Exception{
        List<String> entries = InputReader.readInput("7");

        initBags(entries);

        System.out.println(countBags(shinyGold) - 1);
    }

    private void initBags(List<String> entries){
        shinyGold = new Bag("shiny gold");
        bags.put(shinyGold.color, shinyGold);

        for(String entry : entries){
            var noOtherMatcher = noOther.matcher(entry);
            if(noOtherMatcher.matches()){
                bags.computeIfAbsent(noOtherMatcher.group(1), Bag::new);
            }else{
                String[] parts = entry.split(", ");
                var beginMatcher = begin.matcher(parts[0]);
                if(!beginMatcher.matches()){throw new AssertionError();}
                Bag bag = bags.computeIfAbsent(beginMatcher.group(1), Bag::new);
                Bag content = bags.computeIfAbsent(beginMatcher.group(3), Bag::new);
                bag.bags.put(content, Integer.parseInt(beginMatcher.group(2)));
                for(int i = 1; i < parts.length; i++){
                    var middleMatcher = middle.matcher(parts[i]);
                    if(!middleMatcher.matches()){throw new AssertionError();}
                    Bag next = bags.computeIfAbsent(middleMatcher.group(2), Bag::new);
                    bag.bags.put(next, Integer.parseInt(middleMatcher.group(1)));
                }
            }
        }
    }

    private int countBags(Bag bag){
        int count = 1;
        for(Map.Entry<Bag, Integer> entry : bag.bags.entrySet()){
            count += entry.getValue() * countBags(entry.getKey());
        }
        return count;
    }
}
