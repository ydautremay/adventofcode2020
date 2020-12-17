package dyve.aoc2020.day.day17;

import dyve.aoc2020.day.Part;
import dyve.aoc2020.input.InputReader;

import java.util.*;
import java.util.stream.Collectors;

public class Part2 extends Part {

    Set<Position> actives = new HashSet<>();
    long minX, maxX, minY, maxY, minZ, maxZ, minW, maxW;

    public static void main(String[] args)throws Exception{
        new Part2().subMain(17);
    }

    @Override
    public Object execute(InputReader inputReader){
        List<String> entries = inputReader.stream().collect(Collectors.toList());

        initCubes(entries);

        for(int nb = 0; nb < 6; nb++) {
            minX --;maxX++;
            minY--;maxY++;
            minZ--;maxZ++;
            minW--;maxW++;
            Position p = new Position(0, 0, 0, 0);
            Set<Position> wip = new HashSet<>(actives);
            for (long x = minX; x <= maxX; x++) {
                for (long y = minY; y <= maxY; y++) {
                    for (long z = minZ; z <= maxZ; z++) {
                        for (long w = minW; w <= maxW; w++) {
                            p.replace(x, y, z, w);
                            int neighbours = countNeighbours(p);
                            if(actives.contains(p)){
                                if(neighbours != 2 && neighbours != 3){
                                    wip.remove(p);
                                }
                            }else{
                                if(neighbours == 3){
                                    wip.add(new Position(p));
                                }
                            }
                        }
                    }
                }
            }
            actives = wip;
        }
        return actives.size();
    }

    private int countNeighbours(Position p) {
        int neighbours = 0;
        Position test = new Position(0, 0, 0, 0);
        for (long x = p.x - 1; x <= p.x + 1; x++) {
            for (long y = p.y - 1; y <= p.y + 1; y++) {
                for (long z = p.z - 1; z <= p.z + 1; z++) {
                    for (long w = p.w - 1; w <= p.w + 1; w++) {
                        if (p.x == x && p.y == y && p.z == z && p.w == w) {
                            continue;
                        }
                        test.replace(x, y, z, w);
                        if (actives.contains(test)) {
                            neighbours++;
                        }
                    }
                }
            }
        }
        return neighbours;
    }

    private void initCubes(List<String> entries) {
        for (int i = 0; i < entries.size(); i++){
            String entry = entries.get(i);
            for(int j = 0; j < entry.length(); j++){
                char c = entry.charAt(j);
                Position p = new Position(j, i, 0, 0);
                if(c == '#'){
                    actives.add(p);
                }
            }
        }
        minX = 0L;
        maxX = entries.size();
        minY = 0L;
        maxY = entries.get(0).length();
        minZ = 0L;
        maxZ = 0L;
        minW = 0L;
        maxW = 0L;
    }

}
