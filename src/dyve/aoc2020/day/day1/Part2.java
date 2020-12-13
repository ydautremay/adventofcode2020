package dyve.aoc2020.day.day1;

import dyve.aoc2020.day.Part;
import dyve.aoc2020.input.InputReader;

import java.util.List;
import java.util.stream.Collectors;

public class Part2 extends Part {

    public static void main(String[] args)throws Exception{
        new Part2().subMain(1);
    }

    public Object execute(InputReader reader) throws Exception{
        List<String> entries = reader.stream().collect(Collectors.toList());
        List<Integer> values = entries.stream().map(Integer::parseInt).collect(Collectors.toList());

        for (int i = 0; i < values.size(); i++){
            for(int j = 0; j < values.size(); j++){
                if(i == j){
                    continue;
                }
                int sum = values.get(i) + values.get(j);
                int diff = 2020 - sum;

                if(values.contains(diff)){
                    return values.get(i) * values.get(j) * diff;
                }
            }
        }
        return "";
    }
}
