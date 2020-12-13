package dyve.aoc2020.day.day3;

import dyve.aoc2020.input.InputReader;

import java.util.List;

public class Part1 {

    public static void main(String[] args)throws Exception{
        Part1 part1 = new Part1();
        part1.execute();
    }

    private void execute() throws Exception{
        List<String> entries = InputReader.readInput("3");
        Grid<Character> map = new Grid<>();
        int copies = 0;
        copyMap(entries, map, copies);
        copies++;
        int count = downTheSlope(map, entries, copies, 3, 1);
        System.out.println(count);
    }

    private int downTheSlope(Grid<Character> map, List<String> entries, int copies, int dx, int dy){
        int x = 0, y = 0;
        int count = 0;
        do{
            x += dx;
            y += dy;
            if(x >= copies * entries.get(0).length()){
                copyMap(entries, map, copies);
                copies++;
            }
            if(map.get(x, y) == '#'){
                System.out.printf("Tree in %d, %d !%n", x, y);
                count++;
            }
        }while(y + dy < entries.size());
        return count;
    }

    private void copyMap(List<String> entries, Grid<Character> map, int n) {
        for(int j = 0; j < entries.size(); j++){
            String line = entries.get(j);
            for(int i = 0; i < line.length(); i++){
                map.set(line.charAt(i), n * line.length() + i, j);
            }
        }
    }
}
