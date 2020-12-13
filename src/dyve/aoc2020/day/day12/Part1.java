package dyve.aoc2020.day.day12;

import dyve.aoc2020.input.InputReader;

import java.util.List;

public class Part1 {

    Ship ship;

    public static void main(String[] args)throws Exception{
        Part1 part1 = new Part1();
        part1.execute();
    }

    private void execute() throws Exception{
        List<String> entries = InputReader.readInput("12");
        ship = new Ship(new Position(0, 0), Direction.E);

        for(String s : entries){
            Action a = Action.valueOf(s.substring(0, 1));
            int amount = Integer.parseInt(s.substring(1));
            System.out.printf("%s %d%n", a.name(), amount);
            ship.move(a, amount);
            System.out.printf("x= %d, y= %d, facing %s%n", ship.position.x, ship.position.y, ship.direction.name());
        }

        System.out.println(Math.abs(ship.position.x) + Math.abs(ship.position.y));
    }

}
