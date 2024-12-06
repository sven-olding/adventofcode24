package de.vwgis.adventofcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// https://adventofcode.com/2024/day/5
public class PrintQueue {
    private PrintQueue() {}

    private static final List<PageOrderingRule> pageOrderingRules = new ArrayList<>();
    private static final List<List<Integer>> pageNumbersForUpdate =
            new ArrayList<>(new ArrayList<>());

    public static int solve(String input) {
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
