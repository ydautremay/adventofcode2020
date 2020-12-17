package dyve.aoc2020.day.day17;

import dyve.aoc2020.day.Part;
import dyve.aoc2020.input.InputReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Part2 extends Part {

    Map<Position, Cube> cubes = new HashMap<>();

    public static void main(String[] args)throws Exception{
        new Part2().subMain(17);
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
                    for (long w = cube.p.w - 1; w <= cube.p.w + 1; w++) {
                        if (cube.p.x == x && cube.p.y == y && cube.p.z == z && cube.p.w == w) {
                            continue;
                        }
                        Position p = new Position(x, y, z, w);
                        Cube neighbour = cubes.get(p);
                        if (neighbour != null && neighbour.active) {
                            neighbours++;
                        }
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
                        for (long w = cube.p.w - 1; w <= cube.p.w + 1; w++) {
                            Position p = new Position(x, y, z, w);
                            Cube c = cubes.getOrDefault(p, new Cube(p, false));
                            result.put(p, c);
                        }
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
                Position p = new Position(j, i, 0, 0);
                if(c == '#'){
                    cubes.put(p, new Cube(p, true));
                }else{
                    cubes.put(p, new Cube(p, false));
                }
            }
        }
    }

    private Map<Position, Cube> copy(Map<Position, Cube> original){
        Map<Position, Cube> copy = new HashMap<>();
        for(Position p : original.keySet()){
            copy.put(p, new Cube(p, original.get(p).active));
        }
        return copy;
    }
}
