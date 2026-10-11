package service;

import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public final class InputParser {

    private static final Pattern SEPARATORS = Pattern.compile("[\\s,;]+");

    private InputParser() {
    }

    public static List<Integer> parseIntegers(String input) {
        Objects.requireNonNull(input, "Ввод не может быть null");
        return SEPARATORS.splitAsStream(input.strip())
                .filter(token -> !token.isEmpty())
                .map(InputParser::parseInteger)
                .toList();
    }

    public static int[] parseIntArray(String input) {
        return parseIntegers(input).stream().mapToInt(Integer::intValue).toArray();
    }

    public static List<String> parseLines(String input) {
        Objects.requireNonNull(input, "Ввод не может быть null");
        return input.lines().toList();
    }

    public static double parseCoordinate(String text) {
        Objects.requireNonNull(text, "Ввод не может быть null");
        try {
            double value = Double.parseDouble(text.strip().replace(',', '.'));
            if (Double.isFinite(value)) {
                return value;
            }
        } catch (NumberFormatException e) {
            
        }
        throw new IllegalArgumentException("Координата должна быть конечным числом: «" + text + "»");
    }

    private static int parseInteger(String token) {
        try {
            return Integer.parseInt(token);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Не удалось разобрать целое число: «" + token + "»", e);
        }
    }
}