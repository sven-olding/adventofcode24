package de.vwgis.adventofcode;

import java.util.ArrayList;
import java.util.List;

// https://adventofcode.com/2024/day/9
public class DiskFragmenter {
    private DiskFragmenter() {}

    public static int solve(String diskMap) {
        List<Integer> blocks = parseInput(diskMap);
        compact(blocks);
        return calculateChecksum(blocks);
    }

    private static List<Integer> parseInput(String diskMap) {
        List<Integer> blocks = new ArrayList<>();
        int fileId = 0;

        for (int i = 0; i < diskMap.length(); i += 2) {
            int fileLength = Character.getNumericValue(diskMap.charAt(i));
            int spaceLength =
                    (i + 1 < diskMap.length())
                            ? Character.getNumericValue(diskMap.charAt(i + 1))
                            : 0;

            for (int j = 0; j < fileLength; j++) {
                blocks.add(fileId);
            }

            for (int j = 0; j < spaceLength; j++) {
                blocks.add(-1);
            }

            fileId++;
        }

        return blocks;
    }

    private static void compact(List<Integer> blocks) {
        int n = blocks.size();
        int writeIndex = 0;

        // Compact blocks by shifting them left
        for (int readIndex = 0; readIndex < n; readIndex++) {
            if (blocks.get(readIndex) != -1) { // If it's a file block
                // Move the block to the leftmost free space
                blocks.set(writeIndex, blocks.get(readIndex));
                // Only increment writeIndex when a file block is moved
                if (writeIndex != readIndex) {
                    blocks.set(readIndex, -1); // Set the original spot to free space
                }
                writeIndex++;
            }
        }
    }

    private static int calculateChecksum(List<Integer> blocks) {
        int checksum = 0;
        for (int i = 0; i < blocks.size(); i++) {
            if (blocks.get(i) != -1) {
                checksum += i * blocks.get(i);
            }
        }
        return checksum;
    }
}
