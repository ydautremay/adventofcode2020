package dyve.aoc2020.day.day11;

import dyve.aoc2020.input.InputReader;

import java.util.List;

public class Part1 {

    Grid<Character> seats = new Grid<>();

    int nbRows;
    int nbCols;

    public static void main(String[] args)throws Exception{
        Part1 part1 = new Part1();
        part1.execute();
    }

    public void execute() throws Exception{
        List<String> entries = InputReader.readInput("11");
        nbRows = entries.size();
        nbCols = entries.get(0).length();

        for(int j = 0; j < entries.size(); j++){
            String line = entries.get(j);
            for(int i = 0; i < line.length(); i++){
                seats.set(line.charAt(i), i, j);
            }
        }
        seats.display();
        System.out.println("\n");

        Grid<Character> transfo = iterate();
        while (!seats.equals(transfo)){
            seats = transfo;
            seats.display();
            System.out.println("\n");
            transfo = iterate();
        }
        System.out.println(seats.count(c -> c == '#'));
    }



    private Grid<Character> iterate(){
        Grid<Character> transfo = new Grid<>();
        for(int j = 0; j < nbRows; j++) {
            for (int i = 0; i < nbCols; i++) {
                Character seat = seats.get(i, j);
                transfo.set(seat, i, j);
                if(seat == 'L' && isNowOccupied(i, j)){
                    transfo.set('#', i, j);
                }else if(seat == '#' && isNowFree(i, j)){
                    transfo.set('L', i, j);
                }
            }
        }
        return transfo;
    }

    private boolean isNowOccupied(int i, int j) {
        for(Direction dir : Direction.values()){
            Character c = adjacentSeat(i, j, dir);
            if(c != null && c == '#'){
                return false;
            }
        }
        return true;
    }

    private boolean isNowFree(int i, int j) {
        int countOccupied = 0;
        for(Direction dir : Direction.values()) {
            Character c = adjacentSeat(i, j, dir);
            if(c != null && c == '#'){
                countOccupied++;
            }
        }
        return countOccupied >= 4;
    }

    private Character adjacentSeat(int i, int j, Direction direction){
        return seats.get(direction.moveHorizontally(i), direction.moveVertically(j));
    }

}
