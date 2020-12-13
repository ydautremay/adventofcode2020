package dyve.aoc2020.input;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputReader {

    public static List<String> readInput(String suffix) throws IOException, URISyntaxException {
        try (Scanner scanner = new Scanner(Paths.get(InputReader.class.getClassLoader().getResource("resources/day"+suffix).toURI()))){
            scanner.useDelimiter("\\A");
            List<String> output = new ArrayList<>();
            while(scanner.hasNextLine()){
                output.add(scanner.nextLine());
            }
            return output;
        }
    }
}
