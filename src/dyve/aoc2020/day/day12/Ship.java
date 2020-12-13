package dyve.aoc2020.day.day12;

public class Ship {

    Position position;

    Direction direction;

    public Ship(Position position, Direction direction) {
        this.position = position;
        this.direction = direction;
    }

    public void move(Action action, int amount){
        switch (action){
            case N -> position.y += amount;
            case S -> position.y -= amount;
            case E -> position.x += amount;
            case W -> position.x -= amount;
            case F -> move(Action.valueOf(direction.name()), amount);

            case L, R -> {
                int times = amount / 90;
                for(int i = 0; i < times; i++){
                    rotate(action);
                }
            }
        }
    }

    public void moveTo(Position p, int amount){
        position.x += amount * p.x;
        position.y += amount * p.y;
    }

    private void rotate(Action action){
        switch (action){
            case L -> {
                switch (direction){
                    case N -> direction = Direction.W;
                    case W -> direction = Direction.S;
                    case S -> direction = Direction.E;
                    case E -> direction = Direction.N;
                }
            }
            case R -> {
                switch (direction){
                    case N -> direction = Direction.E;
                    case E -> direction = Direction.S;
                    case S -> direction = Direction.W;
                    case W -> direction = Direction.N;
                }
            }
        }
    }
}
