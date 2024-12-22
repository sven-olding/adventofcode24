package de.vwgis.adventofcode;

public class Util {
    private Util() {
    }

    public static char[][] getCharMatrix(String input) {
        return input.lines()
                .map(line -> line.toCharArray())
                .toArray(char[][]::new);
    }
}
