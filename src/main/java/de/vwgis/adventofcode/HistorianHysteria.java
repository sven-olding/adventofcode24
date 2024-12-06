package de.vwgis.adventofcode;

import java.util.List;
import java.util.stream.IntStream;

// https://adventofcode.com/2024/day/1
public class HistorianHysteria {
    private HistorianHysteria() {}

    public static int solve(List<Integer> left, List<Integer> right) {
        left.sort(Integer::compareTo);
        right.sort(Integer::compareTo);

        return IntStream.range(0, left.size()).map(i -> Math.abs(left.get(i) - right.get(i))).sum();
    }
}
