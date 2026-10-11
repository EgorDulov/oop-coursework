package service;

import lab4.streams.StreamOperations;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Function;

public enum StreamTask {

    AVERAGE("average — среднее значение списка целых чисел",
            InputKind.INTEGERS, "1 2 3 4",
            input -> StreamOperations.average(InputParser.parseIntegers(input))),

    UPPER_CASE_WITH_PREFIX("toUpperCaseWithPrefix — верхний регистр и префикс «_new_»",
            InputKind.STRINGS, "hello\nWorld",
            input -> StreamOperations.toUpperCaseWithPrefix(InputParser.parseLines(input))),

    SQUARES_OF_UNIQUE("squaresOfUniqueElements — квадраты элементов, встречающихся один раз",
            InputKind.INTEGERS, "1 2 2 3 4 4 5",
            input -> StreamOperations.squaresOfUniqueElements(InputParser.parseIntegers(input))),

    LAST_ELEMENT("lastElement — последний элемент коллекции",
            InputKind.STRINGS, "first\nsecond\nthird",
            input -> StreamOperations.lastElement(InputParser.parseLines(input))),

    SUM_OF_EVEN("sumOfEven — сумма чётных чисел массива",
            InputKind.INTEGERS, "1 2 3 4 5 6",
            input -> StreamOperations.sumOfEven(InputParser.parseIntArray(input))),

    SPLIT_FIRST_CHARACTER("splitFirstCharacter — Map: первый символ → остаток строки",
            InputKind.STRINGS, "apple\nbanana\ncherry",
            input -> StreamOperations.splitFirstCharacter(InputParser.parseLines(input)));

    private final String title;
    private final InputKind inputKind;
    private final String example;
    private final Function<String, Object> action;

    StreamTask(String title, InputKind inputKind, String example, Function<String, Object> action) {
        this.title = title;
        this.inputKind = inputKind;
        this.example = example;
        this.action = action;
    }

    public InputKind getInputKind() {
        return inputKind;
    }

    public String getExample() {
        return example;
    }

    public String run(String input) {
        Objects.requireNonNull(input, "Ввод не может быть null");
        try {
            return "Результат: " + action.apply(input);
        } catch (IllegalArgumentException | NoSuchElementException e) {
            return "Ошибка: " + e.getMessage();
        }
    }

    @Override
    public String toString() {
        return title;
    }
}