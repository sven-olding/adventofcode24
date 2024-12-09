package de.vwgis.adventofcode;

import javax.swing.text.Position;

// https://adventofcode.com/2024/day/6
public class GuardGallivant {
    public static int solve(String input) {
        char[][] board = Util.getCharMatrix(input);

        Position guardPosition = getGuardPosition(board);
        if (guardPosition.x == -1 || guardPosition.y == -1) return 0;

        return getPositionsVisited(board);
    }

    private static Position getGuardPosition(char[][] board) {
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[x].length; y++) {
                if (board[x][y] == '^' || board[x][y] == '>' || board[x][y] == 'v') {
                    return new Position(x, y);
                }
            }
        }
        return new Position(-1, -1);
    }

    private static int getPositionsVisited(char[][] board) {
        int result = 0;
        for (char[] row : board) {
            for (char c : row) {
                if (c == 'X') {
                    result++;
                }
            }
        }
        return result;
    }

    record Position(int x, int y) {}
}
