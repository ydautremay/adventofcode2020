package dyve.aoc2020.day.day12;

import dyve.aoc2020.input.InputReader;

import java.util.List;

public class Part2 {

    Ship ship;

    public static void main(String[] args)throws Exception{
        Part2 part1 = new Part2();
        part1.execute();
    }

    private void execute() throws Exception{
        List<String> entries = InputReader.readInput("12");
        ship = new Ship(new Position(0, 0), Direction.E);
        Waypoint waypoint = new Waypoint(new Position(10, 1));

        for(String s : entries){
            Action a = Action.valueOf(s.substring(0, 1));
            int amount = Integer.parseInt(s.substring(1));
            System.out.printf("%s %d%n", a.name(), amount);
            if(a == Action.F) {
                ship.moveTo(waypoint.relativePosition, amount);
            }else{
                waypoint.move(a, amount);
            }
            System.out.printf("Ship : x= %d, y= %d%n", ship.position.x, ship.position.y);
            System.out.printf("Waypoint : x= %d, y= %d%n", waypoint.relativePosition.x, waypoint.relativePosition.y);
        }

        System.out.println(Math.abs(ship.position.x) + Math.abs(ship.position.y));
    }

}
