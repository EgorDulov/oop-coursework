package service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StreamTaskTest {

    @Test
    void runsEveryOperationOnTypicalInput() {
        assertEquals("Результат: 2.5", StreamTask.AVERAGE.run("1 2 3 4"));
        assertEquals("Результат: [_new_HELLO, _new_WORLD]", StreamTask.UPPER_CASE_WITH_PREFIX.run("hello\nWorld"));
        assertEquals("Результат: [1, 9, 25]", StreamTask.SQUARES_OF_UNIQUE.run("1 2 2 3 4 4 5"));
        assertEquals("Результат: third", StreamTask.LAST_ELEMENT.run("first\nsecond\nthird"));
        assertEquals("Результат: 12", StreamTask.SUM_OF_EVEN.run("1 2 3 4 5 6"));
        assertEquals("Результат: {a=pple, b=anana, c=herry}",
                StreamTask.SPLIT_FIRST_CHARACTER.run("apple\nbanana\ncherry"));
    }

    @Test
    void reportsErrorsAsText() {
        assertTrue(StreamTask.AVERAGE.run("").startsWith("Ошибка:"));
        assertTrue(StreamTask.AVERAGE.run("1 x").startsWith("Ошибка:"));
        assertTrue(StreamTask.LAST_ELEMENT.run("").startsWith("Ошибка:"));
        assertTrue(StreamTask.SPLIT_FIRST_CHARACTER.run("apple\navocado").startsWith("Ошибка:"));
        assertTrue(StreamTask.SPLIT_FIRST_CHARACTER.run("apple\n\nbanana").startsWith("Ошибка:"));
    }

    @Test
    void everyExampleProducesResult() {
        for (StreamTask task : StreamTask.values()) {
            assertTrue(task.run(task.getExample()).startsWith("Результат:"), task.name());
        }
    }
}