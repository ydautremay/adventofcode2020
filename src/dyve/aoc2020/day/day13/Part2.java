package dyve.aoc2020.day.day13;

import dyve.aoc2020.input.InputReader;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.LongStream;

public class Part2 {

    public static void main(String[] args)throws Exception{
        Part2 part1 = new Part2();
        part1.execute();
    }


    /**
     * 		29	41	577	13	17	19	23	601	37
     *
     * p-29	D	.	.	.	.	.	.	.	.
     * .
     * .
     * p-10	.	D	.	.	.	.	.	.	.
     * .
     * .
     * p	D	.	D	D	.	D	D	.	.
     * .
     * .
     * p+13	.	.	.	D	.	.	.	.	.
     * p+14	.	.	.	.	D	.	.	.	.
     * .
     * .
     * p+19	.	.	.	.	.	D	.	.	.
     * .
     * .
     * p+23	.	.	.	.	.	.	D	.	.
     * .
     * .
     * p+31	.	.	.	.	.	.	.	D	.
     * .
     * .
     * p+68	.	.	.	.	.	.	.	.	D
     */
    public void execute() throws Exception {
        System.out.println(LongStream.iterate(1, p -> p + 1).map(p -> 95_060_173 * p).filter(p ->
                (p + 31) % 601 == 0 && (p - 10) % 41 == 0 && (p + 14) % 17 == 0 && (p + 68) % 37 == 0
        ).findFirst().getAsLong() - 29);
    }
}
