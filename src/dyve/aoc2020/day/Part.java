package dyve.aoc2020.day;

import dyve.aoc2020.input.InputReader;

public abstract class Part {

    public void subMain(int day) throws Exception {
        try(InputReader reader = InputReader.readInput(day)){
            System.out.println(execute(reader).toString());
        }
    }

    protected abstract Object execute(InputReader reader) throws Exception;
}
