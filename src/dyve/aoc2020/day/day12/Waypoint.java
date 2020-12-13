package dyve.aoc2020.day.day12;

public class Waypoint {

    Position relativePosition;

    public Waypoint(Position relativePosition) {
        this.relativePosition = relativePosition;
    }

    public void move(Action action, int amount){
        switch (action) {
            case N -> relativePosition.y += amount;
            case S -> relativePosition.y -= amount;
            case E -> relativePosition.x += amount;
            case W -> relativePosition.x -= amount;

            case L, R -> {
                int times = amount / 90;
                for(int i = 0; i < times; i++){
                    rotate(action);
                }
            }
        }
    }

    private void rotate(Action action) {
        switch (action) {
            case L -> {
                Position p = new Position(relativePosition.x, relativePosition.y);
                relativePosition.x = -p.y;
                relativePosition.y = p.x;
            }
            case R -> {
                Position p = new Position(relativePosition.x, relativePosition.y);
                relativePosition.x = p.y;
                relativePosition.y = -p.x;
            }
        }
    }
}
