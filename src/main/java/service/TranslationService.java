package service;

import lab3.dictionary.Dictionary;
import lab3.dictionary.DictionaryLoader;
import lab3.exception.FileReadException;
import lab3.exception.InvalidFileFormatException;
import lab3.translator.Translator;

import java.nio.file.Path;
import java.util.Objects;

public final class TranslationService {

    public String translate(Path dictionaryPath, String text) throws FileReadException, InvalidFileFormatException {
        Objects.requireNonNull(text, "Текст не может быть null");
        Dictionary dictionary = DictionaryLoader.load(dictionaryPath);
        return new Translator(dictionary).translate(text);
    }
}