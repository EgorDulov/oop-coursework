package service;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InputParserTest {

    @Test
    void parsesIntegersWithDifferentSeparators() {
        assertEquals(List.of(1, -2, 3, 4), InputParser.parseIntegers(" 1, -2;3\n4 "));
    }

    @Test
    void blankInputGivesEmptyList() {
        assertEquals(List.of(), InputParser.parseIntegers("  \n "));
    }

    @Test
    void rejectsNonIntegers() {
        assertThrows(IllegalArgumentException.class, () -> InputParser.parseIntegers("1 x"));
        assertThrows(IllegalArgumentException.class, () -> InputParser.parseIntegers("1.5"));
        assertThrows(IllegalArgumentException.class, () -> InputParser.parseIntegers("99999999999"));
    }

    @Test
    void parsesIntArray() {
        assertArrayEquals(new int[]{1, 2, 3}, InputParser.parseIntArray("1 2,3"));
    }

    @Test
    void parsesLinesKeepingBlankOnes() {
        assertEquals(List.of("a", "", "b"), InputParser.parseLines("a\n\nb\n"));
        assertEquals(List.of(), InputParser.parseLines(""));
    }

    @Test
    void parsesCoordinatesWithDotOrComma() {
        assertEquals(1.5, InputParser.parseCoordinate(" 1,5 "), 1e-9);
        assertEquals(-2.0, InputParser.parseCoordinate("-2"), 1e-9);
    }

    @Test
    void rejectsInvalidCoordinates() {
        assertThrows(IllegalArgumentException.class, () -> InputParser.parseCoordinate("abc"));
        assertThrows(IllegalArgumentException.class, () -> InputParser.parseCoordinate("NaN"));
        assertThrows(IllegalArgumentException.class, () -> InputParser.parseCoordinate("Infinity"));
        assertThrows(IllegalArgumentException.class, () -> InputParser.parseCoordinate(""));
    }
}