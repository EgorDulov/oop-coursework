package service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InvocationReportServiceTest {

    @Test
    void reportContainsAllCallsAndSkipsPublicMethods() {
        String report = new InvocationReportService().run();

        assertTrue(report.contains("Всего вызовов: 12"));
        assertTrue(report.contains("average=4"));
        assertTrue(report.contains("multiply=3"));
        assertFalse(report.contains("publicSum"));
        assertFalse(report.contains("halve"));
    }
}