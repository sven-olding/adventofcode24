package de.vwgis.adventofcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class PlutonianPebblesTest {

    @Test
    void solvesExampleInput() {
        String result = PlutonianPebbles.solve("125 17", 6);

        assertEquals("2097446912 14168 4048 2 0 2 4 40 48 2024 40 48 80 96 2 8 6 7 6 0 3 2", result);
        assertEquals(22, result.split(" ").length);
    }

    @Test
    void solvesExampleInput2() {
        String result = PlutonianPebbles.solve("125 17", 25);

        assertEquals(55312, result.split(" ").length);
    }

    @Test
    void solvesPuzzleInput() {
        String result = PlutonianPebbles.solve("5 89749 6061 43 867 1965860 0 206250", 25);

        assertEquals(203609, result.split(" ").length);
    }
}
