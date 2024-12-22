package de.vwgis.adventofcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// https://adventofcode.com/2024/day/10
public class HoofIt {
    private static final int[] DX = { -1, 1, 0, 0 }; // Up, Down, Left, Right
    private static final int[] DY = { 0, 0, -1, 1 }; // Up, Down, Left, Right

    public static int solve(String input) {
        char[][] grid = Util.getCharMatrix(input.replace(" ", ""));
        List<Point> trailheads = findTrailheads(grid);

        int totalScore = 0;
        for (Point trailhead : trailheads) {
            totalScore += calculateTrailheadScore(grid, trailhead);
        }

        return totalScore;
    }

    private static List<Point> findTrailheads(char[][] grid) {
        List<Point> trailheads = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '0') {
                    trailheads.add(new Point(i, j));
                }
            }
        }
        return trailheads;
    }

    private static int calculateTrailheadScore(char[][] grid, Point start) {
        Set<Point> reachableNines = new HashSet<>();
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        dfs(grid, start, visited, reachableNines);
        return reachableNines.size();
    }

    private static void dfs(char[][] grid, Point current, boolean[][] visited, Set<Point> reachableNines) {
        visited[current.x][current.y] = true;
        int currentHeight = grid[current.x][current.y] - '0';

        if (currentHeight == 9) {
            reachableNines.add(new Point(current.x, current.y));
            return;
        }

        for (int i = 0; i < 4; i++) {
            int newX = current.x + DX[i];
            int newY = current.y + DY[i];

            if (isValidMove(grid, newX, newY, visited) &&
                    grid[newX][newY] - '0' == currentHeight + 1) {
                dfs(grid, new Point(newX, newY), visited, reachableNines);
            }
        }
    }

    private static boolean isValidMove(char[][] grid, int x, int y, boolean[][] visited) {
        return x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && !visited[x][y];
    }

    private record Point(int x, int y) {
    }
}
