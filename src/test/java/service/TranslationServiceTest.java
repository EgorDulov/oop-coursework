package service;

import lab3.exception.FileReadException;
import lab3.exception.InvalidFileFormatException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TranslationServiceTest {

    @TempDir
    Path tempDir;

    private final TranslationService service = new TranslationService();

    @Test
    void translatesUsingDictionaryFile() throws Exception {
        Path dictionary = tempDir.resolve("dictionary.txt");
        Files.writeString(dictionary, "look | смотреть\nlook forward | ожидать\n", StandardCharsets.UTF_8);

        assertEquals("dog смотреть to the window, dog ожидать",
                service.translate(dictionary, "dog look to the window, dog look forward"));
    }

    @Test
    void reportsMissingDictionary() {
        assertThrows(FileReadException.class, () -> service.translate(tempDir.resolve("missing.txt"), "text"));
    }

    @Test
    void reportsInvalidDictionaryFormat() throws Exception {
        Path dictionary = tempDir.resolve("broken.txt");
        Files.writeString(dictionary, "broken line\n", StandardCharsets.UTF_8);

        assertThrows(InvalidFileFormatException.class, () -> service.translate(dictionary, "text"));
    }

    @Test
    void readsTextFileWithoutBom() throws Exception {
        Path file = tempDir.resolve("text.txt");
        Files.writeString(file, "\uFEFFhello world", StandardCharsets.UTF_8);

        assertEquals("hello world", TextFileReader.read(file));
    }

    @Test
    void textFileErrorsAreReportedAsFileReadException() {
        assertThrows(FileReadException.class, () -> TextFileReader.read(tempDir.resolve("missing.txt")));
        assertThrows(FileReadException.class, () -> TextFileReader.read(tempDir));
    }
}