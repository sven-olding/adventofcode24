package de.vwgis.adventofcode;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// https://adventofcode.com/2024/day/11
public class PlutonianPebbles {
    private PlutonianPebbles() {
    }

    public static String solve(String input, int numberOfBlinks) {
        var stones = Arrays.stream(input.split(" "))
                .mapToLong(Long::parseLong)
                .boxed()
                .toList();

        for (int i = 0; i < numberOfBlinks; i++) {
            stones = stones.stream()
                    .flatMap(stone -> {
                        if (stone == 0) {
                            return Stream.of(1L);
                        } else if (String.valueOf(stone).length() % 2 == 0) {
                            String stoneStr = String.valueOf(stone);
                            int mid = stoneStr.length() / 2;
                            return Stream.of(
                                    Long.parseLong(stoneStr.substring(0, mid)),
                                    Long.parseLong(stoneStr.substring(mid)));
                        } else {
                            return Stream.of(stone * 2024);
                        }
                    })
                    .collect(Collectors.toList());
        }

        return stones.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(" "));
    }
}
