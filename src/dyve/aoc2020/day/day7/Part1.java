package dyve.aoc2020.day.day7;

import dyve.aoc2020.input.InputReader;

import java.util.*;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Part1 {

    private Pattern begin = Pattern.compile("(\\D*) bag[s]? contain (\\d) (\\D*) bags?\\.?");
    private Pattern middle = Pattern.compile("(\\d) (\\D*) bags?\\.?");
    private Pattern noOther = Pattern.compile("(\\D*) bag[s]? contain no other bags\\.");

    private Map<String, Bag> bags = new HashMap<>();

    private Set<Bag> containsShinyGold = new HashSet<>();

    private Bag shinyGold;

    public static void main(String[] args)throws Exception{
        Part1 part1 = new Part1();
        part1.execute();
    }

    public void execute() throws Exception{
        List<String> entries = InputReader.readInput("7");

        initBags(entries);
        bags.values().forEach(b -> System.out.println(b.toLongString()));

        System.out.println();
        System.out.println();

        bags.values().forEach(b -> {
            if (recursiveContainsShinyGold(b)) {
                containsShinyGold.add(b);
                System.out.println();
            }
        });
        System.out.println(containsShinyGold.size());
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

    private boolean recursiveContainsShinyGold(Bag bag){
        if(bag.bags.containsKey(shinyGold)){
            System.out.printf("shiny gold <- %s", bag);
            return true;
        }
        if(containsShinyGold.contains(bag)){
            System.out.printf("shiny gold <- ... <- %s", bag);
            return true;
        }
        for(Bag contenant : bag.bags.keySet()){
            if(recursiveContainsShinyGold(contenant)){
                System.out.printf(" <- %s", bag);
                return true;
            }
        }
        return false;
    }
}
