package dyve.aoc2020.day.day11;

import java.util.LinkedHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Grid<E> {

    LinkedHashMap<Integer, LinkedHashMap<Integer, E>> grid = new LinkedHashMap<>();

    public E get(int i, int j){
        if(grid.get(j) == null){
            return null;
        }
        return grid.get(j).get(i);
    }

    public E getOrDefault(int i, int j, E defaultValue){
        if(grid.get(j) == null){
            return defaultValue;
        }
        return grid.get(j).getOrDefault(i, defaultValue);
    }

    public void set(E e, int i, int j){
        grid.computeIfAbsent(j, k -> new LinkedHashMap<>()).put(i, e);
    }

    public Grid<E> copy(){
        Grid<E> copy = new Grid<>();
        for(Integer i : grid.keySet()){
            LinkedHashMap<Integer, E> inner = grid.get(i);
            LinkedHashMap<Integer, E> innerCopy =  new LinkedHashMap<>(inner);
            copy.grid.put(i, innerCopy);
        }
        return copy;
    }

    public int count(Predicate<E> predicate){
        AtomicInteger count = new AtomicInteger(0);
        grid.forEach((j, line) -> line.forEach((i, e) -> {
            if(predicate.test(e)){
                count.getAndIncrement();
            }
        }));
        return count.get();
    }

    public Stream<E> stream(){
        Stream<E> stream = Stream.empty();
        for (LinkedHashMap<Integer, E> m : grid.values()){
            stream = Stream.concat(stream, m.values().stream());
        }
        return stream;
    }

    public boolean equals(Grid<E> other){
        return grid.equals(other.grid);
    }

    public void display(){
        int maxJ = grid.keySet().stream().mapToInt(i -> i).max().orElse(0);
        int minJ = grid.keySet().stream().mapToInt(i -> i).min().orElse(0);
        int maxI = 0;
        int minI = 0;
        for(int j : grid.keySet()){
            maxI = Integer.max(maxI, grid.get(j).keySet().stream().mapToInt(i -> i).max().orElse(0));
            minI = Integer.min(minI, grid.get(j).keySet().stream().mapToInt(i -> i).min().orElse(0));
        }
        for(int j = minJ; j <= maxJ; j++){
            for (int i = minI; i <= maxI; i++){
                E e = get(i, j);
                if(e == null){
                    System.out.print(" ");
                }else{
                    System.out.print(e.toString());
                }
            }
            System.out.println();
        }
    }
}

