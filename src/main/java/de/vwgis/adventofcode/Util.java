package de.vwgis.adventofcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Util {
    private Util() {}

    public static char[][] getCharMatrix(String input) {
        List<char[]> matrix = new ArrayList<>();
        try (Scanner scanner = new Scanner(input)) {
            while (scanner.hasNextLine()) {
                matrix.add(scanner.nextLine().toCharArray());
            }
        }
        return matrix.toArray(new char[0][]);
    }
}
