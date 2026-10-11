package service;

import lab3.exception.FileReadException;

import java.io.IOException;
import java.nio.charset.MalformedInputException;
import java.nio.charset.StandardCharsets;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.Objects;

public final class TextFileReader {

    private static final String BOM = "\uFEFF";

    private TextFileReader() {
    }

    public static String read(Path path) throws FileReadException {
        Objects.requireNonNull(path, "Путь к файлу не может быть null");
        try {
            String text = Files.readString(path, StandardCharsets.UTF_8);
            return text.startsWith(BOM) ? text.substring(BOM.length()) : text;
        } catch (NoSuchFileException e) {
            throw new FileReadException("Файл не найден: " + path, e);
        } catch (AccessDeniedException e) {
            throw new FileReadException("Нет доступа к файлу: " + path, e);
        } catch (MalformedInputException e) {
            throw new FileReadException("Файл должен быть в кодировке UTF-8: " + path, e);
        } catch (IOException e) {
            throw new FileReadException("Не удалось прочитать файл " + path + ": " + e.getMessage(), e);
        }
    }
}