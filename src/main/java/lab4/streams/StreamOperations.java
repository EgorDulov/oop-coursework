package lab4.streams;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/** Операции над коллекциями, реализованные только средствами Stream API. */
public final class StreamOperations {

    private static final String PREFIX = "_new_";

    private StreamOperations() {
    }

    /**
     * Среднее значение списка целых чисел.
     *
     * @throws NoSuchElementException если список пуст
     */
    public static double average(List<Integer> numbers) {
        Objects.requireNonNull(numbers, "Список не может быть null");
        return numbers.stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElseThrow(() -> new NoSuchElementException(
                        "Нельзя вычислить среднее значение пустого списка"));
    }

    /** Приводит строки к верхнему регистру и добавляет к ним префикс «_new_». */
    public static List<String> toUpperCaseWithPrefix(List<String> strings) {
        Objects.requireNonNull(strings, "Список не может быть null");
        return strings.stream()
                .map(string -> PREFIX + string.toUpperCase(Locale.ROOT))
                .toList();
    }

    /** Квадраты элементов, встречающихся в списке ровно один раз, в порядке их появления. */
    public static List<Long> squaresOfUniqueElements(List<Integer> numbers) {
        Objects.requireNonNull(numbers, "Список не может быть null");
        return numbers.stream()
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() == 1)
                .map(entry -> (long) entry.getKey() * entry.getKey())
                .toList();
    }

    /**
     * Последний элемент коллекции (в порядке её обхода). Элементы null не поддерживаются.
     *
     * @throws NoSuchElementException если коллекция пуста
     */
    public static <T> T lastElement(Collection<T> collection) {
        Objects.requireNonNull(collection, "Коллекция не может быть null");
        return collection.stream()
                .reduce((previous, current) -> current)
                .orElseThrow(() -> new NoSuchElementException("Коллекция пуста"));
    }

    /** Сумма чётных чисел массива или 0, если чётных чисел нет. */
    public static long sumOfEven(int[] numbers) {
        Objects.requireNonNull(numbers, "Массив не может быть null");
        return Arrays.stream(numbers)
                .filter(number -> number % 2 == 0)
                .asLongStream()
                .sum();
    }

    /**
     * Преобразует строки в Map: первый символ — ключ, остальные — значение.
     *
     * @throws IllegalArgumentException если есть пустая строка или две строки с одинаковым первым символом
     */
    public static Map<Character, String> splitFirstCharacter(List<String> strings) {
        Objects.requireNonNull(strings, "Список не может быть null");
        return strings.stream()
                .collect(Collectors.toMap(
                        StreamOperations::firstCharacter,
                        string -> string.substring(1),
                        (first, second) -> {
                            throw new IllegalArgumentException(
                                    "Несколько строк начинаются с одного и того же символа");
                        },
                        LinkedHashMap::new));
    }

    private static char firstCharacter(String string) {
        Objects.requireNonNull(string, "Строка не может быть null");
        if (string.isEmpty()) {
            throw new IllegalArgumentException("Нельзя получить ключ из пустой строки");
        }
        return string.charAt(0);
    }
}