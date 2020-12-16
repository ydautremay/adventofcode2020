package dyve.aoc2020.day.day16;

import dyve.aoc2020.day.Part;
import dyve.aoc2020.input.InputReader;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Part2 extends Part {

    Pattern pattern = Pattern.compile("(.+): (\\d+)-(\\d+) or (\\d+)-(\\d+)");

    List<Rule> rules = new ArrayList<>();

    Map<Rule, Set<Integer>> rulePositions = new HashMap<>();

    Ticket myTicket;

    public static void main(String[] args)throws Exception{
        new Part2().subMain(16);
    }

    @Override
    public Object execute(InputReader inputReader){
        List<String> entries = inputReader.stream().collect(Collectors.toList());

        List<Ticket> validTickets = findValidTickets(entries);

        for(Rule rule : rules) {
            column: for (int i = 0; i < rules.size(); i++) {
                for (Ticket ticket : validTickets) {
                    int value = ticket.values.get(i);
                    if(!rule.verify(value)){
                        continue column;
                    }
                }
                rulePositions.computeIfAbsent(rule, k -> new HashSet<>()).add(i);
            }
        }

        while(rulePositions.values().stream().anyMatch(set -> set.size() > 1)){
            List<Integer> toRemove = rulePositions.values().stream().filter(set -> set.size() == 1).map(set -> set.iterator().next()).collect(Collectors.toList());
            for(Set<Integer> positions : rulePositions.values()){
                if(positions.size() > 1) {
                    positions.removeAll(toRemove);
                }
            }
        }
        System.out.println(rulePositions);

        return rulePositions.entrySet().stream().filter(entry -> entry.getKey().name.startsWith("departure")).map(entry -> entry.getValue().iterator().next()).mapToLong(col -> myTicket.values.get(col)).reduce(1, (i, j) -> i * j);

    }

    private List<Ticket> findValidTickets(List<String> entries) {
        List<Ticket> validTickets = new ArrayList<>();

        boolean yourTicket = false;
        boolean nearby = false;
        for(String entry : entries){
            if(!yourTicket){
                if(entry.equals("")){
                    continue;
                }
                if(entry.equals("your ticket:")){
                    yourTicket = true;
                }else{
                    Matcher m = pattern.matcher(entry);
                    m.matches();
                    rules.add(new Rule(m.group(1), Integer.parseInt(m.group(2)), Integer.parseInt(m.group(3)), Integer.parseInt(m.group(4)), Integer.parseInt(m.group(5))));
                }
                continue;
            }
            if(yourTicket && !nearby){
                if(entry.equals("")){
                    continue;
                }
                if(entry.equals("nearby tickets:")){
                    nearby = true;
                }else{
                    myTicket = new Ticket(entry);
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
            if(Arrays.stream(entry.split(",")).mapToInt(Integer::parseInt).filter(this::failsAllRule).count() == 0){
                validTickets.add(new Ticket(entry));
            }
        }
        return validTickets;
    }

    private boolean failsAllRule(int input){
        for (Rule r : rules){
            if(r.verify(input)){
                return false;
            }
        }
        return true;
    }
}
