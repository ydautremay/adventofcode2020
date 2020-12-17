package dyve.aoc2020.day.day17;

import dyve.aoc2020.day.Part;
import dyve.aoc2020.input.InputReader;

import java.util.*;
import java.util.stream.Collectors;

public class Part1 extends Part {

    Map<Position, Cube> cubes = new HashMap<>();

    public static void main(String[] args)throws Exception{
        new Part1().subMain(17);
    }

    @Override
    public Object execute(InputReader inputReader){
        List<String> entries = inputReader.stream().collect(Collectors.toList());

        initCubes(entries);

        for(int nb = 0; nb < 6; nb++) {
            //Extend grid
            cubes = extendSpace();
            Map<Position, Cube> wip = copy(cubes);
            //Verify neighbours
            for (Cube cube : cubes.values()) {
                int neighbours = countNeighbours(cube);
                if(cube.active){
                    if(neighbours != 2 && neighbours != 3){
                        wip.put(cube.p, new Cube(cube.p, false));
                    }
                }else{
                    if(neighbours == 3){
                        wip.put(cube.p, new Cube(cube.p, true));
                    }
                }
            }
            cubes = wip;
        }
        long nbActive = 0;
        for(Cube cube : cubes.values()){
            if(cube.active)
                nbActive++;
        }
        return nbActive;
    }

    private int countNeighbours(Cube cube) {
        int neighbours = 0;
        for (long x = cube.p.x - 1; x <= cube.p.x + 1; x++) {
            for (long y = cube.p.y - 1; y <= cube.p.y + 1; y++) {
                for (long z = cube.p.z - 1; z <= cube.p.z + 1; z++) {
                    if(cube.p.x == x && cube.p.y == y && cube.p.z == z){
                        continue;
                    }
                    Position p = new Position(x, y, z);
                    Cube neighbour = cubes.get(p);
                    if(neighbour != null && neighbour.active){
                        neighbours++;
                    }
                }
            }
        }
        return neighbours;
    }

    private Map<Position, Cube> extendSpace() {
        Map<Position, Cube> result = new HashMap<>();
        for (Cube cube : cubes.values()) {
            for (long x = cube.p.x - 1; x <= cube.p.x + 1; x++) {
                for (long y = cube.p.y - 1; y <= cube.p.y + 1; y++) {
                    for (long z = cube.p.z - 1; z <= cube.p.z + 1; z++) {
                        Position p = new Position(x, y, z);
                        Cube c = cubes.getOrDefault(p, new Cube(p, false));
                        result.put(p, c);
                    }
                }
            }
        }
        return result;
    }

    private void initCubes(List<String> entries) {
        for (int i = 0; i < entries.size(); i++){
            String entry = entries.get(i);
            for(int j = 0; j < entry.length(); j++){
                char c = entry.charAt(j);
                Position p = new Position(j, i, 0);
                if(c == '#'){
                    cubes.put(p, new Cube(p, true));
                }else{
                    cubes.put(p, new Cube(p, false));
                }
            }
        }
        displayCubes();
    }

    private void displayCubes(){
        long minZ = cubes.keySet().stream().mapToLong(p -> p.z).min().orElse(Long.MIN_VALUE);
        long maxZ = cubes.keySet().stream().mapToLong(p -> p.z).max().orElse(Long.MAX_VALUE);
        long minX = cubes.keySet().stream().mapToLong(p -> p.x).min().orElse(Long.MIN_VALUE);
        long maxX = cubes.keySet().stream().mapToLong(p -> p.x).max().orElse(Long.MAX_VALUE);
        long minY = cubes.keySet().stream().mapToLong(p -> p.y).min().orElse(Long.MIN_VALUE);
        long maxY = cubes.keySet().stream().mapToLong(p -> p.y).max().orElse(Long.MAX_VALUE);
        for(long z = minZ; z <= maxZ; z++){
            System.out.println("z = " + z);
            System.out.println();
            for(long y = minY; y <= maxY; y++){
                for(long x = minX; x <= maxX; x++){
                    Position p = new Position(x, y, z);
                    Cube c = cubes.get(p);
                    if(c != null)
                        System.out.print(c);
                    else
                        System.out.print(".");
                }
                System.out.println();
            }
            System.out.println();
        }
        System.out.println("-----------------------------------------------");
    }

    private Map<Position, Cube> copy(Map<Position, Cube> original){
        Map<Position, Cube> copy = new HashMap<>();
        for(Position p : original.keySet()){
            copy.put(p, new Cube(p, original.get(p).active));
        }
        return copy;
    }
}
