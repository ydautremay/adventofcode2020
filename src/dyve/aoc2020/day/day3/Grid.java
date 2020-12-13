package dyve.aoc2020.day.day3;

import java.util.LinkedHashMap;

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

}

