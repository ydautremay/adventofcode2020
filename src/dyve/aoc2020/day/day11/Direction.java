package dyve.aoc2020.day.day11;

import java.util.function.UnaryOperator;

enum Direction {
    UP(i -> i, j -> j - 1),
    UPRIGHT(i -> i + 1, j -> j - 1),
    RIGHT(i -> i + 1, j -> j),
    DOWNRIGHT(i -> i + 1, j -> j + 1),
    DOWN(i -> i, j -> j + 1),
    DOWNLEFT(i -> i - 1, j -> j + 1),
    LEFT(i -> i - 1, j -> j),
    UPLEFT(i -> i - 1, j -> j - 1);

    private final UnaryOperator<Integer> horizontalOperation;

    private final UnaryOperator<Integer> verticalOperation;

    Direction(UnaryOperator<Integer> horizontalOperation, UnaryOperator<Integer> verticalOperation) {
        this.horizontalOperation = horizontalOperation;
        this.verticalOperation = verticalOperation;
    }

    public int moveHorizontally(int i){
        return horizontalOperation.apply(i);
    }

    public int moveVertically(int j){
        return verticalOperation.apply(j);
    }
}
