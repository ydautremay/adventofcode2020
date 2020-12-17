package dyve.aoc2020.day.day17;

import java.util.Objects;

public class Position {

    long x;

    long y;

    long z;

    long w;

    public Position(long x, long y, long z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Position(long x, long y, long z, long w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Position(Position p){
        this.x = p.x;
        this.y = p.y;
        this.z = p.z;
        this.w = p.w;
    }

    public void replace(long x, long y, long z, long w){
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public void replace(long x, long y, long z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y && z == position.z && w == position.w;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z, w);
    }

    @Override
    public String toString() {
        return String.format("(%d, %d, %d, %d)", x, y, z, w);
    }
}
