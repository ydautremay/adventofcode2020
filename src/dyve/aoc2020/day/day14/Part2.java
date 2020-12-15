package dyve.aoc2020.day.day14;

import dyve.aoc2020.day.Part;
import dyve.aoc2020.input.InputReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class Part2 extends Part {

    Map<Long, Long> memory;

    public static void main(String[] args)throws Exception{
        new Part2().subMain(14);
    }

    @Override
    public Object execute(InputReader inputReader){
        List<String> lines = inputReader.stream().collect(Collectors.toList());
        memory = new HashMap<>();
        String mask = "";
        for(String line : lines){
            String[] parts = line.split(" = ");
            if(parts[0].equals("mask")){
                mask = parts[1];
            }else{
                long mem = Long.parseLong(parts[0].substring(parts[0].indexOf('[') + 1, parts[0].indexOf(']')));
                long value = Long.parseLong(parts[1]);
                List<String> mems = floatMask(mask, Long.toBinaryString(mem));
                for(String memBin : mems){
                    memory.put(mask(mask, memBin), value);
                }
            }
        }
        return memory.values().stream().mapToLong(l -> l).sum();
    }

    private List<String> floatMask(String mask, String toFloat){
        while(toFloat.length() < mask.length()){
            toFloat = "0"+toFloat;
        }
        List<String> result = new ArrayList<>();
        result.add(toFloat);
        for(int i = 0; i < mask.length(); i++){
            char c = mask.charAt(i);
            if(c == 'X'){
                List<String> subfloats = new ArrayList<>();
                for(String subFloat : result){
                    subfloats.add(subFloat.substring(0, i) + "0" + subFloat.substring(i + 1));
                    subfloats.add(subFloat.substring(0, i) + "1" + subFloat.substring(i + 1));
                }
                result = subfloats;
            }
        }
        return result;
    }

    private long mask(String mask, String binaryString){
        while(binaryString.length() < mask.length()){
            binaryString = "0"+binaryString;
        }
        for(int i = 0; i < binaryString.length(); i++){
            if(mask.charAt(i) == '1') {
                binaryString = binaryString.substring(0, i) + mask.charAt(i) + binaryString.substring(i + 1);
            }
        }
        return Long.parseLong(binaryString, 2);
    }
}
