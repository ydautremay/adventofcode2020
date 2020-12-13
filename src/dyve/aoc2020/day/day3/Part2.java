package dyve.aoc2020.day.day3;

import dyve.aoc2020.input.InputReader;

import java.util.List;

public class Part2 {

    public static void main(String[] args)throws Exception{
        Part2 part1 = new Part2();
        part1.execute();
    }

    private void execute() throws Exception{
        List<String> entries = InputReader.readInput("3");
        long count = downTheSlope(entries, 1, 1);
        count *= downTheSlope(entries, 3, 1);
        count *= downTheSlope(entries, 5, 1);
        count *= downTheSlope(entries, 7, 1);
        count *= downTheSlope(entries, 1, 2);
        System.out.println(count);
    }

    private long downTheSlope(List<String> entries, int dx, int dy){
        Grid<Character> map = new Grid<>();
        int copies = 0;
        copyMap(entries, map, copies);
        copies++;
        int x = 0, y = 0;
        long count = 0;
        do{
            x += dx;
            y += dy;
            if(x >= copies * entries.get(0).length()){
                copyMap(entries, map, copies);
                copies++;
            }
            if(map.get(x, y) == '#'){
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
