package dyve.aoc2020.day.day14;

import dyve.aoc2020.day.Part;
import dyve.aoc2020.input.InputReader;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class Part1 extends Part {

    Map<Integer, Long> memory;

    public static void main(String[] args)throws Exception{
        new Part1().subMain(14);
    }

    @Override
    public Object execute(InputReader inputReader){
        memory = new HashMap<>();
        AtomicReference<String> mask = new AtomicReference<>("");
        inputReader.stream().forEach(line -> {
            String[] parts = line.split(" = ");
            if(parts[0].equals("mask")){
                mask.getAndSet(parts[1]);
                System.out.println(mask);
            }else{
                int mem = Integer.parseInt(parts[0].substring(parts[0].indexOf('[') + 1, parts[0].indexOf(']')));
                String binaryString = Long.toBinaryString(Long.parseLong(parts[1]));
                while(binaryString.length() < mask.get().length()){
                    binaryString = "0"+binaryString;
                }
                System.out.println(binaryString);
                for(int i = binaryString.length() - 1; i >= 0; i--){
                    if(mask.get().charAt(i) != 'X'){
                        binaryString  = binaryString.substring(0, i) + mask.get().charAt(i) + binaryString.substring(i + 1);
                    }
                    memory.put(mem, Long.parseLong(binaryString, 2));
                }
                System.out.println(binaryString);
            }
            System.out.println("-------------------");
        });
        return memory.values().stream().mapToLong(l -> l).sum();
    }
}
