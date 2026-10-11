package lab4.streams;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StreamOperationsTest {


    @Test
    void averageOfSeveralNumbers() {
        assertEquals(2.5, StreamOperations.average(List.of(1, 2, 3, 4)), 1e-9);
    }

    @Test
    void averageOfNegativeAndSingleValues() {
        assertEquals(-1.0, StreamOperations.average(List.of(-3, 1)), 1e-9);
        assertEquals(7.0, StreamOperations.average(List.of(7)), 1e-9);
    }

    @Test
    void averageOfEmptyListThrows() {
        assertThrows(NoSuchElementException.class, () -> StreamOperations.average(List.of()));
    }


    @Test
    void addsPrefixAndUpperCases() {
        assertEquals(List.of("_new_HELLO", "_new_МИР", "_new_"),
                StreamOperations.toUpperCaseWithPrefix(List.of("hello", "Мир", "")));
    }

    @Test
    void upperCaseOfEmptyListIsEmpty() {
        assertEquals(List.of(), StreamOperations.toUpperCaseWithPrefix(List.of()));
    }


    @Test
    void squaresOfElementsThatAppearOnce() {
        assertEquals(List.of(1L, 9L, 25L),
                StreamOperations.squaresOfUniqueElements(List.of(1, 2, 2, 3, 4, 4, 5)));
    }

    @Test
    void squaresKeepOriginalOrder() {
        assertEquals(List.of(9L, 1L, 4L), StreamOperations.squaresOfUniqueElements(List.of(3, 1, 2)));
    }

    @Test
    void squaresOfOnlyDuplicatesIsEmpty() {
        assertEquals(List.of(), StreamOperations.squaresOfUniqueElements(List.of(1, 1, 2, 2)));
        assertEquals(List.of(), StreamOperations.squaresOfUniqueElements(List.of()));
    }

    @Test
    void squaresDoNotOverflowInt() {
        assertEquals(List.of(10_000_000_000L), StreamOperations.squaresOfUniqueElements(List.of(100_000)));
    }


    @Test
    void lastElementOfListAndSet() {
        assertEquals(3, StreamOperations.lastElement(List.of(1, 2, 3)));
        assertEquals("only", StreamOperations.lastElement(List.of("only")));

        Set<String> set = new LinkedHashSet<>(List.of("a", "b", "c"));
        assertEquals("c", StreamOperations.lastElement(set));
    }

    @Test
    void lastElementOfEmptyCollectionThrows() {
        assertThrows(NoSuchElementException.class, () -> StreamOperations.lastElement(List.of()));
        assertThrows(NoSuchElementException.class, () -> StreamOperations.lastElement(Set.of()));
    }

    @Test
    void sumOfEvenNumbers() {
        assertEquals(12, StreamOperations.sumOfEven(new int[]{1, 2, 3, 4, 5, 6}));
        assertEquals(2, StreamOperations.sumOfEven(new int[]{-2, -3, 4}));
    }

    @Test
    void sumIsZeroWhenNoEvenNumbers() {
        assertEquals(0, StreamOperations.sumOfEven(new int[]{1, 3, 5}));
        assertEquals(0, StreamOperations.sumOfEven(new int[]{}));
    }

    @Test
    void sumOfEvenDoesNotOverflowInt() {
        int bigEven = Integer.MAX_VALUE - 1;
        assertEquals(2L * bigEven, StreamOperations.sumOfEven(new int[]{bigEven, bigEven}));
    }

    @Test
    void splitsFirstCharacterAndRest() {
        assertEquals(Map.of('a', "pple", 'b', "anana", 'c', "herry"),
                StreamOperations.splitFirstCharacter(List.of("apple", "banana", "cherry")));
    }

    @Test
    void splitKeepsInputOrderAndSingleCharString() {
        Map<Character, String> result = StreamOperations.splitFirstCharacter(List.of("banana", "x", "apple"));

        assertEquals(List.of('b', 'x', 'a'), new ArrayList<>(result.keySet()));
        assertEquals("", result.get('x'));
    }

    @Test
    void splitRejectsEmptyString() {
        assertThrows(IllegalArgumentException.class,
                () -> StreamOperations.splitFirstCharacter(List.of("apple", "")));
    }

    @Test
    void splitRejectsDuplicateKeys() {
        assertThrows(IllegalArgumentException.class,
                () -> StreamOperations.splitFirstCharacter(List.of("apple", "avocado")));
    }

    @Test
    void splitOfEmptyListIsEmptyMap() {
        assertEquals(Map.of(), StreamOperations.splitFirstCharacter(List.of()));
    }

    @Test
    void nullArgumentsAreRejected() {
        assertThrows(NullPointerException.class, () -> StreamOperations.average(null));
        assertThrows(NullPointerException.class, () -> StreamOperations.toUpperCaseWithPrefix(null));
        assertThrows(NullPointerException.class, () -> StreamOperations.squaresOfUniqueElements(null));
        assertThrows(NullPointerException.class, () -> StreamOperations.lastElement(null));
        assertThrows(NullPointerException.class, () -> StreamOperations.sumOfEven(null));
        assertThrows(NullPointerException.class, () -> StreamOperations.splitFirstCharacter(null));
    }
}