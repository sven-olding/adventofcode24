package de.vwgis.adventofcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

// https://adventofcode.com/2024/day/5
public class PrintQueue {
    private PrintQueue() {}

    private static final List<PageOrderingRule> pageOrderingRules = new ArrayList<>();
    private static final List<List<Integer>> pageNumbersForUpdate =
            new ArrayList<>(new ArrayList<>());

    public static int solve(String input) {
        pageNumbersForUpdate.clear();
        pageOrderingRules.clear();

        parseInput(input);

        int result = 0;
        for (List<Integer> update : pageNumbersForUpdate) {
            boolean isUpdateCorrect = true;
            for (PageOrderingRule pageOrderingRule : pageOrderingRules) {
                if (!isCorrectlyOrdered(update, pageOrderingRule)) {
                    isUpdateCorrect = false;
                    break;
                }
            }
            if (isUpdateCorrect) {
                result += update.get(update.size() / 2);
            }
        }

        return result;
    }

    public static int solveSecondPart(String input) {
        pageOrderingRules.clear();
        pageNumbersForUpdate.clear();

        parseInput(input);

        var newlyOrderedCorrect = new ArrayList<ArrayList<Integer>>();

        for (List<Integer> update : pageNumbersForUpdate) {
            for (PageOrderingRule pageOrderingRule : pageOrderingRules) {
                if (!isCorrectlyOrdered(update, pageOrderingRule)) {
                    newlyOrderedCorrect.add(order(update, pageOrderingRules));
                    break;
                }
            }
        }

        int result = 0;

        for (ArrayList<Integer> integers : newlyOrderedCorrect) {
            result += integers.get(integers.size() / 2);
        }

        return result;
    }

    private static ArrayList<Integer> order(
            List<Integer> update, List<PageOrderingRule> pageOrderingRules) {
        // Step 1: Build the graph and in-degree map for the update pages
        var graph = new HashMap<Integer, List<Integer>>();
        var inDegree = new HashMap<Integer, Integer>();

        // Initialize the graph and in-degree map for the pages in the update
        for (int page : update) {
            graph.put(page, new ArrayList<>());
            inDegree.put(page, 0);
        }

        // Add edges based on the rules that apply to this update
        for (PageOrderingRule rule : pageOrderingRules) {
            int x = rule.x();
            int y = rule.y();

            // Only consider rules where both x and y are in the current update
            if (update.contains(x) && update.contains(y)) {
                graph.get(x).add(y);
                inDegree.put(y, inDegree.getOrDefault(y, 0) + 1);
            }
        }

        // Step 2: Perform topological sorting using Kahn's algorithm
        var sortedOrder = new ArrayList<Integer>();
        var queue = new ArrayDeque<Integer>();

        // Add all pages with in-degree 0 to the queue
        for (int page : inDegree.keySet()) {
            if (inDegree.get(page) == 0) {
                queue.add(page);
            }
        }

        while (!queue.isEmpty()) {
            int current = queue.poll();
            sortedOrder.add(current);

            // Decrease in-degree for neighbors and add them to the queue if in-degree becomes 0
            for (int neighbor : graph.get(current)) {
                inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                if (inDegree.get(neighbor) == 0) {
                    queue.add(neighbor);
                }
            }
        }

        // Step 3: Return the sorted order
        return sortedOrder;
    }

    private static boolean isCorrectlyOrdered(
            List<Integer> update, PageOrderingRule pageOrderingRule) {
        int mustComeFirst = pageOrderingRule.x();
        int mayComeAfter = pageOrderingRule.y();
        if (!update.contains(mustComeFirst) || !update.contains(mayComeAfter)) {
            return true;
        }
        return update.indexOf(mustComeFirst) <= update.indexOf(mayComeAfter);
    }

    private static void parseInput(String input) {
        try (Scanner scanner = new Scanner(input)) {
            boolean isSecondSection = false;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!isSecondSection) {
                    if (line.isEmpty()) {
                        isSecondSection = true;
                        continue;
                    }
                    String[] split = line.split("\\|");
                    pageOrderingRules.add(
                            new PageOrderingRule(
                                    Integer.parseInt(split[0]), Integer.parseInt(split[1])));
                } else {
                    var pageNumbers = new ArrayList<Integer>();
                    for (String s : line.split(",")) {
                        pageNumbers.add(Integer.parseInt(s));
                    }
                    pageNumbersForUpdate.add(pageNumbers);
                }
            }
        }
    }

    private record PageOrderingRule(int x, int y) {}
}
