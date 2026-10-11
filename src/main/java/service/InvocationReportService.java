package service;

import lab2.domain.SampleService;
import lab2.invoker.InvocationResult;
import lab2.invoker.MethodInvocationException;
import lab2.invoker.MethodInvoker;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public final class InvocationReportService {

    public String run() {
        SampleService sample = new SampleService();
        try {
            List<InvocationResult> results = new MethodInvoker().invokeAnnotated(sample);
            Map<String, Long> callsByMethod = results.stream()
                    .collect(Collectors.groupingBy(
                            result -> result.method().getName(), TreeMap::new, Collectors.counting()));

            StringBuilder report = new StringBuilder("Вызовы аннотированных protected и private методов:\n");
            results.forEach(result -> report.append("  ").append(result.describe()).append('\n'));
            report.append("\nВсего вызовов: ").append(results.size()).append('\n');
            report.append("Вызовов по методам: ").append(callsByMethod).append('\n');
            report.append("Записи журнала: ").append(sample.getJournal());
            return report.toString();
        } catch (MethodInvocationException e) {
            return "Ошибка: " + e.getMessage();
        }
    }
}