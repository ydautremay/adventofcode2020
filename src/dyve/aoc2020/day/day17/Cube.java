package dyve.aoc2020.day.day17;

import java.util.Objects;

public class Cube {

    Position p;

    boolean active;

    public Cube(long x, long y, long z) {
        this.p = new Position(x, y, z);
    }

    public Cube(long x, long y, long z, long w) {
        this.p = new Position(x, y, z, w);
    }

    public Cube(Position p, boolean active) {
        this.p = p;
        this.active = active;
    }

    public String toString(){
//        return "[" + p + (active ? "#" : ".") + "]";
        return active ? "#" : ".";
    }
}
