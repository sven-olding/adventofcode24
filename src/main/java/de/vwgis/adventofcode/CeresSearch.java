package de.vwgis.adventofcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CeresSearch {
    private CeresSearch() {}

    private static final char[] XMAS = {'X', 'M', 'A', 'S'};

    public static int solve(String input) {
        char[][] matrix = Util.getCharMatrix(input);

        int result = 0;
        int rows = matrix.length;
        int cols = matrix[0].length;

        int[][] directions = {
            {0, 1}, {1, 0}, {1, 1}, {1, -1},
            {0, -1}, {-1, 0}, {-1, -1}, {-1, 1}
        };

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                for (int[] direction : directions) {
                    if (containsXmas(matrix, row, col, direction[0], direction[1])) {
                        result++;
                    }
                }
            }
        }

        return result;
    }

    private static boolean containsXmas(
            char[][] matrix, int rowIdx, int colIdx, int rowDirection, int colDirection) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        for (int i = 0; i < XMAS.length; i++) {
            int r = rowIdx + i * rowDirection;
            int c = colIdx + i * colDirection;
            if (r < 0 || r >= rows || c < 0 || c >= cols || matrix[r][c] != XMAS[i]) {
                return false;
            }
        }

        return true;
    }

}
