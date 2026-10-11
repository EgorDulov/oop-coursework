package service;

public enum InputKind {

    INTEGERS("Целые числа через пробел, запятую или перенос строки"),
    STRINGS("Строки: каждая на отдельной строке поля ввода");

    private final String hint;

    InputKind(String hint) {
        this.hint = hint;
    }

    public String getHint() {
        return hint;
    }
}